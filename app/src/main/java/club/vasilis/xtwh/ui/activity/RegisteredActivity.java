package club.vasilis.xtwh.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.IOException;
import java.util.UUID;

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

public class RegisteredActivity extends AppCompatActivity {
    @BindView(R.id.register_username)
    EditText register_username;
    @BindView(R.id.register_passwd)
    EditText register_passwd;
    @BindView(R.id.reregister_passwd)
    EditText reregister_passwd;
    @BindView(R.id.register_submit)
    Button register_submit;


    @BindView(R.id.register_toolbar)
    Toolbar registerToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);
        ButterKnife.bind(this);

        registerToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        register_username.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub
                if (!hasFocus) {
                    if (register_username.getText().toString().trim().length() < 4) {
                        Toast.makeText(RegisteredActivity.this, "用户名不能小于4个字符", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });
        register_passwd.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub
                if (!hasFocus) {
                    if (register_passwd.getText().toString().trim().length() < 6) {
                        Toast.makeText(RegisteredActivity.this, "密码不能小于8个字符", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });
        reregister_passwd.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub
                if (!hasFocus) {
                    if (!reregister_passwd.getText().toString().trim().equals(register_passwd.getText().toString().trim())) {
                        Toast.makeText(RegisteredActivity.this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });

        register_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkEdit()) {
                    return;
                }
                String account = register_username.getText().toString().trim();
                String pwd = register_passwd.getText().toString().trim();
                postRequest(account, pwd);

            }
        });


    }

    /**
     * 已有账号，返回登录界面
     */
    @OnClick(R.id.register_prompt)
    public void onClick() {
        Intent intent = new Intent();
        intent.setClass(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * post 请求后台
     *
     * @param account
     * @param pwd
     */
    private void postRequest(String account, String pwd) {
        String uuid = getUUID();
        String url = MyApplication.HOST + "user";
        //建立请求表单，添加上传服务器的数据
        RequestBody formBody = new FormBody.Builder()
                .add("method", "registerA")
                .add("UUID", uuid)
                .add("account", account)
                .add("password", pwd)
                .build();
        //添加请求
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        //添加一个线程，用于得到服务器响应的数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                Response response = null;
                try {
                    response = MyApplication.client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        User user = new User();
                        user.setUUID(uuid);
                        user.setNickName(account);
                        MyApplication.myUser = user;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                Toast.makeText(RegisteredActivity.this, "恭喜您注册成功！", Toast.LENGTH_SHORT).show();

                                finish();
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private String getUUID() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        return str.replaceAll("-", "");
    }

    private boolean checkEdit() {
        if (register_username.getText().toString().trim().equals("")) {
            Toast.makeText(RegisteredActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
        } else if (register_passwd.getText().toString().trim().equals("")) {
            Toast.makeText(RegisteredActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
        } else if (!register_passwd.getText().toString().trim().equals(reregister_passwd.getText().toString().trim())) {
            Toast.makeText(RegisteredActivity.this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
        } else {
            return true;
        }
        return false;
    }


}
