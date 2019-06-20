package club.vasilis.xtwh.ui.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import club.vasilis.xtwh.R;
import club.vasilis.xtwh.adapter.CommunityAdapter;
import club.vasilis.xtwh.application.MyApplication;
import club.vasilis.xtwh.domain.Community;
import club.vasilis.xtwh.domain.Phrase;
import club.vasilis.xtwh.listener.OnItemClickListener;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 社区页的fragment
 *
 * @author Vasilis
 * @date 2019/4/25 * 12:49
 */
public class CommunityFragment extends Fragment implements OnRefreshListener, OnLoadMoreListener, OnItemClickListener {
    private List<Community> communityList = new ArrayList<>();

    @BindView(R.id.community_fab_send)
    FloatingActionButton fabSend;

    @BindView(R.id.community_refreshLayout)
    RefreshLayout refreshLayout;

    private CommunityAdapter adapter;
    private Unbinder unbinder;
    //当前加载的条数
    private int offset = 0;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_community, container, false);
        unbinder = ButterKnife.bind(this, view);

        // 初始化控件
        RecyclerView recyclerView = view.findViewById(R.id.community_rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        // 评论功能暂未实现
        adapter = new CommunityAdapter();
        adapter.addOnItemClickListener(this);
        recyclerView.setAdapter(adapter);
        refreshHttp("community?method=getInfo");

        refreshLayout.setRefreshHeader(new ClassicsHeader(getContext()));
        refreshLayout.setRefreshFooter(new ClassicsFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));

        return view;
    }


    private void showDialog() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_send_community, null, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.show();
        final EditText etSend = view.findViewById(R.id.send_community_ed);
        Button btnSend = view.findViewById(R.id.send_community_btn);
        btnSend.setOnClickListener((v) -> {
                    String text = etSend.getText().toString();
                    Community community = new Community();
                    community.setContent(text);
                    community.setUser(MyApplication.myUser);
                    community.setDate(System.currentTimeMillis());
                    updateItem(community);
                    // 关闭对话框
                    dialog.cancel();
                }
        );

    }

    /**
     * 发布说说的网络请求
     * @param community
     */
    private void updateItem(Community community) {
        //传JSON应该会更容易。。。
        String url = MyApplication.HOST + "community";
        FormBody body = new FormBody.Builder()
                .add("method","updateItem")
                .add("UUID",community.getUser().getUUID())
                .add("date", String.valueOf(community.getDate()))
                .add("content",community.getContent())
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        MyApplication.client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Toast.makeText(getContext(), "请检查网络连接", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful() && response.body() != null) {
                    int result = Integer.valueOf(response.body().string());
                    if (result <1) {
                        fabSend.post(() -> {
                            Toast.makeText(getContext(), "发布失败，请检查网络", Toast.LENGTH_SHORT).show();

                        });
                    } else {
                        community.setId(result);
                        fabSend.post(() -> {
                            adapter.update(community);
                            Toast.makeText(getContext(), "发布成功", Toast.LENGTH_SHORT).show();
                        });
                    }


                }
            }
        });

    }

    /**
     * 对每项的动态进行判断是否点赞
     */
    private void setIsPhrase(List<Community> communityList) {
        for (Community community : communityList) {
            List<Phrase> phraseList = community.getPhraseList();
            for (Phrase phrase : phraseList) {
                if (phrase.getUUID().equals(MyApplication.myUser.getUUID())) {
                    community.setPhrase(true);
                    break;
                }
            }
        }
    }


    /**
     * 下拉刷新
     *
     * @param refreshLayout
     */
    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        refreshLayout.finishRefresh(1000/*,false*/);//传入false表示刷新失败
        refreshHttp("community?method=getInfo");
    }

    /**
     * 上拉加载
     *
     * @param refreshLayout
     */
    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
       /* String url = MyApplication.HOST + param;

        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            Response response = MyApplication.client.newCall(request).execute();
            if (response.isSuccessful() && response.body() != null) {
                String json = response.body().string();
                if (!"".equals(json)) {
                    List<Product> productList = JSON.parseArray(json, Product.class);
                    rvtitlelist.post(() -> {
                        adapter.refresh(productList);
                        this.productList = productList;
                    });
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            rvtitlelist.post(() -> {
                Toast.makeText(getContext(), "请检查网络是否正常", Toast.LENGTH_SHORT).show();
            });
        }
    }).start();*/
        refreshLayout.finishLoadMore(1000);//传入false表示加载失败

    }

    /**
     * 单纯第一次加载用吧
     *
     * @param param
     */
    public void refreshHttp(String param) {
        new Thread(() -> {
            String url = MyApplication.HOST + param;

            Request request = new Request.Builder()
                    .url(url)
                    .build();
            try {
                Response response = MyApplication.client.newCall(request).execute();
                if (response.isSuccessful() && response.body() != null) {
                    String json = response.body().string();
                    if (!"".equals(json)) {
                        communityList = JSON.parseArray(json, Community.class);
                        setIsPhrase(communityList);
                        Logger.d(communityList);
                        Logger.d(MyApplication.myUser);
                        fabSend.post(() -> {

                            adapter.refresh(communityList);
                        });
                    }
                } else {
                    fabSend.post(() -> {
                        Toast.makeText(getContext(), "请检查网络是否正常", Toast.LENGTH_SHORT).show();
                    });
                }
            } catch (IOException e) {
                e.printStackTrace();
                fabSend.post(() -> {
                    Toast.makeText(getContext(), "请检查网络是否正常", Toast.LENGTH_SHORT).show();
                });
            }
        }).start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v, int position) {
        int id = v.getId();
        Community community = communityList.get(position);
        switch (id){
            case R.id.community_item_phrase:{

                List<Phrase> phraseList = community.getPhraseList();
                if (community.isPhrase()){
                    for (Phrase phrase : phraseList) {
                        if (phrase.getUUID().equals(MyApplication.myUser.getUUID())){
                            phraseList.remove(phrase);
                            phraseItem(true,phrase);
                            break;
                        }
                    }
                    community.setPhrase(false);
                    adapter.refreshItem(position,community);
                }else {
                    Phrase phrase = new Phrase();
                    phrase.setCommunityId(community.getId());
                    phrase.setUUID(MyApplication.myUser.getUUID());
                    phraseItem(false,phrase);
                    phraseList.add(phrase);
                    community.setPhrase(true);
                    adapter.refreshItem(position,community);
                }
                break;
            }
        }
    }

    private void phraseItem(boolean delete,Phrase phrase) {
        //传JSON应该会更容易。。。
        String url = MyApplication.HOST + "community";
        FormBody body = new FormBody.Builder()
                .add("method","phraseItem")
                .add("delete",String.valueOf(delete))
                .add("UUID",phrase.getUUID())
                .add("communityId",String.valueOf(phrase.getCommunityId()))
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        MyApplication.client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Toast.makeText(getContext(), "请检查网络连接", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
            }
        });

    }


    @OnClick(R.id.community_fab_send)
    public void onViewClicked() {
        showDialog();
    }
}
