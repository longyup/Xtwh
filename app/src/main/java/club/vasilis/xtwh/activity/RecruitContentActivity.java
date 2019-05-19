package club.vasilis.xtwh.activity;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import club.vasilis.xtwh.fragment.RecruitDailsFragment;
import club.vasilis.xtwh.R;

public class RecruitContentActivity extends AppCompatActivity {

    public static void actionStart(Context context,int ivDailsId,String tvDailsTitleText,String tvDailsDistanceText,String tvDailsRegionText,String tvDailsNumberText){
        Intent intent = new Intent(context,RecruitContentActivity.class);
        intent.putExtra("iv_dailsId",ivDailsId);
        intent.putExtra("tv_dailsTitls",tvDailsTitleText);
        intent.putExtra("tv_dailsDistance",tvDailsDistanceText);
        intent.putExtra("tv_dailsRegion",tvDailsRegionText);
        intent.putExtra("tv_dailsNumber",tvDailsNumberText);
        context.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruit_content);
        int ivDailsId = getIntent().getIntExtra("iv_dailsId",R.drawable.th);
        String tvDailsTitleText = getIntent().getStringExtra("tv_dailsTitls");
        String tvDailsDistanceText = getIntent().getStringExtra("tv_dailsDistance");
        String tvDailsRegionText = getIntent().getStringExtra("tv_dailsRegion");
        String tvDailsNumberText = getIntent().getStringExtra("tv_dailsNumber");

        RecruitDailsFragment fragment = (RecruitDailsFragment) getSupportFragmentManager().findFragmentById(R.id.recurit_dails_fragment);
        fragment.refresh(ivDailsId,tvDailsTitleText,tvDailsDistanceText,tvDailsRegionText,tvDailsNumberText);

    }

}
