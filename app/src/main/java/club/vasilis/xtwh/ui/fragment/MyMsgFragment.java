package club.vasilis.xtwh.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import club.vasilis.xtwh.R;
import club.vasilis.xtwh.adapter.MyMsgOtherAdapter;
import club.vasilis.xtwh.application.MyApplication;
import club.vasilis.xtwh.ui.activity.LoginActivity;
import club.vasilis.xtwh.ui.activity.MyMsgPersonageActivity;

/*
展示我的信息界面
 */
public class MyMsgFragment extends Fragment {

    @BindView(R.id.my_msg_person)
    LinearLayout myMsgPerson;
    @BindView(R.id.my_msg_person_unlogin)
    LinearLayout myMsgPersonUnlogin;

    private Unbinder unbinder;
    private View view;
    private String[] otherTitle = {"我的组织", "我的活动", "我的帖子", "我的收藏", "我的积分", "设置", "在线咨询"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my_msg, container, false);
        unbinder = ButterKnife.bind(this, view);

        if (MyApplication.myUser != null) {
            myMsgPerson.setVisibility(View.VISIBLE);
            myMsgPersonUnlogin.setVisibility(View.GONE);
        }else {
            myMsgPerson.setVisibility(View.GONE);
            myMsgPersonUnlogin.setVisibility(View.VISIBLE);
        }


        //其他内容
//        initOther();//初始化其他内容数据
        RecyclerView rv_other = view.findViewById(R.id.my_msg_other_rv_content);
        rv_other.setLayoutManager(new LinearLayoutManager(getActivity()));
        MyMsgOtherAdapter otherAdapter = new MyMsgOtherAdapter(otherTitle);
        rv_other.setAdapter(otherAdapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (MyApplication.myUser != null) {
            myMsgPerson.setVisibility(View.VISIBLE);
            myMsgPersonUnlogin.setVisibility(View.GONE);
        }else {
            myMsgPerson.setVisibility(View.GONE);
            myMsgPersonUnlogin.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    //登录的判断
    @OnClick({R.id.my_msg_person, R.id.my_msg_person_unlogin})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.my_msg_person: {
                Intent intent = new Intent(getActivity(), MyMsgPersonageActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.my_msg_person_unlogin: {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
            break;
        }
    }

}
