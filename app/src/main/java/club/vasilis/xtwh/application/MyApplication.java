package club.vasilis.xtwh.application;

import android.app.Application;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * @author Vasilis
 * @date 2019/5/26 * 19:16
 */
public class MyApplication extends Application {
    public static final String HOST = "http://120.76.62.29:8080/10_NULL";
    public static final String LOCAL= "http://localhost:8080/10_NULL_war_exploded/";

    public static OkHttpClient client = new OkHttpClient.Builder()
            .readTimeout(20000L, TimeUnit.MILLISECONDS)
            .callTimeout(20000L, TimeUnit.MILLISECONDS)
            .build();

    @Override
    public void onCreate() {
        super.onCreate();

    }
}
