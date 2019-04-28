package club.vasilis.xtwh.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import club.vasilis.xtwh.R;

public class MyMsgPersonageActivity extends BaseActivity {

    private TextView tv_resetPed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_msg_personage);
        findViewById(R.id.my_msg_personage_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MyMsgPersonageActivity.this,"You have clicked...退出账号",Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.my_msg_personage_user_reset_pwd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MyMsgPersonageActivity.this,"You have clicked...修改密码",Toast.LENGTH_SHORT).show();

            }
        });

        tv_resetPed = findViewById(R.id.my_msg_personage_account_id);
        tv_resetPed.setText(String.valueOf(BaseActivity.myUser.getAccount()));
    }
}
