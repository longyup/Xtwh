package club.vasilis.xtwh.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import club.vasilis.xtwh.R;
import club.vasilis.xtwh.activity.MainActivity;

public class RecruitDailsFragment extends Fragment {
private View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.recruit_dails_fragment,container,false);
        ImageView btnBack = view.findViewById(R.id.btn_recruit_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);

            }
        });

        return view;
    }

    public void refresh(int ivDailsId,String tvDailsTitleText,String tvDailsDistanceText,String tvDailsRegionText,String tvDailsNumberText){


        ImageView ivDails = view.findViewById(R.id.iv_recruit_dails);
        TextView tvDailsTitle = view.findViewById(R.id.tv_title_recruit_dails);
        TextView tvDailsDistance = view.findViewById(R.id.tv_distance_recruit_dails);
        TextView tvDailsRegion = view.findViewById(R.id.tv_region_recruit_dails);
        TextView tvDailsNumber = view.findViewById(R.id.tv_number_recruit_dails);

        //刷新内容
        ivDails.setImageResource(ivDailsId);
        tvDailsTitle.setText(tvDailsTitleText);
        tvDailsDistance.setText(tvDailsDistanceText);
        tvDailsRegion.setText(tvDailsRegionText);
        tvDailsNumber.setText(tvDailsNumberText);

    }
}
