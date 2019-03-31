package club.vasilis.xtwh.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import club.vasilis.xtwh.R;

public class MyMsgPersonageUseraAdapter extends RecyclerView.Adapter<MyMsgPersonageUseraAdapter.ViewHolder> {

    private 

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View userView;
        View vLine;
        TextView tv_userName;
        ImageView iv_userImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userView = itemView;
            tv_userName = itemView.findViewById(R.id.my_msg_personage_user_content;
            iv_userImg = itemView.findViewById(R.id.my_msg_personage_user_img);
            vLine = itemView.findViewById(R.id.my_msg_user_view_line);
        }
    }
}
