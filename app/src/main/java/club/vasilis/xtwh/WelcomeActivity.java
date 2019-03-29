package club.vasilis.xtwh;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;


import club.vasilis.xtwh.activity.GuideActivity;
import club.vasilis.xtwh.activity.MainActivity;
import club.vasilis.xtwh.utils.CacheUtils;

public class WelcomeActivity extends Activity {
    //静态常量
    public static final String START_MAIN = "start_main";

    private RelativeLayout rl_welcome_root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        rl_welcome_root = findViewById(R.id.rl_welcome_root);

        //设置启动页的渐变，缩放，旋转动画

        //渐变动画从无到有
        AlphaAnimation aa= new AlphaAnimation(0,1);
        aa.setDuration(500);//设置持续时间
        aa.setFillAfter(true);

        //缩放动画
        ScaleAnimation sa = new ScaleAnimation(0,1,0,1,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        sa.setDuration(500);
        sa.setFillAfter(true);

        //旋转动画
        //如果pivotXType=Animation.RELATIVE_TO_SELF，则参数pivotXValue为旋转中心在控件自身水平位置百分比，如果X和Y的Value都设置为0.5，则该控件以自身中心旋转
        RotateAnimation ra = new RotateAnimation(0,360,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        ra.setDuration(500);
        //通过setFillAfter方法使对象保留在终止点
        ra.setFillAfter(true);

        //添加三个动画使三个动画同时播放
        AnimationSet set = new AnimationSet(false);//没有先后顺序
        set.addAnimation(aa);
        set.addAnimation(sa);
        set.addAnimation(ra);

        set.setDuration(2000);//更改持续时间可覆盖之前的持续时间

        rl_welcome_root.setAnimation(set);

        //设置动画监听器
        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            //当动画播放完成时回调此方法
            @Override
            public void onAnimationEnd(Animation animation) {

                //判断是否进入过主页面
                boolean isStartMain = CacheUtils.getBoolean(WelcomeActivity.this, START_MAIN);
                Intent intent;
                if (isStartMain){
                    //如果进入过主页面，直接进入主页面
                    intent = new Intent(WelcomeActivity.this, MainActivity.class);
                }else {
                    //如果没有，进入引导页面
                    intent = new Intent(WelcomeActivity.this,GuideActivity.class);

                }

                startActivity(intent);
                //关闭Welcome页面
                finish();


                //Toast.makeText(WelcomeActivity.this, "动画播放完成", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
