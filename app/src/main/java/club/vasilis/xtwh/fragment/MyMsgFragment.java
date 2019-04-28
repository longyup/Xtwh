package club.vasilis.xtwh.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import club.vasilis.xtwh.R;
import club.vasilis.xtwh.activity.BaseActivity;
import club.vasilis.xtwh.activity.MyMsgPersonageActivity;
import club.vasilis.xtwh.adapter.MyMsgOtherAdapter;
import club.vasilis.xtwh.domain.MyMsgOther;
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

//    //初始化其他内容
//    private void initOther() {
//        int len = otherName.length;
//        for (int i = 0; i < len; i++){
//            MyMsgOther other = new MyMsgOther(otherName[i], R.drawable.main_listitem_arrow);
//            otherList.add(other);
//        }
//    }
}
