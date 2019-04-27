package club.vasilis.xtwh.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import club.vasilis.xtwh.R;
import club.vasilis.xtwh.activity.CultureIntroductionContentActivity;
import club.vasilis.xtwh.domain.CultureIntroduction;

/**
 * 首页
 */
public class CultureIntroductionTitleFragment extends Fragment implements View.OnClickListener {

    private ViewPager vp_main;
    private ArrayList<ImageView> imageViews;
    private LinearLayout ll_creatAcity;
    private LinearLayout ll_joinClub;
    private LinearLayout ll_aroundThePublic;
    private LinearLayout ll_cultureIntroduction;

    private List<CultureIntroduction> CultureMainTitle = new ArrayList<>();
    private List<CultureIntroduction> mCultureIntroductionList = new ArrayList<>();
    private CultureIntroductionAdapter adapter;
    private RecyclerView cultureIntroductionTitleRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //垂直滚动
        View view = inflater.inflate(R.layout.culture_introduction_title_frag, container, false);
        cultureIntroductionTitleRecyclerView = view.findViewById(R.id.rv_culture_introduction_title);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        cultureIntroductionTitleRecyclerView.setLayoutManager(layoutManager);
        adapter = new CultureIntroductionAdapter(getCultureIntroduction());
        cultureIntroductionTitleRecyclerView.setAdapter(adapter);

