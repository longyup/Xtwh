package club.vasilis.xtwh.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import club.vasilis.xtwh.R;
import club.vasilis.xtwh.application.MyApplication;
import club.vasilis.xtwh.domain.User;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MyMsgPersonageActivity extends BaseActivity {


//    @BindView(R.id.my_msg_personage_account_id)
//    TextView tv_resetPed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_msg_personage);
        ButterKnife.bind(this);
//        findViewById(R.id.my_msg_personage_login).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MyMsgPersonageActivity.this, "You have clicked...退出账号", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        findViewById(R.id.my_msg_personage_user_reset_pwd).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MyMsgPersonageActivity.this, "You have clicked...修改密码", Toast.LENGTH_SHORT).show();
//
//            }
//        });

//        tv_resetPed.setText(MyApplication.myUser.getAccount());
    }

    @OnClick({R.id.my_msg_personage_user_content_fragment,R.id.my_msg_personage_user_motify_msg,R.id.exit})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.exit:
                Toast.makeText(MyMsgPersonageActivity.this, "You have clicked...退出账号", Toast.LENGTH_SHORT).show();
                break;

            case R.id.my_msg_personage_user_motify_msg:
                Toast.makeText(MyMsgPersonageActivity.this, "You have clicked...确认修改信息", Toast.LENGTH_SHORT).show();
//                motifyRequest();
                printMyUser();
                break;
        }
    }

    private void motifyRequest() {
        String url = MyApplication.HOST+"user";
        //建立请求表单
        RequestBody formBody = new FormBody.Builder()
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
                .add("idCard",MyApplication.myUser.getIdCard())
                .add("localPlace",MyApplication.myUser.getLocalPlace())
                .build();

        //添加请求
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
//        添加一个线程，用于得到服务器响应的数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                Response response = null;
                try {
                    response = MyApplication.client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        String json = response.body().string().trim();
                        int code = JSON.parseObject(json).getInteger("");//获得服务器端的响应数据
                        if (code == 1) {
                            Toast.makeText(MyMsgPersonageActivity.this, "服务器已保存您修改的数据！", Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void printMyUser() {
        System.err.println("myuser的信息");
        System.err.println("acc: "+MyApplication.myUser.getAccount());
        System.err.println("nickname:"+MyApplication.myUser.getNickName());
        System.err.println("name: "+MyApplication.myUser.getName());
        System.err.println("pwd"+MyApplication.myUser.getPassword());
        System.err.println("sex: "+MyApplication.myUser.getSex());
        System.err.println("phone: "+MyApplication.myUser.getPhone());
        System.err.println("e_mail: "+MyApplication.myUser.getE_mail());
        System.err.println("birthday: "+MyApplication.myUser.getBirthday());
        System.err.println("signa: "+MyApplication.myUser.getSignature());
        System.err.println("idcard:"+MyApplication.myUser.getIdCard());
        System.err.println("place"+MyApplication.myUser.getLocalPlace());
    }
}
