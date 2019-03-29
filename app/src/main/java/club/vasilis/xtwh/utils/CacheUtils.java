package club.vasilis.xtwh.utils;

import android.content.Context;
import android.content.SharedPreferences;



/*
*作用：缓存软件的一些参数和数据
*
*/
public class CacheUtils {
    public static boolean getBoolean(Context context ,String key){
        //得到缓存值
        SharedPreferences sp = context.getSharedPreferences("qishi",Context.MODE_PRIVATE);
        return sp.getBoolean(key,false);
    }

    /**
     * 保存软件的参数
     * @param context
     * @param key
     * @param value
     */
    public static void putBoolean(Context context, String key, boolean value) {
        SharedPreferences sp = context.getSharedPreferences("qishi",Context.MODE_PRIVATE);
        sp.edit().putBoolean(key,value).commit();
    }
}
