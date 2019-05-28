package club.vasilis.xtwh.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import club.vasilis.xtwh.R;
import club.vasilis.xtwh.ui.fragment.ActivityFragment;
import club.vasilis.xtwh.ui.fragment.CommunityFragment;
import club.vasilis.xtwh.ui.fragment.IndexFragment;
import club.vasilis.xtwh.ui.fragment.MyMsgFragment;
import club.vasilis.xtwh.ui.view.CustomViewPager;


/**
 * 主页面，使用tabLayout+viewPager去加载
 */

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


    @BindView(R.id.main_titlebar)
    TextView tvTitleBar;

    @BindView(R.id.main_viewpager)
    CustomViewPager viewPager;

    @BindView(R.id.main_tab)
    BottomNavigationView mainTab;

    @BindView(R.id.main_navigation_view)
    NavigationView navigationView;
    @BindView(R.id.main_drawer_layout)
    DrawerLayout drawerLayout;


    private List<Fragment> fragmentList;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initView();



       /* // tablayoutb联动viewpager
        mainTab.setupWithViewPager(viewPager);

        // TabLayout设置关联viewpager后，会清空所有tab栏，所以在此后重新
        mainTab.getTabAt(0).setText("首页").setIcon(R.drawable.home);
        mainTab.getTabAt(1).setText("活动").setIcon(R.drawable.home);
        mainTab.getTabAt(2).setText("社区").setIcon(R.drawable.community);
        mainTab.getTabAt(3).setText("我的").setIcon(R.drawable.mine);*/

        //侧滑栏的监听事件
        navigationView.setNavigationItemSelectedListener(menuItem -> {
                    switch (menuItem.getItemId()) {
                        case R.id.nav_mine: {
                            Intent intent = new Intent(MainActivity.this, MyMsgPersonageActivity.class);
                            startActivity(intent);
                            break;
                        }
                        case R.id.nav_mine_acitity: {
                            mainTab.setSelectedItemId(R.id.tab_menu_acivity);
                            break;
                        }
                        case R.id.nav_setting: {
                            mainTab.setSelectedItemId(R.id.tab_menu_mine);
                            break;
                        }
                        default:
                    }

                    drawerLayout.closeDrawers();
                    return false;
                }
        );

    }


    private void initView() {
        // 对tablayout增加控件
        mainTab.setOnNavigationItemSelectedListener(this);
        //系统默认选中第一个,但是系统选中第一个不执行onNavigationItemSelected(MenuItem)方法
        // 如果要求刚进入页面就执行clickTabOne()方法,则手动调用选中第一个
        mainTab.setSelectedItemId(R.id.tab_menu_home);

        fragmentList = new ArrayList<>();
        fragmentList.add(new IndexFragment());
        fragmentList.add(new ActivityFragment());
        fragmentList.add(new CommunityFragment());
        fragmentList.add(new MyMsgFragment());

        //为viewpager设置adapter
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return fragmentList.size();
            }

            @NonNull
            @Override
            public Fragment getItem(int i) {
                return fragmentList.get(i);
            }
        });
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        switch (itemId) {
            case R.id.tab_menu_home: {
                // 防止点击跳页切换时显示期间的内容，去除了滑动的动画
                viewPager.setCurrentItem(0,false);
                tvTitleBar.setText("文化信息");
                break;
            }
            case R.id.tab_menu_acivity: {
                viewPager.setCurrentItem(1,false);
                tvTitleBar.setText("活动一览");
                break;
            }
            case R.id.tab_menu_community: {
                viewPager.setCurrentItem(2,false);
                tvTitleBar.setText("社区交流");
                break;
            }
            case R.id.tab_menu_mine: {
                viewPager.setCurrentItem(3,false);
                tvTitleBar.setText("个人中心");
                break;
            }
            default:
        }
        return true;
    }

}
