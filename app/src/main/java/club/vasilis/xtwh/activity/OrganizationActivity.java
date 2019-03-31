package club.vasilis.xtwh.activity;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import club.vasilis.xtwh.R;
import club.vasilis.xtwh.adapter.AddOrganizationAdapter;

public class OrganizationActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<String> mDatas;
    private List<String> regionDatas;
    private List<String> HourDatas;
    private List<String> PeoplenumberDatas;
    private AddOrganizationAdapter recycleAdapter;
    private ImageView back;

    @SuppressLint("NewApi") @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        back = findViewById(R.id.btn_add_activity_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //返回按键



            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_add_activity );

        initData();
        initRegionData();
        initHourData();
        initpeoplenumberData();
        recycleAdapter= new AddOrganizationAdapter(this,mDatas,regionDatas,HourDatas,PeoplenumberDatas);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置Adapter
        recyclerView.setAdapter( recycleAdapter);
        //设置增加或删除条目的动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    private void initData() {
        mDatas = new ArrayList<String>();
        for ( int i=0; i < 40; i++) {
            mDatas.add( "志愿余杭");
        }
    }

    private void initRegionData(){
        regionDatas = new ArrayList<String>();

        for ( int i=0; i < 40; i++) {
            regionDatas.add( "浙江省， 杭州市， 富阳区");
        }
    }

    private void initHourData(){
        HourDatas = new ArrayList<String>();

        for ( int i=0; i < 40; i++) {
            HourDatas.add( "信用时数");
        }
    }

    private void initpeoplenumberData(){
        PeoplenumberDatas = new ArrayList<String>();

        for ( int i=0; i < 40; i++) {
            PeoplenumberDatas.add( "组织人数");
        }
    }
}
