package club.vasilis.xtwh.fragment;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import club.vasilis.xtwh.R;

/**
 * 文化详情页
 */
public class CultureIntroductionContentFragment extends Fragment {
    private View view;
    /*@BindView(R.id.btn_content_back)
    Button btnContentBack;*/

    @BindView(R.id.tv_cultureIntroduction_content)
    TextView tvCultureIntroductionContent;

    @BindView(R.id.tv_cultureIntroduction_title)
    TextView tvCultureIntroductionTitle;

    private Unbinder unbinder;


    /*private ImageView btn_content_back;

    private TextView tv_cultureIntroduction_content;*/

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {




        view = inflater.inflate(R.layout.culture_introduction_content_frag, container, false);
        unbinder = ButterKnife.bind(this, view);
        tvCultureIntroductionContent.setMovementMethod(ScrollingMovementMethod.getInstance());

        /*tv_cultureIntroduction_content = view.findViewById(R.id.tv_cultureIntroduction_content);
        tv_cultureIntroduction_content.setMovementMethod(ScrollingMovementMethod.getInstance());
        btn_content_back = view.findViewById(R.id.btn_content_back);
        btn_content_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
        return view;
    }

    @OnClick(R.id.btn_content_back)
    void show(){
        getActivity().finish();
    }

    public void refresh(String Title, String Content) {
        View ll_cultureIntroduction_visibility_layout = view.findViewById(R.id.ll_cultureIntroduction_visibility_layout);
        ll_cultureIntroduction_visibility_layout.setVisibility(View.VISIBLE);
        /*TextView tv_cultureIntroduction_title = view.findViewById(R.id.tv_cultureIntroduction_title);
        TextView tv_cultureIntroduction_content = view.findViewById(R.id.tv_cultureIntroduction_content);*/
        //刷新标题
        tvCultureIntroductionTitle.setText(Title);
        //刷新内容
        tvCultureIntroductionContent.setText(Content);
    }
}
