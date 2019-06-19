package club.vasilis.xtwh.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import club.vasilis.xtwh.domain.User;

/**
 * @author Vasilis
 * @date 2019/6/10 * 9:09
 */
public class MainActivityViewModel extends AndroidViewModel {
    private static final String TAG = "MainActivityViewModel";

    // 用户的账号信息
    public final MutableLiveData<User> userData = new MutableLiveData<>();

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
    }
}
