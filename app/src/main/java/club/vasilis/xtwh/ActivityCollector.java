package club.vasilis.xtwh;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vasilis
 * @date 2019/3/29 * 12:22
 */
public class ActivityCollector {
    private static List<Activity> activityList = new ArrayList<>();

    public static void addActivity(Activity activity) {
        activityList.add(activity);
    }
    public static void removeActivity(Activity activity) {
        activityList.remove(activity);
    }
    public static void finishAll(){
        for (Activity activity : activityList) {
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
        activityList.clear();
    }
}
