package club.vasilis.xtwh.activity;

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
import club.vasilis.xtwh.adapter.RecruitActivityAdapter;
import club.vasilis.xtwh.domain.Recruit;

public class RecruitActivity extends AppCompatActivity {
    private List<Recruit> Datas = new ArrayList<>();
//    private List<String> distanceDatas = new ArrayList<>();
//    private List<String> regionActivityDatas = new ArrayList<>();
//    private List<String> numberDatas = new ArrayList<>();


//    private String[] titleData ={"地球一小时幸福跑","地球一小时幸福跑","地球一小时幸福跑","地球一小时幸福跑","地球一小时幸福跑","地球一小时幸福跑",
//            "地球一小时幸福跑","地球一小时幸福跑","地球一小时幸福跑","地球一小时幸福跑","地球一小时幸福跑",};
//    private String[] distanceData = {"29.63km","29.63km","29.63km","29.63km","29.63km","29.63km","29.63km","29.63km","29.63km","29.63km","29.63km"};
//    private String[] regionData = {"西湖区","西湖区","西湖区","西湖区","西湖区","西湖区","西湖区","西湖区","西湖区","西湖区","西湖区",};
//    private String[] numberData = {"8/20","8/20","8/20","8/20","8/20","8/20","8/20","8/20","8/20","8/20","8/20",};

    private RecyclerView recuritRecyclerView;
    private ImageView recuritBack;

    private RecruitActivityAdapter recruitActivityAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruit);

        recuritRecyclerView = findViewById(R.id.recycler_view_Recruit_activity);
        recuritBack = findViewById(R.id.btn_recruit_back);
        recuritBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //返回点击


            }
        });

        initDatas();
        recruitActivityAdapter= new RecruitActivityAdapter(Datas,this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置布局管理器
        recuritRecyclerView.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置Adapter
        recuritRecyclerView.setAdapter( recruitActivityAdapter);
        //设置增加或删除条目的动画
        recuritRecyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    private void initDatas() {
        Recruit recruit = new Recruit(0,R.drawable.th,"地球一小时幸福跑","29.63km","西湖区","9/20");

        for (int i=0;i<20;i++){
            Datas.add(recruit);
        }



    }


}
