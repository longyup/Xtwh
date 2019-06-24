package club.vasilis.xtwh.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import club.vasilis.xtwh.R;
import club.vasilis.xtwh.application.MyApplication;
import club.vasilis.xtwh.domain.Community;
import club.vasilis.xtwh.domain.User;
import club.vasilis.xtwh.listener.OnItemClickListener;
import club.vasilis.xtwh.utils.TimeUtils;

/**
 * @author Vasilis
 * @date 2019/3/29 * 13:28
 */
public class CommunityAdapter extends RecyclerView.Adapter<CommunityAdapter.ViewHolder> {

    private List<Community> communityList;
    private int size;

    private OnItemClickListener listener;


    public void addOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_community, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        Community community = communityList.get(i);
        //获取发表动态的用户
        User user = community.getUser();
        // 设置头像
        String headImg = user.getHeadImg();
        if (headImg != null && !"".equals(headImg)) {
            Glide.with(viewHolder.itemView.getContext()).load(MyApplication.HOST + headImg).into(viewHolder.ivHead);
        } else {
            viewHolder.ivHead.setImageResource(R.drawable.head);
        }
        // 设置用户名
        viewHolder.tvName.setText(user.getNickName());
        //设置时间
        viewHolder.tvDate.setText(TimeUtils.stampToDate(community.getDate()));
        viewHolder.tvContent.setText(community.getContent());
        // 设置点赞图标
        if (community.isPhrase()) {
            viewHolder.ivPhrase.setImageResource(R.drawable.phrase);
            viewHolder.tvPhrase.setText(String.valueOf(community.getPhraseList().size()));
        } else {
            viewHolder.ivPhrase.setImageResource(R.drawable.unphrase);
            viewHolder.tvPhrase.setText("点赞");
        }

    }


    @Override
    public int getItemCount() {
        return size;
    }

    /**
     * 添加说说
     * @param community
     */
    public void update(Community community) {
       // this.communityList.add(0, community);
        size = communityList.size();
        notifyDataSetChanged();
    }

    /**
     * 下拉刷新
     * @param communityList
     */
    public void refresh(List<Community> communityList) {
        this.communityList = communityList;
        size = communityList.size();
        notifyDataSetChanged();
    }
    public void refreshItem(int position ,Community community){
        communityList.set(position,community);
        notifyItemChanged(position);
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.community_item_iv_head)
        ImageView ivHead;
        @BindView(R.id.community_item_tv_name)
        TextView tvName;
        @BindView(R.id.community_item_tv_date)
        TextView tvDate;
        @BindView(R.id.community_item_tv_content)
        TextView tvContent;
        @BindView(R.id.community_item_image_group)
        LinearLayout imageGroup;
        @BindView(R.id.community_item_iv_phrase)
        ImageView ivPhrase;
        @BindView(R.id.community_item_tv_phrase)
        TextView tvPhrase;
        @BindView(R.id.community_item_phrase)
        LinearLayout phrase;
        @BindView(R.id.community_item_iv_comment)
        ImageView ivComment;
        @BindView(R.id.community_item_tv_comment)
        TextView tvComment;
        @BindView(R.id.community_item_comment)
        LinearLayout comment;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
          //  itemView.setOnClickListener(this);
            phrase.setOnClickListener(this);
            comment.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.onClick(v, getLayoutPosition());
            }
        }
    }

}
