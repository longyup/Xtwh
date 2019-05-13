package club.vasilis.xtwh.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import club.vasilis.xtwh.R;
import club.vasilis.xtwh.activity.RecruitContentActivity;
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
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recruit_activity_item,viewGroup,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Recruit recruit = Datas.get(holder.getAdapterPosition());
                RecruitContentActivity.actionStart(mContext,recruit.getImgId(),recruit.getTitle(),recruit.getDistance(),recruit.getRegion(),recruit.getNumber());

//                showNormalDialog();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
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
        ImageView resuritIv;
        TextView titletv;
        TextView distancetv;
        TextView regiontv;
        TextView numbertv;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titletv = itemView.findViewById(R.id.recruit_title);
            distancetv = itemView.findViewById(R.id.recurit_distance_tv);
            regiontv = itemView.findViewById(R.id.recurit_region_tv);
            numbertv = itemView.findViewById(R.id.recurit_peoplenumber_tv);
            resuritIv = itemView.findViewById(R.id.recurit_iv);
        }
    }
}
