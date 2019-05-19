package club.vasilis.xtwh.fragment;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import club.vasilis.xtwh.R;
import club.vasilis.xtwh.activity.MyMsgPersonageActivity;
import club.vasilis.xtwh.adapter.MyMsgOtherAdapter;

/*
展示我的信息界面
 */
public class MyMsgFragment extends Fragment {

    private View view;
    private String[] otherTitle = {"我的组织","我的活动","我的帖子","我的收藏","我的积分","设置","在线咨询"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.my_msg_frag,container,false);

        //进入个人信息更改界面
        view.findViewById(R.id.my_msg_person).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyMsgPersonageActivity.class);
                startActivity(intent);
            }
        });


        //其他内容
//        initOther();//初始化其他内容数据
        RecyclerView rv_other = view.findViewById(R.id.my_msg_other_rv_content);
        rv_other.setLayoutManager(new LinearLayoutManager(getActivity()));
        MyMsgOtherAdapter otherAdapter = new MyMsgOtherAdapter(otherTitle);
        rv_other.setAdapter(otherAdapter);
        return view;
    }

}
