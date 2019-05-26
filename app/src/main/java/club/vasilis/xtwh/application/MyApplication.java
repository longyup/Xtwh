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
    public static OkHttpClient client = new OkHttpClient.Builder()
            .readTimeout(2000L, TimeUnit.MILLISECONDS)
            .callTimeout(2000L, TimeUnit.MILLISECONDS)
            .build();

    @Override
    public void onCreate() {
        super.onCreate();

    }
}
