package club.vasilis.xtwh.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import club.vasilis.xtwh.R;
import club.vasilis.xtwh.domain.MyMsgUserContent;

public class MyMsgPersonageUserAdapter extends RecyclerView.Adapter<MyMsgPersonageUserAdapter.ViewHolder> {

    private static final String TAG = "MyMsgPersonageUserAdapt";
    private List<MyMsgUserContent> mUserContentList ;

    public MyMsgPersonageUserAdapter(List<MyMsgUserContent> userContents) {
        this.mUserContentList = userContents;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_msg_personage_use_content_item,viewGroup,false);
        final ViewHolder holder = new ViewHolder(view);
        //点击事件
        holder.tv_userName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                final Context context = v.getContext();
                final MyMsgUserContent userContent = mUserContentList.get(position);

                //测试
                Log.e(TAG, "onClick: "+"You have clicked view"+userContent.getName());
                Toast.makeText(context,"You have clicked view"+userContent.getName(),Toast.LENGTH_SHORT).show();

                //修改数据
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("设置"+userContent.getName());
                builder.setCancelable(false);
                builder.setView(new EditText(v.getContext()));
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context,"设置"+userContent.getName()+"成功!",Toast.LENGTH_SHORT).show();

                    }
                });

                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });

        holder.iv_userImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                MyMsgUserContent userContent = mUserContentList.get(position);
                Log.e(TAG, "onClick: "+"You have clicked view"+userContent.getName());
                Toast.makeText(v.getContext(),"You have clicked view"+userContent.getName(),Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        MyMsgUserContent userContent = mUserContentList.get(i);
        viewHolder.tv_userName.setText(userContent.getName());
        viewHolder.iv_userImg.setImageResource(userContent.getImgId());
    }

    @Override
    public int getItemCount() {
        return mUserContentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        View userView;
        View vLine;
        TextView tv_userName;
        ImageView iv_userImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userView = itemView;
            tv_userName = itemView.findViewById(R.id.my_msg_personage_user_content);
            iv_userImg = itemView.findViewById(R.id.my_msg_personage_user_img);
            vLine = itemView.findViewById(R.id.my_msg_user_view_line);
        }
    }
}
