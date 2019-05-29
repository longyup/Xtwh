package club.vasilis.xtwh.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import butterknife.BindView;
import butterknife.ButterKnife;
import club.vasilis.xtwh.R;
import club.vasilis.xtwh.application.MyApplication;

public class ActivityDailsFragment extends Fragment {
    @BindView(R.id.iv_web_activity_dails)
    ImageView ivDailsImg;
    @BindView(R.id.tv_web_activity_dails_name)
    TextView tvDailsName;
    @BindView(R.id.tv_web_activity_dails_startTimeTime)
    TextView tvDailsStartTime;
    @BindView(R.id.tv_web_activity_dails_launchTime)
    TextView tvDailsLaunchTime;
    @BindView(R.id.tv_web_activity_dails_info)
    TextView tvDailsInfo;

    String url = MyApplication.HOST + "activity?method=getJsonActivityAll";

    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
       View view = LayoutInflater.from(getContext()).inflate(R.layout.show_web_activity_dails_list_data,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    public void refresh(String ivDails,String tvDailsNameText,String tvDailsStartTimeText,String tvDailsLaunchTimeText,String tvDailsInfoText){

                //刷新内容
                Glide.with(getContext()).load(ivDails).into(ivDailsImg);
                tvDailsName.setText(tvDailsNameText);
                tvDailsStartTime.setText(tvDailsStartTimeText);
                tvDailsLaunchTime.setText(tvDailsLaunchTimeText);
                tvDailsInfo.setText(tvDailsInfoText);

    }

}
