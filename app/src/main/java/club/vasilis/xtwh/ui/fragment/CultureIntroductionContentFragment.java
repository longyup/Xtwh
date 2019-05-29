package club.vasilis.xtwh.ui.fragment;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import club.vasilis.xtwh.R;

/**
 * 文化详情页
 */
public class CultureIntroductionContentFragment extends Fragment {
    private View view;

    @BindView(R.id.tv_cultureIntroduction_content)
    TextView tvCultureIntroductionContent;

    @BindView(R.id.tb_cultureSites_infor)
    Toolbar tbCultureSitesInfor;


    /*@BindView(R.id.tv_cultureIntroduction_title)
    TextView tvCultureIntroductionTitle;*/

    private Unbinder unbinder;


    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_product_infor, container, false);
        unbinder = ButterKnife.bind(this, view);
        tvCultureIntroductionContent.setMovementMethod(ScrollingMovementMethod.getInstance());
        ((AppCompatActivity) getActivity()).setSupportActionBar(tbCultureSitesInfor);
        setHasOptionsMenu(true);
        return view;


    }

   /* @OnClick(R.id.btn_content_back)
    void show(){
        getActivity().finish();
    }*/

    public void refresh(String Title, String Content) {
        View ll_cultureIntroduction_visibility_layout = view.findViewById(R.id.ll_cultureIntroduction_visibility_layout);
        ll_cultureIntroduction_visibility_layout.setVisibility(View.VISIBLE);
        //刷新标题
        //tvCultureIntroductionTitle.setText(Title);
        tbCultureSitesInfor.setTitle(Title);
        //刷新内容
        tvCultureIntroductionContent.setText(Content);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.tb_culture_sites_infor_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.back:
                getActivity().finish();
                break;
                default:
        }
        return true;
    }
}
