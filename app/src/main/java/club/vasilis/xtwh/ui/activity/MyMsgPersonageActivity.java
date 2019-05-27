package club.vasilis.xtwh.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import club.vasilis.xtwh.R;

public class MyMsgPersonageActivity extends BaseActivity {

//    private TextView tv_resetPed;

    @BindView(R.id.my_msg_personage_account_id)
    TextView tv_resetPed;

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

//        tv_resetPed = findViewById(R.id.my_msg_personage_account_id);
        tv_resetPed.setText(String.valueOf(BaseActivity.myUser.getAccount()));
    }

    @OnClick({R.id.my_msg_personage_user_content_fragment,R.id.my_msg_personage_user_reset_pwd})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.my_msg_personage_login:
                Toast.makeText(MyMsgPersonageActivity.this, "You have clicked...退出账号", Toast.LENGTH_SHORT).show();
                break;

            case R.id.my_msg_personage_user_reset_pwd:
                Toast.makeText(MyMsgPersonageActivity.this, "You have clicked...修改密码", Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
