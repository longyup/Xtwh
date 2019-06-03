package club.vasilis.xtwh.ui.activity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import club.vasilis.xtwh.R;
import club.vasilis.xtwh.application.MyApplication;
import club.vasilis.xtwh.domain.User;

public class LoginActivity extends AppCompatActivity {


    @BindView(R.id.username)
    EditText etName;
    @BindView(R.id.password)
    EditText etPassword;
    @BindView(R.id.loading)
    ProgressBar loading;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);


    }

    @OnClick(R.id.login)
    public void onViewClicked() {
        String account = etName.getText().toString();
        String password = etPassword.getText().toString();

    }

    @OnClick(R.id.text_log)
    public void onClick() {
        MyApplication.myUser = new User("2017002442","null_demo","demo","demo","男","17326080001",
                "17326080001@163.com","1998-1-1","个性签名demo","个性简历demo","杭州");
        finish();
    }
}
