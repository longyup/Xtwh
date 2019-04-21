package club.vasilis.xtwh.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import club.vasilis.xtwh.R;
import club.vasilis.xtwh.adapter.AddOrganizationAdapter;
import club.vasilis.xtwh.domain.Organization;

public class OrganizationFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<Organization> Datas = new ArrayList<>();
    private AddOrganizationAdapter recycleAdapter;
    private ImageView back;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.organization_frag,container,false);

        back = view.findViewById(R.id.btn_add_activity_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //返回按键



            }
        });
        initdata();
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_add_activity );
        recycleAdapter= new AddOrganizationAdapter(getContext(),Datas);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置Adapter
        recyclerView.setAdapter( recycleAdapter);
        //设置增加或删除条目的动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        return view;
    }


    private void initdata(){
        Organization organization = new Organization(0,R.drawable.pic,"志愿余杭","浙江省， 杭州市， 富阳区","信用时数","组织人数");

        for (int i=0;i<20;i++){
            Datas.add(organization);
        }
    }

}
