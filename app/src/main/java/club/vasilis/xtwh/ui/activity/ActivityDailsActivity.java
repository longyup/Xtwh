package club.vasilis.xtwh.ui.activity;;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import club.vasilis.xtwh.R;
import club.vasilis.xtwh.ui.fragment.ActivityDailsFragment;

public class ActivityDailsActivity extends AppCompatActivity {


    public static void  actionStart(Context context,String ivDails,String tvDailsNameText,String tvDailsStartTimeText,String tvDailsLaunchTimeText,String tvDailsInfoText){
                Intent intent = new Intent();
                intent.setClass(context,ActivityDailsActivity.class);
                intent.putExtra("iv_dails",ivDails);
                intent.putExtra("tv_dailsName",tvDailsNameText);
                intent.putExtra("tv_dailsStartTime",tvDailsStartTimeText);
                intent.putExtra("tv_dailsLaunchTime",tvDailsLaunchTimeText);
                intent.putExtra("tv_dailsInfo",tvDailsInfoText);
                context.startActivity(intent);


    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dails_activity);

        String ivDails = getIntent().getStringExtra("iv_dails");
        String tvDailsNameText = getIntent().getStringExtra("tv_dailsName");
        String tvDailsStartTimeText = getIntent().getStringExtra("tv_dailsStartTime");
        String tvDailsLaunchTimeText = getIntent().getStringExtra("tv_dailsLaunchTime");
        String tvDailsInfoText = getIntent().getStringExtra("tv_dailsInfo");

        ActivityDailsFragment dailsFragment = (ActivityDailsFragment) getSupportFragmentManager().findFragmentById(R.id.activityDailsFragment);
        if (dailsFragment != null) {
            dailsFragment.refresh(ivDails,tvDailsNameText,tvDailsStartTimeText,tvDailsLaunchTimeText,tvDailsInfoText);
        }

    }




}
