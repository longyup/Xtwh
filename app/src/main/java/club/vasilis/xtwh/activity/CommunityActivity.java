package club.vasilis.xtwh.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import club.vasilis.xtwh.R;
import club.vasilis.xtwh.adapter.CommunityAdapter;
import club.vasilis.xtwh.domain.Comment;
import club.vasilis.xtwh.domain.Community;
import club.vasilis.xtwh.domain.Phrase;
import club.vasilis.xtwh.domain.User;

public class CommunityActivity extends BaseActivity {
    private List<User> userList = new ArrayList<>();
    private List<Community> communityList = new ArrayList<>();
    private List<Phrase> phraseList = new ArrayList<>();
    private List<Comment> commentList = new ArrayList<>();
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
        CommunityAdapter adapter = new CommunityAdapter(communityList, userList);
        recyclerView.setAdapter(adapter);
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
    private void setIsPhrase(){
        for (Community community : communityList) {
            for (Phrase phrase : phraseList) {
                if (phrase.getCommunityId() == community.getId()){
                    if (phrase.getUuid().equals(myUser.getUuid())){
                        community.setPhrase(true);
                    }else {
                        community.setPhrase(false);
                    }
                    break;
                }
            }
        }
    }
}
