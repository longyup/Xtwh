package club.vasilis.xtwh.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import butterknife.BindView;
import butterknife.ButterKnife;
import club.vasilis.xtwh.R;
import club.vasilis.xtwh.ui.activity.BaseActivity;

public class MyMsgPersonageUserAdapter extends RecyclerView.Adapter<MyMsgPersonageUserAdapter.ViewHolder> {

    private static final String TAG = "MyMsgPersonageUserAdapt";

     private String[] userContextTitle;

    public MyMsgPersonageUserAdapter(String[] userContextTitle) {
        this.userContextTitle = userContextTitle;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup,final int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_msg_personage_use_content_item,viewGroup,false);
        final ViewHolder holder = new ViewHolder(view);
        //点击事件

        holder.userView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Context context = v.getContext();
                final int position = holder.getAdapterPosition();
                //测试
                Log.e(TAG, "onClick: "+"You have clicked view"+userContextTitle[position]);
                Toast.makeText(context,"You have clicked view"+userContextTitle[position],Toast.LENGTH_SHORT).show();

                //修改数据
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("设置"+ userContextTitle[position]);
                builder.setCancelable(false);
                builder.setView(new EditText(v.getContext()));
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context,"设置"+userContextTitle[position]+"成功!",Toast.LENGTH_SHORT).show();
//                        BaseActivity.myUser
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
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        //个人信息
        switch (i){
            case 0:
                viewHolder.iv_userHead.setVisibility(View.VISIBLE);//显示头像
                viewHolder.iv_userHead.setImageResource(R.drawable.head);
                viewHolder.tv_userMsg.setVisibility(View.INVISIBLE);//隐藏tv
                break;

            case 1:
                viewHolder.tv_userMsg.setText(BaseActivity.myUser.getNickName());
                break;
            case 2:
                viewHolder.tv_userMsg.setText(BaseActivity.myUser.getName());
                break;
            case 3:
                viewHolder.tv_userMsg.setText(BaseActivity.myUser.getSex());
                break;
            case 4:
                viewHolder.tv_userMsg.setText(BaseActivity.myUser.getPhoneNumber());
                break;
            case 5:
                viewHolder.tv_userMsg.setText(BaseActivity.myUser.getE_mail());
            break;case 6:
                viewHolder.tv_userMsg.setText(BaseActivity.myUser.getBirthday());
            break;case 7:
                viewHolder.tv_userMsg.setText(BaseActivity.myUser.getSignature());
                break;
            case 8:
                viewHolder.tv_userMsg.setText(BaseActivity.myUser.getProfile());
            break;case 9:
                viewHolder.tv_userMsg.setText(BaseActivity.myUser.getLocalPalace());
                break;
        }

        //个人信息标题
        viewHolder.tv_userTitle.setText(userContextTitle[i]);
//        viewHolder.iv_userIcon.setImageResource(myMsgUserContent.getImgId());
    }

    @Override
    public int getItemCount() {
        return userContextTitle.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        View userView;

        @BindView(R.id.my_msg_user_view_line)
        View vLine;

        //用户个人信息
        @BindView(R.id.my_msg_personage_user_frame_user_msg)
        TextView tv_userMsg;

        @BindView(R.id.my_msg_personage_user_frame_head_img)
        ImageView iv_userHead;

        //用户个人界面标题
        @BindView(R.id.my_msg_personage_user_title)
        TextView tv_userTitle;

        @BindView(R.id.my_msg_personage_user_icon)
        ImageView iv_userIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);
            userView = itemView;
//            tv_userMsg = itemView.findViewById(R.id.my_msg_personage_user_frame_user_msg);
//            iv_userHead = itemView.findViewById(R.id.my_msg_personage_user_frame_head_img);
//            tv_userTitle = itemView.findViewById(R.id.my_msg_personage_user_title);
//            iv_userIcon = itemView.findViewById(R.id.my_msg_personage_user_icon);
//            vLine = itemView.findViewById(R.id.my_msg_user_view_line);
        }
    }
}
