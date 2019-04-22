package club.vasilis.xtwh.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import club.vasilis.xtwh.activity.OrganizationFragment;
import club.vasilis.xtwh.activity.RecruitFragment;


public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private String[] mTitles = new String[]{"活动招募", "加入组织", "身边公益","文化介绍"};

    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                //活动招募
                return new OrganizationFragment();
            case 1:
                //加入组织
                return new RecruitFragment();
            case 2:
                //身边公益

            case 3:
                //文化介绍

        }

       return new OrganizationFragment();
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    //ViewPager与TabLayout绑定后，这里获取到PageTitle就是Tab的Text
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
