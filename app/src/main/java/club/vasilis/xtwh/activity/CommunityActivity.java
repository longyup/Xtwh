package club.vasilis.xtwh.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import club.vasilis.xtwh.R;
import club.vasilis.xtwh.adapter.CommunityAdapter;
import club.vasilis.xtwh.domain.Comment;
import club.vasilis.xtwh.domain.Community;
import club.vasilis.xtwh.domain.Phrase;
import club.vasilis.xtwh.domain.User;
import club.vasilis.xtwh.utils.Util;

public class CommunityActivity extends BaseActivity {

    private List<User> userList = new ArrayList<>();
    private List<Community> communityList = new ArrayList<>();
    private List<Phrase> phraseList = new ArrayList<>();
    private List<Comment> commentList = new ArrayList<>();

    private Button btnSendCommunity;

    private CommunityAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);
        // 初始化数据
        testInit();
        // 初始化控件
        RecyclerView recyclerView = findViewById(R.id.community_rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // 评论功能暂未实现
        adapter = new CommunityAdapter(communityList, userList);
        recyclerView.setAdapter(adapter);


        btnSendCommunity = findViewById(R.id.community_btn_send);
        // 点击按钮弹出对话框
        btnSendCommunity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            showDialog();
            }
        });

    }
    private void showDialog(){
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_send_community,null,false);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.show();
        final EditText etSend = view.findViewById(R.id.send_community_ed);
        Button btnSend = view.findViewById(R.id.send_community_btn);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                community.setPhraseNum(0);
                community.setUUID(myUser.getUuid());
                communityList.add(community);
                adapter.update(communityList);
                // 关闭对话框
                dialog.cancel();

            }
        });

    }
    private void testInit(){

        User admin = new User("admin", "admin", "admin");
        userList.add(admin);
        userList.add(myUser);
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

    }

    /**
     * 对每项的动态进行判断是否点赞
     *
     */
    private void setIsPhrase() {
        for (Community community : communityList) {
            for (Phrase phrase : phraseList) {
                if (phrase.getCommunityId() == community.getId()) {
                    if (phrase.getUuid().equals(myUser.getUuid())) {
                        community.setPhrase(true);
                    } else {
                        community.setPhrase(false);
                    }
                    break;
                }
            }
        }
    }
}
