package club.vasilis.xtwh.activity;

import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import club.vasilis.xtwh.Fragment.CultureIntroductionTitleFragment;
import club.vasilis.xtwh.R;
import club.vasilis.xtwh.domain.CultureIntroduction;


public class MainActivity extends AppCompatActivity{




    private CultureIntroductionTitleFragment cFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        cFragment = (CultureIntroductionTitleFragment) getSupportFragmentManager().findFragmentById(R.id.culture_introduction_content_fragment);








    }





}
