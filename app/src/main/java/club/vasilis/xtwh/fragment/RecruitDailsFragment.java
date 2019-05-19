package club.vasilis.xtwh.fragment;

import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.OnClick;
import club.vasilis.xtwh.R;
import club.vasilis.xtwh.R2;
import club.vasilis.xtwh.web.ShowActivityDails;

public class RecruitDailsFragment extends Fragment {


    private View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.recruit_dails_fragment,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @OnClick(R2.id.btn_recruit_back)
    public void onClick(){

    }

    public void refresh(int ivDailsId,String tvDailsTitleText,String tvDailsDistanceText,String tvDailsRegionText,String tvDailsNumberText){
        ImageView ivDails = view.findViewById(R.id.iv_recruit_dails);
        TextView tvDailsTitle = view.findViewById(R.id.tv_title_recruit_dails);
        TextView tvDailsDistance = view.findViewById(R.id.tv_distance_recruit_dails);
        TextView tvDailsRegion = view.findViewById(R.id.tv_region_recruit_dails);
        TextView tvDailsNumber = view.findViewById(R.id.tv_number_recruit_dails);

        Button btnDelete = view.findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(getContext(), ShowActivityDails.class);
                startActivity(intent);
            }
        });

        //刷新内容
        ivDails.setImageResource(ivDailsId);
        tvDailsTitle.setText(tvDailsTitleText);
        tvDailsDistance.setText(tvDailsDistanceText);
        tvDailsRegion.setText(tvDailsRegionText);
        tvDailsNumber.setText(tvDailsNumberText);

    }
}
