package club.vasilis.xtwh.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import club.vasilis.xtwh.R;
import club.vasilis.xtwh.ui.activity.RecruitContentActivity;
import club.vasilis.xtwh.domain.Recruit;

public class RecruitActivityAdapter extends RecyclerView.Adapter<RecruitActivityAdapter.ViewHolder> {
    private List<Recruit> Datas;
    private Context mContext;
    private LayoutInflater inflater;


    public RecruitActivityAdapter(List<Recruit> Datas, Context mContext) {
        this.Datas = Datas;
        this.mContext = mContext;
        inflater=LayoutInflater. from(mContext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recruit_activity_item,viewGroup,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Recruit recruit = Datas.get(holder.getAdapterPosition());
//                RecruitContentActivity.actionStart(mContext,recruit.getImgId(),recruit.getTitle(),recruit.getDistance(),recruit.getRegion(),recruit.getNumber());
            }
        });


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
    Recruit recruit = Datas.get(i);
    viewHolder.titletv.setText(recruit.getTitle());
    viewHolder.regiontv.setText(recruit.getRegion());
    viewHolder.distancetv.setText(recruit.getDistance());
    viewHolder.numbertv.setText(recruit.getNumber());
    viewHolder.resuritIv.setImageResource(recruit.getImgId());
    }



    @Override
    public int getItemCount() {
        return Datas.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.recurit_iv)
        ImageView resuritIv;
        @BindView(R.id.recruit_title)
        TextView titletv;
        @BindView(R.id.recurit_distance_tv)
        TextView distancetv;
        @BindView(R.id.recurit_region_tv)
        TextView regiontv;
        @BindView(R.id.recurit_peoplenumber_tv)
        TextView numbertv;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }


}
