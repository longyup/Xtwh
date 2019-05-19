package club.vasilis.xtwh.fragment;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import club.vasilis.xtwh.R;
import club.vasilis.xtwh.adapter.RecruitActivityAdapter;
import club.vasilis.xtwh.domain.Recruit;

public class RecruitFragment extends Fragment {
    private List<Recruit> Datas = new ArrayList<>();
    private RecyclerView recuritRecyclerView;
    private ImageView recuritBack;
    private RecruitActivityAdapter recruitActivityAdapter;
    private SwipeRefreshLayout refreshLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recuit_frag,container,false);
        recuritRecyclerView = view.findViewById(R.id.recycler_view_Recruit_activity);

        initDatas();
        recruitActivityAdapter= new RecruitActivityAdapter(Datas,getContext());
        recuritRecyclerView.setAdapter(recruitActivityAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        //设置布局管理器
        recuritRecyclerView.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置Adapter
        recuritRecyclerView.setAdapter( recruitActivityAdapter);
        //设置增加或删除条目的动画
        recuritRecyclerView.setItemAnimator(new DefaultItemAnimator());

        refreshLayout = view.findViewById(R.id.recruit_swipRefreshlayout);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
        return view;
    }

    /**
     * 下拉刷新
     */
    private void refresh(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //应该进行网络请求
                    Thread.sleep(200);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //回到主线程刷新
                refreshLayout.post(new Runnable() {
                    @Override
                    public void run() {

                        recruitActivityAdapter.notifyDataSetChanged();
                        Toast.makeText(getContext(), "刷新成功", Toast.LENGTH_SHORT).show();
                        refreshLayout.setRefreshing(false);
                    }
                });

            }
        }).start();
    }

    private void initDatas() {
        Recruit recruit = new Recruit(0,R.drawable.th,"地球一小时幸福跑","29.63km","西湖区","9/20");
        Datas.add(recruit);
        recruit = new Recruit();
        recruit.setId(1);;
        recruit.setTitle("园区安全巡查");
        recruit.setImgId(R.drawable.recruit05);
        recruit.setDistance("30km");
        recruit.setRegion("西湖区");
        recruit.setNumber("1/20");
        Datas.add(recruit);

        recruit = new Recruit();
        recruit.setId(2);;
        recruit.setTitle("浙商国际中心区园区安全消防巡查");
        recruit.setImgId(R.drawable.recruit01);
        recruit.setDistance("40km");
        recruit.setRegion("富阳区");
        recruit.setNumber("9/20");
        Datas.add(recruit);

        recruit = new Recruit();
        recruit.setId(3);;
        recruit.setTitle("5.1无痕杭州");
        recruit.setImgId(R.drawable.recruit02);
        recruit.setDistance("60km");
        recruit.setRegion("西湖区");
        recruit.setNumber("9/20");
        Datas.add(recruit);

        recruit = new Recruit();
        recruit.setId(4);;
        recruit.setTitle("临安站点义剪+敬老活动");
        recruit.setImgId(R.drawable.recruit03);
        recruit.setDistance("30km");
        recruit.setRegion("临安区");
        recruit.setNumber("9/20");
        Datas.add(recruit);

        recruit = new Recruit();
        recruit.setId(5);;
        recruit.setTitle("兰园销控安全参观");
        recruit.setImgId(R.drawable.recruit04);
        recruit.setDistance("70km");
        recruit.setRegion("市辖区");
        recruit.setNumber("9/20");
        Datas.add(recruit);

    }


}
