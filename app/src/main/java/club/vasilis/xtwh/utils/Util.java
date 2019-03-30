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
    public static int getNowDate(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMdd");
        return Integer.parseInt(simpleDateFormat.format(date));
    }
    public static int getNowYear(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        return Integer.parseInt(simpleDateFormat.format(date));
    }
    public static User getUser(String uuid,List<User> userList){
        for (User user : userList) {
            if(user.getUuid().equals(uuid)){
                return user;
            }
        }
        return null;
    }

}
