package club.vasilis.xtwh.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import club.vasilis.xtwh.R;
import club.vasilis.xtwh.application.MyApplication;
import club.vasilis.xtwh.domain.User;
import okhttp3.FormBody;
import okhttp3.Request;

public class MyMsgPersonageUserAdapter extends RecyclerView.Adapter<MyMsgPersonageUserAdapter.ViewHolder> {

    private static final String TAG = "MyMsgPersonageUserAdapt";
//    private final Context context;

    String[] users = new String[]{MyApplication.myUser.getPassword(),MyApplication.myUser.getNickName(),MyApplication.myUser.getName(),
                                    MyApplication.myUser.getSex(),MyApplication.myUser.getPhone(), MyApplication.myUser.getE_mail(),MyApplication.myUser.getBirthday(),
                                    MyApplication.myUser.getSignature(),MyApplication.myUser.getIdCard(),
                                    MyApplication.myUser.getLocalPlace()};


    private String[] userContextTitle;

    private View view;

//    private List<User> userMsgList = MyApplication.myUser;

//    public MyMsgPersonageUserAdapter(String[] userContextTitle,Context context) {
//        this.userContextTitle = userContextTitle;
//        this.context = context;
//
//    }

    public MyMsgPersonageUserAdapter(String[] userContextTitle) {
        this.userContextTitle = userContextTitle;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup,final int i) {
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_my_msg_personage_use_content,viewGroup,false);
        final ViewHolder holder = new ViewHolder(view);
        //点击事件

        holder.userView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Context context = v.getContext();
                final int position = holder.getAdapterPosition();
                //测试
//                Log.e(TAG, "onClick: "+"You have clicked view"+userContextTitle[position]);
//                Toast.makeText(context,"You have clicked view"+userContextTitle[position]+position,Toast.LENGTH_SHORT).show();

                //修改数据
                EditText et = new EditText(v.getContext());
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("设置"+ userContextTitle[position]);
                builder.setCancelable(false);
                builder.setView(et);
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (position != 0){
                            Toast.makeText(context,position+"设置"+userContextTitle[position]+"成功!",Toast.LENGTH_SHORT).show();
                            String msg = et.getText().toString();
                            System.err.println(msg);
                            holder.tv_userMsg.setText(msg);
                            setMotifyMsg(position,msg,v);
                        }
                    }
                });

                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
//                refreshMsg();
//                motifyRequest(refreshMsg());
            }
        });
        return holder;
    }


    private void setMotifyMsg(int position, String msg, View v) {
        switch (position){
            case 0:
                Toast.makeText(v.getContext(),"修改了头像",Toast.LENGTH_SHORT).show();
                break;
            case 1:
                MyApplication.myUser.setNickName(msg);
                break;
            case 2:
                MyApplication.myUser.setName(msg);
                break;
            case 3:
                MyApplication.myUser.setSex(msg);
                break;
            case 4:
                MyApplication.myUser.setPhone(msg);
                break;
            case 5:
                MyApplication.myUser.setE_mail(msg);
                break;
            case 6:
                MyApplication.myUser.setBirthday(msg);
                break;
            case 7:
                MyApplication.myUser.setSignature(msg);
                break;
            case 8:
                MyApplication.myUser.setIdCard(msg);
                break;
            case 9:
                MyApplication.myUser.setLocalPlace(msg);
                break;
            case 10:
                MyApplication.myUser.setPassword(msg);
                break;
        }
    }

    private void printMyUser(User user) {
        System.err.println("myuser的信息");
        System.err.println("acc: "+user.getAccount());
        System.err.println("nickname:"+user.getNickName());
        System.err.println("name: "+user.getName());
        System.err.println("pwd"+user.getPassword());
        System.err.println("sex: "+user.getSex());
        System.err.println("phone: "+user.getPhone());
        System.err.println("e_mail: "+user.getE_mail());
        System.err.println("birthday: "+user.getBirthday());
        System.err.println("signa: "+user.getSignature());
        System.err.println("idcard:"+user.getIdCard());
        System.err.println("place"+user.getLocalPlace());
    }

    /**
     * 后台请求
     * @param motifyUser
     */
    private void motifyRequest(User motifyUser) {
        String url = MyApplication.HOST+"user";
        //建立请求表单
        FormBody formBody = new FormBody.Builder()
                .add("method","motifyMsg")
                .add("account",MyApplication.myUser.getAccount())
                .add("nickName",MyApplication.myUser.getNickName())
                .add("name",MyApplication.myUser.getName())
                .add("password",MyApplication.myUser.getPassword())
                .add("sex",MyApplication.myUser.getSex())
                .add("phone",MyApplication.myUser.getPhone())
                .add("e_mail",MyApplication.myUser.getE_mail())
                .add("birthday",MyApplication.myUser.getBirthday())
                .add("signature",MyApplication.myUser.getSignature())
                .add("idcard",MyApplication.myUser.getIdCard())
                .add("localPlace",MyApplication.myUser.getLocalPlace())
                .build();

        //添加请求
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        //添加一个线程，用于得到服务器响应的数据
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Response response = null;
//                try {
//                    response = MyApplication.client.newCall(request).execute();
//                    if (response.isSuccessful()) {
//                        String json = response.body().string().trim();
//                        int code = JSON.parseObject(json).getInteger("");//获得服务器端的响应数据
//                        if (code == 1) {
//                            Toast.makeText(context, "服务器已保存您修改的数据！", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        //个人信息
        switch (i){
            case 0:
                //如果没有头像，设置为默认头像
                viewHolder.iv_userHead.setVisibility(View.VISIBLE);//显示头像
                viewHolder.tv_userMsg.setVisibility(View.INVISIBLE);//隐藏tv
                if (MyApplication.myUser.getHeadImg() != null){
                    Glide.with(view).load(MyApplication.myUser.getHeadImg()).into(viewHolder.iv_userHead);
                }else {
                    viewHolder.iv_userHead.setImageResource(R.drawable.head);
                }

                break;

            case 1:
                viewHolder.tv_userMsg.setText(MyApplication.myUser.getNickName());
                break;
            case 2:
                viewHolder.tv_userMsg.setText(MyApplication.myUser.getName());
                break;
            case 3:
                viewHolder.tv_userMsg.setText(MyApplication.myUser.getSex());
                break;
            case 4:
                viewHolder.tv_userMsg.setText(MyApplication.myUser.getPhone());
                break;
            case 5:
                viewHolder.tv_userMsg.setText(MyApplication.myUser.getE_mail());
                break;
            case 6:
                viewHolder.tv_userMsg.setText(MyApplication.myUser.getBirthday());
            break;case 7:
                viewHolder.tv_userMsg.setText(MyApplication.myUser.getSignature());
                break;
            case 8:
                viewHolder.tv_userMsg.setText(MyApplication.myUser.getProfile());
                break;
            case 9:
                viewHolder.tv_userMsg.setText(MyApplication.myUser.getLocalPlace());
                break;
            case 10:
//                viewHolder.tv_userMsg.setText("");
                viewHolder.iv_userHead.setVisibility(View.INVISIBLE);//显示头像
            //    viewHolder.tv_userMsg.setVisibility(View.INVISIBLE);//隐藏tv
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
