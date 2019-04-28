package club.vasilis.xtwh.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 禁止了滑动事件
 * @author Vasilis
 * @date 2019/4/27 * 21:08
 */
public class CustomViewPager extends ViewPager {

    //是否可以进行滑动
    private boolean isSlide = false;

    public CustomViewPager(@NonNull Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setSlide(boolean slide) {
        isSlide = slide;
    }
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (isSlide) {
            return super.onTouchEvent(ev);
        } else {
            return false;
        }

    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (isSlide) {
            return super.onInterceptTouchEvent(ev);
        } else {
            return false;
        }

    }


}
