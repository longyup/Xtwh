package club.vasilis.xtwh.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import club.vasilis.xtwh.R;
import club.vasilis.xtwh.adapter.FolkCustomAdapter;
import club.vasilis.xtwh.application.MyApplication;
import club.vasilis.xtwh.domain.FolkCustom;
import club.vasilis.xtwh.listener.OnItemClickListener;
import okhttp3.Request;
import okhttp3.Response;

public class FolkCustomFragment extends Fragment implements OnItemClickListener {
    @BindView(R.id.frag_folk_custom_rv)
    RecyclerView fragFolkCustomRv;
    private View view;
    private FolkCustomAdapter adapter;
    private static final String TAG = "FolkCustomFragment";

    public static FolkCustomFragment getInstance() {
        return new FolkCustomFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_folk_custom, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;

    }

    private void init() {
        GridLayoutManager manager = new GridLayoutManager(getContext(), 2);
        fragFolkCustomRv.setLayoutManager(manager);

        adapter = new FolkCustomAdapter();
        adapter.AddOnItemListener(this);
        fragFolkCustomRv.setAdapter(adapter);
        refreshHttp("folk_custom?method=cusShowAllJson");
    }

    /**
     * 通过网络刷新,然后调用adapter去刷新
     *
     * @param param 请求参数
     */
    private void refreshHttp(String param) {
        new Thread(() -> {
            String url = MyApplication.HOST + param;

            Request request = new Request.Builder()
                    .url(url)
                    .build();
            try {
                Response response = MyApplication.client.newCall(request).execute();
                if (response.isSuccessful() && response.body() != null) {
                    String json = response.body().string();
                    List<FolkCustom> folkCustomList = JSON.parseArray(json, FolkCustom.class);
                    fragFolkCustomRv.post(() -> {
                        adapter.refresh(folkCustomList);
                    });
                }
            } catch (IOException e) {
                e.printStackTrace();
                fragFolkCustomRv.post(() -> {
                    Toast.makeText(getContext(), "请检查网络是否正常", Toast.LENGTH_SHORT).show();
                });
            }
        }).start();
    }

    @Override
    public void onClick(View v, int position) {
        Toast.makeText(getContext(), "onclick"+position, Toast.LENGTH_SHORT).show();
    }
}
