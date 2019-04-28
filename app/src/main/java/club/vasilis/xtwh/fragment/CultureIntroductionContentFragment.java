package club.vasilis.xtwh.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import club.vasilis.xtwh.R;

/**
 * 文化详情页
 */
public class CultureIntroductionContentFragment extends Fragment {

    private View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.culture_introduction_content_frag,container,false);
        return view;
    }
    public void refresh(String Title,String Content){
        View ll_cultureIntroduction_visibility_layout = view.findViewById(R.id.ll_cultureIntroduction_visibility_layout);
        ll_cultureIntroduction_visibility_layout.setVisibility(View.VISIBLE);
        TextView tv_cultureIntroduction_title = view.findViewById(R.id.tv_cultureIntroduction_title);
        TextView tv_cultureIntroduction_content = view.findViewById(R.id.tv_cultureIntroduction_content);
        //刷新标题
        tv_cultureIntroduction_title.setText(Title);
        //刷新内容
        tv_cultureIntroduction_content.setText(Content);
    }
}