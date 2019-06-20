package club.vasilis.xtwh.application;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.concurrent.TimeUnit;

import club.vasilis.xtwh.domain.User;
import okhttp3.OkHttpClient;

/**
 * @author Vasilis
 * @date 2019/5/26 * 19:16
 */
public class MyApplication extends Application {
    //
    public static User myUser;
    //   public static User myUser = new User("2017002442","null_demo","demo","demo","男","17326080001","17326080001@163.com","1998-1-1","个性签名demo","个性简历demo","杭州");
    public static final String HOST = "http://120.76.62.29:8080/10_NULL/";

    public static OkHttpClient client = new OkHttpClient.Builder()
            .readTimeout(20000L, TimeUnit.MILLISECONDS)
            .callTimeout(20000L, TimeUnit.MILLISECONDS)
            .build();


    @Override
    public void onCreate() {
        super.onCreate();

        // XFrame.initXLog()//初始化XLog
        //           .setTag("Test")//设置全局tag
        //        .setShowThreadInfo(true)//是否开启线程信息显示，默认true
        //      .setDebug(true);//是否显示日志，默认true，发布时最好关闭
        Logger.addLogAdapter(new AndroidLogAdapter());
    }
}
