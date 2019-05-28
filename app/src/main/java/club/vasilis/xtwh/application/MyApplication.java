package club.vasilis.xtwh.application;

import android.app.Application;

import com.youth.xframe.XFrame;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * @author Vasilis
 * @date 2019/5/26 * 19:16
 */
public class MyApplication extends Application {
    public static final String HOST = "http://120.76.62.29:8080/10_NULL/";

    public static OkHttpClient client = new OkHttpClient.Builder()
            .readTimeout(20000L, TimeUnit.MILLISECONDS)
            .callTimeout(20000L, TimeUnit.MILLISECONDS)
            .build();


    @Override
    public void onCreate() {
        super.onCreate();

        XFrame.initXLog()//初始化XLog
                .setTag("Test")//设置全局tag
                .setShowThreadInfo(true)//是否开启线程信息显示，默认true
                .setDebug(true);//是否显示日志，默认true，发布时最好关闭
    }
}
