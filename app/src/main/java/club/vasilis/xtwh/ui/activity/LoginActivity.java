package club.vasilis.xtwh.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.alibaba.fastjson.JSONObject;
import com.orhanobut.logger.Logger;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import club.vasilis.xtwh.R;
import club.vasilis.xtwh.application.MyApplication;
import club.vasilis.xtwh.domain.User;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {


    @BindView(R.id.username)
    EditText etName;
    @BindView(R.id.password)
    EditText etPassword;
    @BindView(R.id.loading)
    ProgressBar loading;
    @BindView(R.id.login_toolbar)
    Toolbar loginToolbar;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        loginToolbar.setNavigationOnClickListener(v -> {
            finish();
        });
    }

    @OnClick(R.id.login)
    public void onViewClicked() {
        String account = etName.getText().toString();
        String password = etPassword.getText().toString();
        String url = MyApplication.HOST + "user";
        FormBody body = new FormBody.Builder()
                .add("method", "loginA")
                .add("account", account)
                .add("password", password)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        MyApplication.client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Toast.makeText(LoginActivity.this, "请检查网络连接", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful() && response.body() != null) {
                    String result = response.body().string();
                    if ("0".equals(result)) {
                        etName.post(() -> {
                            Toast.makeText(LoginActivity.this, "登陆失败，请检查账号密码", Toast.LENGTH_SHORT).show();
                            etPassword.setText("");
                        });
                    } else {
                        MyApplication.myUser = JSONObject.parseObject(result, User.class);
                        if (MyApplication.myUser.getNickName() == null || MyApplication.myUser.getNickName().equals("")){
                            MyApplication.myUser.setNickName(MyApplication.myUser.getAccount());
                        }
                        etName.post(() -> {
                            Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                            Logger.d(MyApplication.myUser.toString());
                            finish();
                        });
                    }
                }
            }
        });

    }

    @OnClick(R.id.tv_reg)
    public void onClick() {
        Intent intent = new Intent();
        intent.setClass(this, RegisteredActivity.class);
        startActivity(intent);
        finish();
    }
}
