package club.vasilis.xtwh.activity;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import java.util.ArrayList;
import club.vasilis.xtwh.Fragment.CultureIntroductionTitleFragment;
import club.vasilis.xtwh.R;


public class MainActivity extends AppCompatActivity{




    private CultureIntroductionTitleFragment cFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        cFragment = (CultureIntroductionTitleFragment) getSupportFragmentManager().findFragmentById(R.id.culture_introduction_content_fragment);








    }





}
