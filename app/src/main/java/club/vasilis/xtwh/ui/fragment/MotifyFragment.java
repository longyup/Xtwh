package club.vasilis.xtwh.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import club.vasilis.xtwh.R;
import club.vasilis.xtwh.application.MyApplication;

public class MotifyFragment extends Fragment {

    @BindView(R.id.motify_et_nick_name)
    EditText motifyEtNickName;
    @BindView(R.id.motify_et_name)
    EditText motifyEtName;
    @BindView(R.id.motify_et_pwd)
    EditText motifyEtPwd;
    @BindView(R.id.motify_et_sex)
    EditText motifyEtSex;
    @BindView(R.id.motify_et_phone)
    EditText motifyEtPhone;
    @BindView(R.id.motify_et_mail)
    EditText motifyEtMail;
    @BindView(R.id.motify_et_birthday)
    EditText motifyEtBirthday;
    @BindView(R.id.motify_et_signature)
    EditText motifyEtSignature;
    @BindView(R.id.motify_et_place)
    EditText motifyEtPlace;
    @BindView(R.id.motify_et_id_card)
    EditText motifyEtIdCard;
    private View view;
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_motify, container, false);
        unbinder = ButterKnife.bind(this,view);
        initEdit();
        return view;
    }

    private void initEdit() {
        motifyEtNickName.setText(MyApplication.myUser.getNickName());

    }


    @OnClick(R.id.motify_btn_ok)
    public void onClick() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
