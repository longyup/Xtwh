package club.vasilis.xtwh.ui.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import club.vasilis.xtwh.R;
import club.vasilis.xtwh.adapter.CommunityAdapter;
import club.vasilis.xtwh.application.MyApplication;
import club.vasilis.xtwh.domain.Comment;
import club.vasilis.xtwh.domain.Community;
import club.vasilis.xtwh.domain.Phrase;
import club.vasilis.xtwh.domain.User;
import club.vasilis.xtwh.listener.OnItemClickListener;
import club.vasilis.xtwh.ui.activity.BaseActivity;
import club.vasilis.xtwh.utils.Util;

/**
 * 社区页的fragment
 *
 * @author Vasilis
 * @date 2019/4/25 * 12:49
 */
public class CommunityFragment extends Fragment implements OnRefreshListener, OnLoadMoreListener, OnItemClickListener {


    private List<User> userList = new ArrayList<>();
    private List<Community> communityList = new ArrayList<>();
    private List<Phrase> phraseList = new ArrayList<>();
    private List<Comment> commentList = new ArrayList<>();

    @BindView(R.id.community_fab_send)
    FloatingActionButton fabSend;

    @BindView(R.id.community_refreshLayout)
    RefreshLayout refreshLayout;

    private CommunityAdapter adapter;
    private Unbinder unbinder;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_community, container, false);
        unbinder = ButterKnife.bind(this, view);

        // 初始化数据,后期会换成网络
        testInit();
        // 对每项的动态进行判断是否点赞，放在json数据解析后
        setIsPhrase();
        // 初始化控件
        RecyclerView recyclerView = view.findViewById(R.id.community_rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        // 评论功能暂未实现
        adapter = new CommunityAdapter(communityList, userList);
        recyclerView.setAdapter(adapter);


        refreshLayout.setRefreshHeader(new ClassicsHeader(getContext()));
        refreshLayout.setRefreshFooter(new ClassicsFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));

        return view;
    }


    private void showDialog() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_send_community, null, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.show();
        final EditText etSend = view.findViewById(R.id.send_community_ed);
        Button btnSend = view.findViewById(R.id.send_community_btn);
        btnSend.setOnClickListener((v)-> {
                String text = etSend.getText().toString();
                Community community = new Community();
                community.setContent(text);
                // 获取时间
                String date = Util.getNowDate();
                String time = Util.getNowTime();
                StringBuilder sb = new StringBuilder();
                sb.append(date)
                        .append(" ")
                        .append(time);
                community.setDate(sb.toString());
                community.setId(2);
                community.setPhrase(false);
                community.setPhraseNum(0);
                community.setUUID(MyApplication.myUser.getNickName());
                adapter.update(community);
                // 关闭对话框
                dialog.cancel();

            }
        );

    }

    private void testInit() {
        communityList.clear();
        User admin = new User("123456789", "admin", "admin");
        userList.add(admin);
        userList.add(MyApplication.myUser);
        Community community = new Community();
        community.setContent("测试测试啊实打实的");
        community.setDate("03.30 13:52");
        community.setId(1);
        community.setPhraseNum(2);
        community.setUUID("admin");
        communityList.add(community);

        community = new Community();
        community.setContent("1111测试测试啊实打实的");
        community.setDate("03.30 13:56");
        community.setId(2);
        community.setPhraseNum(0);
        community.setUUID("demo");
        communityList.add(community);

        community = new Community();
        community.setContent("1111测试测1试啊实打实的");
        community.setDate("04.01 13:56");
        community.setId(3);
        community.setPhraseNum(0);
        community.setUUID("demo");
        communityList.add(community);

        community = new Community();
        community.setContent("1111测试测1试啊实打实的");
        community.setDate("04.10 13:56");
        community.setId(4);
        community.setPhraseNum(0);
        community.setUUID("demo");
        communityList.add(community);


        community = new Community();
        community.setContent("22测试测试啊实打实的");
        community.setDate("04.11 13:56");
        community.setId(5);
        community.setPhraseNum(0);
        community.setUUID("demo");
        communityList.add(community);

        community = new Community();
        community.setContent("33测试测试啊实打实的");
        community.setDate("04.14 13:56");
        community.setId(6);
        community.setPhraseNum(0);
        community.setUUID("demo");
        communityList.add(community);


        community = new Community();
        community.setContent("55测试测试啊实打实的");
        community.setDate("04.21 13:56");
        community.setId(7);
        community.setPhraseNum(0);
        community.setUUID("demo");
        communityList.add(community);

        community = new Community();
        community.setContent("66测试测试啊实打实的");
        community.setDate("04.25 13:56");
        community.setId(8);
        community.setPhraseNum(11);
        community.setUUID("demo");
        communityList.add(community);

        community = new Community();
        community.setContent("20测试测试啊实打实的");
        community.setDate("04.27 13:56");
        community.setId(9);
        community.setPhraseNum(6);
        community.setUUID("demo");
        communityList.add(community);


    }

    /**
     * 对每项的动态进行判断是否点赞
     */
    private void setIsPhrase() {
        for (Community community : communityList) {
            for (Phrase phrase : phraseList) {
                if (phrase.getCommunityId() == community.getId()) {
                    if (phrase.getUuid().equals(MyApplication.myUser.getNickName())) {
                        community.setPhrase(true);
                    } else {
                        community.setPhrase(false);
                    }
                    break;
                }
            }
        }
    }

    /**
     * 下拉刷新
     *
     * @param refreshLayout
     */
    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        refreshLayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
    }

    /**
     * 上拉加载
     *
     * @param refreshLayout
     */
    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        refreshLayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v, int position) {

    }

    @OnClick(R.id.community_fab_send)
    public void onViewClicked() {
        showDialog();
    }
}
