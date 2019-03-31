package club.vasilis.xtwh.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import club.vasilis.xtwh.R;
import club.vasilis.xtwh.adapter.MyMsgOtherAdapter;
import club.vasilis.xtwh.domain.MyMsgOther;

public class MyMsgActivity extends BaseActivity {

    private List<MyMsgOther> otherList = new ArrayList<>();

    private String[] otherName = {"我的组织","我的活动","我的帖子","我的收藏","我的积分","设置","在线咨询"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_msg);

        //进入个人信息更改界面
        findViewById(R.id.my_msg_person).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyMsgActivity.this, MyMsgPersonageActivity.class);
                startActivity(intent);
            }
        });

        //其他内容
        initOther();//初始化其他内容数据
        RecyclerView rv_other = findViewById(R.id.my_msg_other_rv_content);
        rv_other.setLayoutManager(new LinearLayoutManager(this));
        MyMsgOtherAdapter otherAdapter = new MyMsgOtherAdapter(otherList);
        rv_other.setAdapter(otherAdapter);
    }

    //初始化其他内容
    private void initOther() {
        int len = otherName.length;
        for (int i = 0; i < len; i++){
            MyMsgOther other = new MyMsgOther(otherName[i],R.drawable.main_listitem_arrow);
            otherList.add(other);
        }
    }
}
