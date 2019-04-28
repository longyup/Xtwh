package club.vasilis.xtwh.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import club.vasilis.xtwh.Fragment.CultureIntroductionTitleFragment;
import club.vasilis.xtwh.Fragment.RecruitFragment;
import club.vasilis.xtwh.R;


public class MainActivity extends AppCompatActivity{




    private CultureIntroductionTitleFragment cFragment;
    private RecruitFragment recruitFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cFragment = (CultureIntroductionTitleFragment) getSupportFragmentManager().findFragmentById(R.id.culture_introduction_content_fragment);

        recruitFragment = (RecruitFragment) getSupportFragmentManager().findFragmentById(R.id.recruit_fragment);

    }





}
