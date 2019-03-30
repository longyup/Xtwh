package club.vasilis.xtwh.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import club.vasilis.xtwh.R;

/**
 * 测试用的MainActiity
 * 界面测试时均加入此集合后进行单独测试。
 */
public class TestActivity extends BaseActivity {
    private String[] name = {"首页", "社区","活动","个人信息"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ListView listView = findViewById(R.id.test_list_view);
        ListAdapter adapter = new ArrayAdapter<String>(this, R.layout.main_item, R.id.activity_name, name);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                   /* case 0: {
                        Intent intent = new Intent(getApplicationContext(), NetWorkBroadcastActivity.class);
                        startActivity(intent);
                        break;
                    }*/
                    case 1: {
                        Intent intent = new Intent(getApplicationContext(), CommunityActivity.class);
                        startActivity(intent);
                        break;
                    }/*
                    case 2: {
                        Intent intent = new Intent(getApplicationContext(), LocalBroadcastActivity.class);
                        startActivity(intent);
                        break;
                    }*/
                    default:
                        break;
                }
            }
        });
    }
}
