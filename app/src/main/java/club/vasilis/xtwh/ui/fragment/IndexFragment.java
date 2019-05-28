package club.vasilis.xtwh.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import club.vasilis.xtwh.R;

/**
 * 首页
 */
public class IndexFragment extends Fragment{


    @BindView(R.id.vp_main_1)
    ViewPager vpMain1;
    @BindView(R.id.tab_culture_introduction)
    TabLayout tabCultureIntroduction;
    @BindView(R.id.vp_main)
    ViewPager vpMain;


    private ArrayList<ImageView> imageViews;
    private List<Fragment> fragmentList;
    private Unbinder bind;

    private ProductFragment titleFragment1 = new ProductFragment();
    private ProductFragment titleFragment3 = new ProductFragment();
    private ProductFragment titleFragment4 = new ProductFragment();
    private static final String TAG = "IndexFragment";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //垂直滚动
        View view = inflater.inflate(R.layout.fragment_index, container, false);
        bind = ButterKnife.bind(this, view);

        initBanner();

        initView();

        return view;
    }

    /**
     * 初始化banner
     */
    private void initBanner() {
        //在ViewPager的初始化之后发送消息
        mHandler.sendEmptyMessageDelayed(0, 1000 * 2);

        //准备数据
        int[] ids = new int[]{
                R.drawable.main1,
                R.drawable.main2,
                R.drawable.main3
        };

        imageViews = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            ImageView imageView = new ImageView(getContext());
            //设置背景
            imageView.setBackgroundResource(ids[i]);

            //添加到集合中去
            imageViews.add(imageView);


            //设置viewpage的适配器
            vpMain1.setAdapter(new MyPagerAdapter());


        }
    }

    /**
     * 初始化viewpager
     */
    private void initView() {
        fragmentList = new ArrayList<>();
        fragmentList.add(CultureSitesFragment.getInstance());
        fragmentList.add(ProductFragment.getInstance());
        fragmentList.add(titleFragment3);
        fragmentList.add(titleFragment4);
        List<String> titleList = new ArrayList<>();
        titleList.add("文化遗产");
        titleList.add("特色美食");
        titleList.add("名人趣事");
        titleList.add("风情民俗");
        vpMain.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Log.e(TAG, "getItem: "+position);
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return titleList.get(position);
            }
        });
        tabCultureIntroduction.setupWithViewPager(vpMain);


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
        @Override
        public void handleMessage(Message msg) {
            MyPagerAdapter myPagerAdapter = new MyPagerAdapter();
            int count = myPagerAdapter.getCount();
            int index = vpMain1.getCurrentItem();
            index = (index + 1) % count;
            vpMain1.setCurrentItem(index);    //收到消息后设置viewPager当前要显示的图片
            mHandler.sendEmptyMessageDelayed(0, 1000 * 2);    //第一个参数随便写；第二个参数表示每两秒刷新一次
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
