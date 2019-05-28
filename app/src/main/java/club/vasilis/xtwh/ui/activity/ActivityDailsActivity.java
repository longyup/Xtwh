package club.vasilis.xtwh.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;
import club.vasilis.xtwh.R;

public class ActivityDailsActivity extends Activity {

    @BindView(R.id.iv_web_activity_dails)
    ImageView ivDailsImg;
    @BindView(R.id.tv_web_activity_dails_name)
    TextView tvDailsName;
    @BindView(R.id.tv_web_activity_dails_startTimeTime)
    TextView tvDailsStartTime;
    @BindView(R.id.tv_web_activity_dails_launchTime)
    TextView tvDailsLaunchTime;
    @BindView(R.id.tv_web_activity_dails_info)
    TextView tvDailsInfo;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_web_activity_dails_list_data);
        ButterKnife.bind(this);


    }
}
