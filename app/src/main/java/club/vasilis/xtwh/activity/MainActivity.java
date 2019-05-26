package club.vasilis.xtwh.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import club.vasilis.xtwh.R;
import club.vasilis.xtwh.fragment.CommunityFragment;
import club.vasilis.xtwh.fragment.CultureIntroductionTitleFragment;
import club.vasilis.xtwh.fragment.MyMsgFragment;
import club.vasilis.xtwh.fragment.RecruitFragment;


/**
 * 主页面，使用tabLayout+viewPager去加载
 */

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    private TabLayout mainTab;
    private TextView tvTitleBar;

    private ViewPager viewPager;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private List<Fragment> fragmentList;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return fragmentList.size();
            }

            @Override
            public Fragment getItem(int i) {
                return fragmentList.get(i);
            }


        });
        // tablayoutb联动viewpager
        mainTab.setupWithViewPager(viewPager);

        // TabLayout设置关联viewpager后，会清空所有tab栏，所以在此后重新
        mainTab.getTabAt(0).setText("首页").setIcon(R.drawable.home);
        mainTab.getTabAt(1).setText("活动").setIcon(R.drawable.home);
        mainTab.getTabAt(2).setText("社区").setIcon(R.drawable.community);
        mainTab.getTabAt(3).setText("我的").setIcon(R.drawable.mine);

        //侧滑栏的监听事件
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_mine:{
                       Intent intent = new Intent(MainActivity.this, MyMsgPersonageActivity.class);
                       startActivity(intent);
                        break;
                   }
                    case R.id.nav_mine_acitity:{
                        mainTab.getTabAt(1).select();
                        break;
                    }
                    case R.id.nav_setting:{
                        mainTab.getTabAt(3).select();
                        break;
                    }
                    default:
                }

                drawerLayout.closeDrawers();
                return false;
            }
        });

    }


    private void initView() {
        // 对tablayout增加控件
        mainTab = findViewById(R.id.main_tab);
        mainTab.addTab(mainTab.newTab().setText("首页").setIcon(R.drawable.home));
        mainTab.addTab(mainTab.newTab().setText("活动").setIcon(R.drawable.home));
        mainTab.addTab(mainTab.newTab().setText("社区").setIcon(R.drawable.community));
        mainTab.addTab(mainTab.newTab().setText("我的").setIcon(R.drawable.mine));

        mainTab.addOnTabSelectedListener(this);

        viewPager = findViewById(R.id.main_viewpager);
        //

        fragmentList = new ArrayList<>();
        fragmentList.add(new CultureIntroductionTitleFragment());
        fragmentList.add(new RecruitFragment());
        fragmentList.add(new CommunityFragment());
        fragmentList.add(new MyMsgFragment());

        drawerLayout = findViewById(R.id.main_drawer_layout);

        navigationView = findViewById(R.id.main_navigation_view);

        tvTitleBar = findViewById(R.id.main_titlebar);
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        //添加选中Tab的逻辑
        Log.e(TAG, "onTabSelected: "+tab.getPosition() );
        switch (tab.getPosition()){
            case 0:{
                tvTitleBar.setText("文化信息");
                break;
            }
            case 1:{
                tvTitleBar.setText("活动一览");
                break;
            }
            case 2:{
                tvTitleBar.setText("社区交流");
                break;
            }
            case 3:{
                tvTitleBar.setText("个人中心");
                break;
            }
            default:
        }

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        // 添加未选中Tab的逻辑
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        // 再次选中tab的逻辑
    }
}
