package club.vasilis.xtwh.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import club.vasilis.xtwh.R;
import club.vasilis.xtwh.domain.Recruit;

public class RecruitActivityAdapter extends RecyclerView.Adapter<RecruitActivityAdapter.ViewHolder> {

    private List<Recruit> Datas;
//    private List<String> distanceDatas;
//    private List<String> regionActivityDatas;
//    private List<String> numberDatas;
    private Context mContext;
    private LayoutInflater inflater;
    private FragmentManager fragmentManager;

    public RecruitActivityAdapter(List<Recruit> Datas, Context mContext,FragmentManager fragmentManager) {
        this.Datas = Datas;
//        this.distanceDatas = distanceDatas;
//        this.regionActivityDatas = regionActivityDatas;
//        this.numberDatas = numberDatas;
        this.mContext = mContext;
        inflater=LayoutInflater. from(mContext);
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recruit_activity_item,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNormalDialog();
            }
        });
        return holder;
    }

    private void showNormalDialog(){
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(mContext);
        normalDialog.setIcon(R.drawable.back);
        normalDialog.setTitle("志愿活动");
        normalDialog.setMessage("是否查看？");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DailsDialog.getInstance().show(fragmentManager,"活动详情");
                    }
                });
        normalDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        // 显示
        normalDialog.show();
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