        //水平滚动
        initCultueMainTitle();
        RecyclerView recyclerView = view.findViewById(R.id.rv_main_culture_introduction);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getActivity());
        layoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager1);
        CultureIntroductionAdapter1 adapter1 = new CultureIntroductionAdapter1(CultureMainTitle);
        recyclerView.setAdapter(adapter1);

        vp_main = view.findViewById(R.id.vp_main);
        ll_creatAcity = view.findViewById(R.id.ll_creatAcity);
        ll_joinClub = view.findViewById(R.id.ll_joinClub);
        ll_aroundThePublic = view.findViewById(R.id.ll_aroundThePublic);
        ll_cultureIntroduction = view.findViewById(R.id.ll_cultureIntroduction);
        ll_creatAcity.setOnClickListener(this);
        ll_joinClub.setOnClickListener(this);
        ll_aroundThePublic.setOnClickListener(this);
        ll_cultureIntroduction.setOnClickListener(this);

        //在ViewPager的初始化之后发送消息
        mHandler.sendEmptyMessageDelayed(0, 1000*2);

        //准备数据
        int[] ids = new int[]{
                R.drawable.main1,
                R.drawable.main2,
                R.drawable.main3
        };

        imageViews = new ArrayList<>();
        for (int i = 0;i<ids.length;i++){
            ImageView imageView = new ImageView(getContext());
            //设置背景
            imageView.setBackgroundResource(ids[i]);

            //添加到集合中去
            imageViews.add(imageView);



            //设置viewpage的适配器
            vp_main.setAdapter(new MyPagerAdapter());


        }

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }


    //CultureIntroduction的适配器
    class CultureIntroductionAdapter extends RecyclerView.Adapter<CultureIntroductionAdapter.ViewHolder> {


        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.culture_introduction_item, viewGroup, false);
            final ViewHolder holder = new ViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CultureIntroduction cultureIntroductions = mCultureIntroductionList.get(holder.getAdapterPosition());
                    CultureIntroductionContentActivity.actionStart(getActivity(), cultureIntroductions.getTitle(), cultureIntroductions.getContent());
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            CultureIntroduction cultureIntroductions = mCultureIntroductionList.get(i);
            viewHolder.tv_culture_introduction_title.setText(cultureIntroductions.getTitle());
        }

        @Override
        public int getItemCount() {
            return mCultureIntroductionList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            TextView tv_culture_introduction_title;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                tv_culture_introduction_title = itemView.findViewById(R.id.tv_culture_introduction_title);
            }
        }

        public CultureIntroductionAdapter(List<CultureIntroduction> CultureIntroductionList) {
            mCultureIntroductionList = CultureIntroductionList;
        }
    }

    //文化遗产数据
    private List<CultureIntroduction> getCultureIntroduction() {

        for (int i = 1; i <= 5; i++) {
            CultureIntroduction cultureIntroduction = new CultureIntroduction();
            cultureIntroduction.setTitle("文化遗产" + i);
            cultureIntroduction.setContent(getRandomLengthContent1("文化遗产" + i + "."));
            mCultureIntroductionList.add(cultureIntroduction);
        }
        return mCultureIntroductionList;
    }

    private String getRandomLengthContent1(String content) {
        Random random = new Random();
        int length = random.nextInt(20) + 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(content);

        }
        return builder.toString();
    }

    //特色美食
    private List<CultureIntroduction> getCultureIntroduction2() {
        List<CultureIntroduction> mCultureIntroductionList = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            CultureIntroduction cultureIntroduction = new CultureIntroduction();
            cultureIntroduction.setTitle("特色美食 " + i);
            cultureIntroduction.setContent(getRandomLengthContent2("特色美食" + i + "."));
            mCultureIntroductionList.add(cultureIntroduction);
        }
        return mCultureIntroductionList;
    }

    private String getRandomLengthContent2(String content) {
        Random random = new Random();
        int length = random.nextInt(20) + 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(content);

        }
        return builder.toString();
    }

    //名人趣事
    private List<CultureIntroduction> getCultureIntroduction3() {
        List<CultureIntroduction> mCultureIntroductionList = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            CultureIntroduction cultureIntroduction = new CultureIntroduction();
            cultureIntroduction.setTitle("名人趣事 " + i);
            cultureIntroduction.setContent(getRandomLengthContent3("名人趣事" + i + "."));
            mCultureIntroductionList.add(cultureIntroduction);
        }
        return mCultureIntroductionList;
    }

    private String getRandomLengthContent3(String content) {
        Random random = new Random();
        int length = random.nextInt(20) + 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(content);

        }
        return builder.toString();
    }

    //民俗风情
    private List<CultureIntroduction> getCultureIntroduction4() {
        List<CultureIntroduction> mCultureIntroductionList = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            CultureIntroduction cultureIntroduction = new CultureIntroduction();
            cultureIntroduction.setTitle("民俗风情 " + i);
            cultureIntroduction.setContent(getRandomLengthContent2("民俗风情" + i + "."));
            mCultureIntroductionList.add(cultureIntroduction);
        }
        return mCultureIntroductionList;
    }

    private String getRandomLengthContent4(String content) {
        Random random = new Random();
        int length = random.nextInt(20) + 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(content);

        }
        return builder.toString();
    }


    //设置水平滚动适配器

    class CultureIntroductionAdapter1 extends RecyclerView.Adapter<CultureIntroductionAdapter1.ViewHolder> {

        private List<CultureIntroduction> mCultureIntroductionMain;


        @NonNull
        @Override
        public CultureIntroductionAdapter1.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.culture_introduction_main_item, viewGroup, false);
            final ViewHolder holder = new ViewHolder(view);
            holder.titleView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int positon = holder.getAdapterPosition();
                    if (positon == 0) {
                        mCultureIntroductionList.clear();
                        adapter.notifyDataSetChanged();
                        adapter = new CultureIntroductionAdapter(getCultureIntroduction());
                        cultureIntroductionTitleRecyclerView.setAdapter(adapter);

                    }
                    if (positon == 1) {
                        mCultureIntroductionList.clear();
                        adapter.notifyDataSetChanged();
                        adapter = new CultureIntroductionAdapter(getCultureIntroduction2());
                        cultureIntroductionTitleRecyclerView.setAdapter(adapter);
                    }

                    if (positon == 2) {
                        mCultureIntroductionList.clear();
                        adapter.notifyDataSetChanged();
                        adapter = new CultureIntroductionAdapter(getCultureIntroduction3());
                        cultureIntroductionTitleRecyclerView.setAdapter(adapter);
                    }

                    if (positon == 3) {
                        mCultureIntroductionList.clear();
                        adapter.notifyDataSetChanged();
                        adapter = new CultureIntroductionAdapter(getCultureIntroduction4());
                        cultureIntroductionTitleRecyclerView.setAdapter(adapter);
                    }
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull CultureIntroductionAdapter1.ViewHolder viewHolder, int i) {
            CultureIntroduction cultureIntroduction = mCultureIntroductionMain.get(i);
            viewHolder.TitleNameMain.setText(cultureIntroduction.getTitle());
        }

        @Override
        public int getItemCount() {
            return mCultureIntroductionMain.size();
        }

        public CultureIntroductionAdapter1(List<CultureIntroduction> cultureIntroductionMainList) {
            mCultureIntroductionMain = cultureIntroductionMainList;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            TextView TitleNameMain;
            View titleView;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                TitleNameMain = itemView.findViewById(R.id.tv_culture_introduction_main_title);
                titleView = itemView;
            }
        }

    }


    //设置文化介绍初始化数据
    private void initCultueMainTitle() {
        CultureIntroduction c1 = new CultureIntroduction("文化遗产", "文化遗产文化遗产");
        CultureMainTitle.add(c1);
        CultureIntroduction c2 = new CultureIntroduction("特色美食", "特色美食特色美食");
        CultureMainTitle.add(c2);
        CultureIntroduction c3 = new CultureIntroduction("名人趣事", "名人趣事名人趣事");
        CultureMainTitle.add(c3);
        CultureIntroduction c4 = new CultureIntroduction("民俗风情", "民俗风情民俗风情");
        CultureMainTitle.add(c4);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_creatAcity:

                Toast.makeText(getContext(), "活动招募", Toast.LENGTH_SHORT).show();
                break ;
            case R.id.ll_joinClub:
                Toast.makeText(getContext(), "加入组织", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_aroundThePublic:
                Toast.makeText(getContext(), "身边公益", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_cultureIntroduction:
                Toast.makeText(getContext(), "文化介绍", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    //vp_main适配器
    class MyPagerAdapter extends PagerAdapter {

        //返回数据的总个数
        @Override
        public int getCount() {
            return imageViews.size();
        }

        /**
         * 添加控件，添加内容
         * container ViewPager
         * position  要创建页面的位置
         */

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            ImageView imageView = imageViews.get(position);
            //添加到容器中
            container.addView(imageView);

            return imageView;
        }

        /**
         * 判断是否为同一张图片
         * view     当前创建的视图
         * object   instantiateItem返回的结果值
         */

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }

        //destroyItem（）是加入页面的时候，默认缓存三个，如不做处理，滑多了程序就会崩
        //因为它默认是看三张图片，第四张图片的时候就会报错，还有就是不要返回父类的作用

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }

    }

    //自动刷新
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            MyPagerAdapter myPagerAdapter = new MyPagerAdapter();
            int count = myPagerAdapter.getCount();
            int index= vp_main.getCurrentItem();
            index=(index+1)%count;
            vp_main.setCurrentItem(index);    //收到消息后设置viewPager当前要显示的图片
            mHandler.sendEmptyMessageDelayed(0, 1000*2);    //第一个参数随便写；第二个参数表示每两秒刷新一次
        }
    };
}
