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
import club.vasilis.xtwh.domain.Organization;

public class OrganizationActivity extends BaseActivity{
    private RecyclerView recyclerView;
    private List<Organization> Datas = new ArrayList<>();
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
        initdata();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_add_activity );
        recycleAdapter= new AddOrganizationAdapter(this,Datas);
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

    private void initdata(){
        Organization organization = new Organization(0,R.drawable.pic,"志愿余杭","浙江省， 杭州市， 富阳区","信用时数","组织人数");

        for (int i=0;i<20;i++){
            Datas.add(organization);
        }
    }

}
