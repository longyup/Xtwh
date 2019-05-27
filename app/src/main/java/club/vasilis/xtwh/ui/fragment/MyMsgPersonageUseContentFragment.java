package club.vasilis.xtwh.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import club.vasilis.xtwh.R;
import club.vasilis.xtwh.adapter.MyMsgPersonageUserAdapter;

/*
我的信息-->个人基本信息
 */
public class MyMsgPersonageUseContentFragment extends Fragment {

    private String[] userContextTitle = {"头像","昵称","姓名","性别","手机号","电子邮件","生日","个性签名","个人简介","常驻居住地"};
    private TextView tv_accountId = null;

    private Unbinder unbinder;

    private View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.my_msg_personage_user_frag,container,false);
//        tv_accountId = view.findViewById(R.id.my_msg_personage_account_id);
//        tv_accountId.setText(BaseActivity.myUser.getAccount());//显示账户信息
        unbinder = ButterKnife.bind(this,view);

        RecyclerView rv_userContent = view.findViewById(R.id.my_msg_personage_user_rv_content);
        rv_userContent.setLayoutManager(new LinearLayoutManager(getActivity()));
        MyMsgPersonageUserAdapter userAdapter = new MyMsgPersonageUserAdapter(userContextTitle);
        rv_userContent.setAdapter(userAdapter);
        return view;
    }

}
