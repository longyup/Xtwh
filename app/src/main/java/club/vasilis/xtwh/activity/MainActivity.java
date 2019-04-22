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


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager vp_main;
    private ArrayList<ImageView> imageViews;
    private LinearLayout ll_creatAcity;
    private LinearLayout ll_joinClub;
    private LinearLayout ll_aroundThePublic;
    private LinearLayout ll_cultureIntroduction;


    private CultureIntroductionTitleFragment cFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        cFragment = (CultureIntroductionTitleFragment) getSupportFragmentManager().findFragmentById(R.id.culture_introduction_content_fragment);



        vp_main = findViewById(R.id.vp_main);
        ll_creatAcity = findViewById(R.id.ll_creatAcity);
        ll_joinClub = findViewById(R.id.ll_joinClub);
        ll_aroundThePublic = findViewById(R.id.ll_aroundThePublic);
        ll_cultureIntroduction = findViewById(R.id.ll_cultureIntroduction);
        ll_creatAcity.setOnClickListener(this);
        ll_joinClub.setOnClickListener(this);
        ll_aroundThePublic.setOnClickListener(this);
        ll_cultureIntroduction.setOnClickListener(this);



        //在ViewPager的初始化之后发送消息
        mHandler.sendEmptyMessageDelayed(0, 1000*2);

        //准备数据
        int[] ids = new int[]{
                R.drawable.main1,
                R.drawable.main2,
                R.drawable.main3
        };

        imageViews = new ArrayList<>();
        for (int i = 0;i<ids.length;i++){
            ImageView imageView = new ImageView(this);
            //设置背景
            imageView.setBackgroundResource(ids[i]);

            //添加到集合中去
            imageViews.add(imageView);



            //设置viewpage的适配器
            vp_main.setAdapter(new MyPagerAdapter());


        }
    }


    @Override
    public void onClick(View v) {
            switch (v.getId()){
                case R.id.ll_creatAcity:

                    Toast.makeText(this, "活动招募", Toast.LENGTH_SHORT).show();
                    break ;
                case R.id.ll_joinClub:
                    Toast.makeText(this, "加入组织", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.ll_aroundThePublic:
                    Toast.makeText(this, "身边公益", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.ll_cultureIntroduction:
                    Toast.makeText(this, "文化介绍", Toast.LENGTH_SHORT).show();
                    break;
            }
    }

    //vp_main适配器
    class MyPagerAdapter extends PagerAdapter {

        //返回数据的总个数
        @Override
        public int getCount() {
            return imageViews.size();
        }

        /**
         * 添加控件，添加内容
         * container ViewPager
         * position  要创建页面的位置
         */

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            ImageView imageView = imageViews.get(position);
            //添加到容器中
            container.addView(imageView);

            return imageView;
        }

        /**
         * 判断是否为同一张图片
         * view     当前创建的视图
         * object   instantiateItem返回的结果值
         */

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }

        //destroyItem（）是加入页面的时候，默认缓存三个，如不做处理，滑多了程序就会崩
        //因为它默认是看三张图片，第四张图片的时候就会报错，还有就是不要返回父类的作用

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }

    }

    //自动刷新
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            MyPagerAdapter myPagerAdapter = new MyPagerAdapter();
            int count = myPagerAdapter.getCount();
            int index= vp_main.getCurrentItem();
            index=(index+1)%count;
            vp_main.setCurrentItem(index);    //收到消息后设置viewPager当前要显示的图片
            mHandler.sendEmptyMessageDelayed(0, 1000*2);    //第一个参数随便写；第二个参数表示每两秒刷新一次
        }
    };


}
