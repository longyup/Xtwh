package club.vasilis.xtwh.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import club.vasilis.xtwh.R;
import club.vasilis.xtwh.ui.activity.BaseActivity;
import club.vasilis.xtwh.domain.Comment;
import club.vasilis.xtwh.domain.Community;
import club.vasilis.xtwh.domain.Phrase;
import club.vasilis.xtwh.domain.User;
import club.vasilis.xtwh.utils.Util;

/**
 * @author Vasilis
 * @date 2019/3/29 * 13:28
 */
public class CommunityAdapter extends RecyclerView.Adapter<CommunityAdapter.ViewHolder> {

    private List<Community> communityList;
    private List<User> userList;
    private List<Comment> commentList;
    private int size;


    public CommunityAdapter(List<Community> communityList, List<User> userList, List<Comment> commentList) {
        this.communityList = communityList;
        this.userList = userList;
        this.commentList = commentList;
    }

    public CommunityAdapter(List<Community> communityList, List<User> userList) {
        this.communityList = communityList;
        this.userList = userList;
        size = communityList.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.community_item, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final Community community = communityList.get(size - i - 1);

        //获取发表动态的用户
        User user = Util.getUser(community.getUUID(), userList);
        // 头像应该从网上下载
        //viewHolder.ivHead.setImageBitmap();
        // viewHolder.tvName.setText(user.getName());
        viewHolder.tvDate.setText(community.getDate());
        viewHolder.tvContent.setText(community.getContent());
        if (community.isPhrase()) {
            viewHolder.ivPhrase.setBackgroundResource(R.drawable.phrase);
        } else {
            viewHolder.ivPhrase.setBackgroundResource(R.drawable.unphrase);
        }

        if (community.isPhrase()) {
            viewHolder.ivPhrase.setImageResource(R.drawable.phrase);
            viewHolder.tvPhrase.setText(String.valueOf(community.getPhraseNum()));
        } else {
            viewHolder.ivPhrase.setImageResource(R.drawable.unphrase);
            viewHolder.tvPhrase.setText("点赞");
        }


        viewHolder.phrase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (community.isPhrase()) {
                    //从表中删除
                    viewHolder.ivPhrase.setImageResource(R.drawable.unphrase);
                    viewHolder.tvPhrase.setText("点赞");
                    community.setPhraseNum(community.getPhraseNum() - 1);
                    community.setPhrase(false);

                } else {
                    String year = Util.getNowYear();
                    String date = Util.getNowDate();
                    String time = Util.getNowTime();
                    StringBuilder sb = new StringBuilder();
                    sb.append(year)
                            .append("-")
                            .append(date)
                            .append(" ")
                            .append(time);
                    Phrase phrase = new Phrase(1, sb.toString(), BaseActivity.myUser.getNickName(), community.getId());
                    viewHolder.ivPhrase.setImageResource(R.drawable.phrase);
                    community.setPhraseNum(community.getPhraseNum() + 1);
                    viewHolder.tvPhrase.setText(String.valueOf(community.getPhraseNum()));
                    community.setPhrase(true);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return size;
    }

    public void update(Community community) {
        this.communityList.add(community);
        size = communityList.size();
        notifyDataSetChanged();
        // 重新获取数据
        /*communityList

        notifyDataSetChanged();*/
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
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

        }
    }

}
