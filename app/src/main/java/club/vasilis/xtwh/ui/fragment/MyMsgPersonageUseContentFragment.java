package club.vasilis.xtwh.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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
import club.vasilis.xtwh.adapter.MyMsgPersonageUserAdapter;
import club.vasilis.xtwh.application.MyApplication;

/*
我的信息-->个人基本信息
 */
public class MyMsgPersonageUseContentFragment extends Fragment {

    private Context context;

    @BindView(R.id.my_msg_personage_account_id)
    TextView myMsgPersonageAccountId;
    private String[] userContextTitle = {"头像", "昵称", "姓名", "性别", "手机号", "电子邮件", "生日", "个性签名", "身份证", "常住地", "修改密码"};

    private Unbinder unbinder;

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my_msg_personage_user, container, false);
//        tv_accountId = view.findViewById(R.id.my_msg_personage_account_id);
//        tv_accountId.setText(BaseActivity.myUser.getAccount());//显示账户信息
        unbinder = ButterKnife.bind(this, view);

        myMsgPersonageAccountId.setText(MyApplication.myUser.getAccount());
        RecyclerView rv_userContent = view.findViewById(R.id.my_msg_personage_user_rv_content);
        rv_userContent.setLayoutManager(new LinearLayoutManager(getActivity()));
        MyMsgPersonageUserAdapter userAdapter = new MyMsgPersonageUserAdapter(userContextTitle);
        rv_userContent.setAdapter(userAdapter);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick(R.id.exit)
    public void onViewClicked() {
        Toast.makeText(context,"退出登录...",Toast.LENGTH_SHORT).show();
        MyApplication.myUser = null;
        getActivity().finish();
    }
}
