package club.vasilis.xtwh.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import club.vasilis.xtwh.R;
import club.vasilis.xtwh.domain.Activity;
import club.vasilis.xtwh.listener.OnItemClickListener;

public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.Holder> {
    private List<Activity> activityList;
    Context context;
    public OnItemClickListener listener;

    public ActivityAdapter() {
    }
    public ActivityAdapter(List<Activity> activityList) {
        this.activityList = activityList;
    }
    public void setActivity(List<Activity> activityList) {
        this.activityList = activityList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ActivityAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.show_web_activity_list_data,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityAdapter.Holder holder, int position) {
        Activity activity = activityList.get(position);
        if (activityList != null){
            holder.tvName.setText(activity.getName());
            holder.tvStartTime.setText(activity.getStartTime());
            holder.tvLaunchTime.setText(activity.getLaunchTime());
            holder.tvId.setText(activity.getId());
            Glide.with(holder.ivImg).load(activity.getImg()).into(holder.ivImg);

        }


    }

    @Override
    public int getItemCount() {
        if (activityList != null){
            return activityList.size();
        }
        return 0;
    }

    public void AddOnItemListener(OnItemClickListener listener){
        this.listener = listener;
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.activity_json_name)
        TextView tvName;
        @BindView(R.id.activity_json_startTime)
        TextView tvStartTime;
        @BindView(R.id.activity_json_launchTime)
        TextView tvLaunchTime;
        @BindView(R.id.activity_json_id)
        TextView tvId;
        @BindView(R.id.activity_json_img)
        ImageView ivImg;

        public Holder(View view) {
            super(view);
            ButterKnife.bind(this,view);
            if (listener != null) {
                itemView.setOnClickListener(this);
            }
        }

        @Override
        public void onClick(View v) {
            listener.onClick(v,getLayoutPosition());
        }
    }
}
