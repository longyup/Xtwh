package club.vasilis.xtwh.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import club.vasilis.xtwh.R;
import club.vasilis.xtwh.application.MyApplication;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);
        ButterKnife.bind(this);


        register_username.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub
                if(!hasFocus){
                    if(register_username.getText().toString().trim().length()<4){
                        Toast.makeText(RegisteredActivity.this, "用户名不能小于4个字符", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });
        register_passwd.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub
                if(!hasFocus){
                    if(register_passwd.getText().toString().trim().length()<6){
                        Toast.makeText(RegisteredActivity.this, "密码不能小于8个字符", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });
        reregister_passwd.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub
                if(!hasFocus){
                    if(!reregister_passwd.getText().toString().trim().equals(register_passwd.getText().toString().trim())){
                        Toast.makeText(RegisteredActivity.this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });

        register_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkEdit()){
                    return;
                }
                String name =  register_username.getText().toString().trim();
                String pwd = register_passwd.getText().toString().trim();
                postRequest(name,pwd);

            }
        });


    }

    /**
     * 已有账号，返回登录界面
     * @param button
     */
    @OnClick(R.id.register_prompt)
        public void onClick(Button button){
            switch (button.getId()){
                case R.id.register_prompt:

                    break;
            }
    }

    /**
     * post 请求后台
     * @param name
     * @param pwd
     */
    private void postRequest(String name, String pwd) {

        String url = MyApplication.HOST + "user?";
        //建立请求表单，添加上传服务器的数据
        RequestBody formBody = new FormBody.Builder()
                .add("method","registerA")
                .add("username",name)
                .add("password",pwd)
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
                    if (response.isSuccessful()){
                        String json = response.body().string().trim();
                        int code = JSON.parseObject(json).getInteger("");
                        if (code == 0){
                            Toast.makeText(RegisteredActivity.this, "恭喜您注册成功！", Toast.LENGTH_SHORT).show();
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
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

    private boolean checkEdit(){
        if(register_username.getText().toString().trim().equals("")){
            Toast.makeText(RegisteredActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
        }else if(register_passwd.getText().toString().trim().equals("")){
            Toast.makeText(RegisteredActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
        }else if(!register_passwd.getText().toString().trim().equals(reregister_passwd.getText().toString().trim())){
            Toast.makeText(RegisteredActivity.this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
        }else{
            return true;
        }
        return false;
    }


}
