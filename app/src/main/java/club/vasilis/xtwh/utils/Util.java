package club.vasilis.xtwh.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import club.vasilis.xtwh.domain.User;

/**
 * @author Vasilis
 * @date 2019/3/29 * 13:04
 */
public class Util {
    public static String getNowDate(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd");
        return simpleDateFormat.format(date);
    }
    public static String getNowYear(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        return simpleDateFormat.format(date);
    }
    public static String getNowTime(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        return simpleDateFormat.format(date);
    }
    public static User getUser(String uuid,List<User> userList){
        for (User user : userList) {
            if(user.getNickName().equals(uuid)){
                return user;
            }
        }
        return null;
    }

}
