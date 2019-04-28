package club.vasilis.xtwh.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import club.vasilis.xtwh.R;
import club.vasilis.xtwh.activity.BaseActivity;
import club.vasilis.xtwh.adapter.MyMsgPersonageUserAdapter;
import club.vasilis.xtwh.domain.MyMsgUserContent;

/*
我的信息-->个人基本信息
 */
public class MyMsgPersonageUseContentFragment extends Fragment {

    private String[] userContextTitle = {"头像","昵称","姓名","性别","手机号","电子邮件","生日","个性签名","个人简介","常驻居住地"};

    private View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.my_msg_personage_user_frag,container,false);
        RecyclerView rv_userContent = view.findViewById(R.id.my_msg_personage_user_rv_content);
        rv_userContent.setLayoutManager(new LinearLayoutManager(getActivity()));
        MyMsgPersonageUserAdapter userAdapter = new MyMsgPersonageUserAdapter(getUserContent());
        rv_userContent.setAdapter(userAdapter);
        return view;
    }

        private List<MyMsgUserContent> getUserContent(){
            List<MyMsgUserContent> userContentList = new ArrayList<>();
            int len = userContextTitle.length;
            for (int i = 0; i < len; i++){
                MyMsgUserContent userContent = new MyMsgUserContent(userContextTitle[i],R.drawable.main_listitem_arrow);
                userContentList.add(userContent);
            }

            return userContentList;
    }
}
