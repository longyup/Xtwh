package club.vasilis.xtwh.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


import club.vasilis.xtwh.R;
import club.vasilis.xtwh.utils.CacheUtils;
import club.vasilis.xtwh.utils.DensityUtil;


import java.util.ArrayList;

public class GuideActivity extends AppCompatActivity {

    private ViewPager viewpager;
    private Button btn_start_main;
    private LinearLayout ll_point_group;
    private ArrayList<ImageView> imageViews;
    private ImageView iv_red_point;

    //两点的间距
    private int leftmax;

    private int widthdpi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        viewpager = findViewById(R.id.viewpager);
        btn_start_main = findViewById(R.id.btn_start_main);
        ll_point_group = findViewById(R.id.ll_point_group);
        iv_red_point = findViewById(R.id.iv_red_point);

        //设置按钮点击事件
        btn_start_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1.保存曾经进入过的主页面
                CacheUtils.putBoolean(GuideActivity.this,WelcomeActivity.START_MAIN,true);
                //2.跳转到主页面
                Intent intent = new Intent(GuideActivity.this,MainActivity.class);
                startActivity(intent);
                //3.关闭引导页
                finish();
            }
        });

        //准备数据
        int[] ids = new int[]{
                R.drawable.guide_1,
                R.drawable.guide_2,
                R.drawable.guide_3
        };

        //px,dp之间转换
        widthdpi = DensityUtil.dip2px(this,10);

        imageViews = new ArrayList<>();
        for (int i = 0;i<ids.length;i++){
            ImageView imageView = new ImageView(this);
            //设置背景
            imageView.setBackgroundResource(ids[i]);

            //添加到集合中去
            imageViews.add(imageView);

            //创建点
            ImageView point = new ImageView(this);
            point.setBackgroundResource(R.drawable.point_normal);
            //布局方面的属性设置方式   单位为像素
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(widthdpi,widthdpi);
            if (i !=0){
                //不包括第0个，所有的点距离左边有10个像素
                params.leftMargin = widthdpi;
            }
            point.setLayoutParams(params);
            //添加到线性布局中去
            ll_point_group.addView(point);

            //设置viewpage的适配器
            viewpager.setAdapter(new MyPagerAdapter());

            //根据View的生命周期，当视图执行到OnLayout或者OnDraw的时候，视图的高和宽，边距都有了
            iv_red_point.getViewTreeObserver().addOnGlobalLayoutListener(new MyOnGlobalLayoutListener());

            //得到屏幕滑动的百分比
            viewpager.addOnPageChangeListener(new MyOnGlobalLayoutListener());
        }
    }
    class MyOnGlobalLayoutListener implements ViewTreeObserver.OnGlobalLayoutListener, ViewPager.OnPageChangeListener {
        @Override
        public void onGlobalLayout() {
            //执行不止一次
            iv_red_point.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            //间距 = 第1个点距离左边的距离 - 第0个点距离左边的距离
            leftmax = ll_point_group.getChildAt(1).getLeft() - ll_point_group.getChildAt(0).getLeft();
        }

        /**
         *
         * @param i 当前滑动页面的位置
         * @param v 页面滑动的百分比
         * @param i1 滑动的像素
         */
        @Override
        public void onPageScrolled(int i, float v, int i1) {
            //两点间滑动距离对应的坐标 = 原来的起始位置 + 两点间移动的距离
            int leftmargin = (int) ((i * leftmax) + (v * leftmax));
            //params.leftMargin = 两点间滑动距离对应的坐标
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) iv_red_point.getLayoutParams();
            iv_red_point.setLayoutParams(params);
            params.leftMargin = leftmargin;
            iv_red_point.setLayoutParams(params);
        }



        /**
         * 当页面被选中的时候回调这个方法
         * @param i 被选中页面的对应位置
         */
        @Override
        public void onPageSelected(int i) {
            if(i == imageViews.size()-1){
                //到最后一个页面显示按钮
                btn_start_main.setVisibility(View.VISIBLE);
            }
            else {
                //其他页面不显示
                btn_start_main.setVisibility(View.GONE);
            }
        }

        /**
         * 当ViewPager页面滑动状态发生变化的时候
         * @param i
         */
        @Override
        public void onPageScrollStateChanged(int i) {

        }
    }
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
         *判断是否为同一张图片
         * view     当前创建的视图
         * object   instantiateItem返回的结果值
         *
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


}
