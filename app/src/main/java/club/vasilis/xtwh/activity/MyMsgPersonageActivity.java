package club.vasilis.xtwh.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import club.vasilis.xtwh.R;

public class MyMsgPersonageActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_msg_personage);
        findViewById(R.id.my_msg_personage_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MyMsgPersonageActivity.this,"You have clicked...",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
