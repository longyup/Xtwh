package club.vasilis.xtwh.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import club.vasilis.xtwh.GlideImageLoader;
import club.vasilis.xtwh.R;

/**
 * 首页
 */
public class IndexFragment extends Fragment{


    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.tab_culture_introduction)
    TabLayout tabCultureIntroduction;
    @BindView(R.id.vp_main)
    ViewPager vpMain;


    private ArrayList<ImageView> imageViews;
    private List<Fragment> fragmentList;
    private Unbinder bind;

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
        //mHandler.sendEmptyMessageDelayed(0, 1000 * 2);


        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        Integer[] images={R.drawable.main1,
                R.drawable.main2,
                R.drawable.main3};
        banner.setImages(Arrays.asList(images));
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        String[] titles = {"特色小镇","传统农具","剪纸艺术"};
        banner.setBannerTitles(Arrays.asList(titles));
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();

    }

    /**
     * 初始化viewpager
     */
    private void initView() {
        fragmentList = new ArrayList<>();
        fragmentList.add(CultureSitesFragment.getInstance());
        fragmentList.add(ProductFragment.getInstance());
        fragmentList.add(titleFragment3);
        fragmentList.add(FolkCustomFragment.getInstance());
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







    @Override
    public void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
