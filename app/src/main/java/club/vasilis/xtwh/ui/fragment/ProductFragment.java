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
import butterknife.Unbinder;
import club.vasilis.xtwh.R;
import club.vasilis.xtwh.adapter.ProductAdapter;
import club.vasilis.xtwh.application.MyApplication;
import club.vasilis.xtwh.domain.Product;
import club.vasilis.xtwh.listener.OnItemClickListener;
import club.vasilis.xtwh.ui.activity.ProductInfoActivity;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 乡土特产信息
 */
public class ProductFragment extends Fragment implements OnItemClickListener {

    @BindView(R.id.rv_titlelist)
    RecyclerView rvtitlelist;
    private ProductAdapter adapter;
    private Unbinder bind;
    private List<Product> productList;

    public static ProductFragment getInstance() {
        return new ProductFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        bind = ButterKnife.bind(this, view);
        init();

        return view;
    }

    private void init() {
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        rvtitlelist.setLayoutManager(layoutManager);

        adapter = new ProductAdapter();
        adapter.AddOnItemListener(this);
        // rvtitlelist.setNestedScrollingEnabled(false);
        rvtitlelist.setAdapter(adapter);
        refreshHttp("product?method=findAll");
    }


    /**
     * 通过网络刷新,然后调用adapter去刷新
     *
     * @param param 请求参数
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
        }).start();
    }


    @Override
    public void onClick(View v, int position) {
        Product product = productList.get(position);
        ProductInfoActivity.actionStart(getContext(), product.getId());

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
