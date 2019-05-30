package club.vasilis.xtwh.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import club.vasilis.xtwh.R;
import club.vasilis.xtwh.application.MyApplication;
import club.vasilis.xtwh.domain.Product;
import okhttp3.Request;
import okhttp3.Response;

public class ProductInfoActivity extends AppCompatActivity {

    @BindView(R.id.product_info_tv_title)
    TextView tvTitle;
    @BindView(R.id.product_info_tv_online_time)
    TextView tvOnlineTime;
    @BindView(R.id.product_info_tv_details)
    TextView tvDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);
        ButterKnife.bind(this);

        initView();

    }

    private void initView() {
        String id = getIntent().getStringExtra("id");

        new Thread(() -> {
            String url = MyApplication.HOST + "product?method=detailsbyjson&id=" + id;

            Request request = new Request.Builder()
                    .url(url)
                    .build();

            try {
                Response response = MyApplication.client.newCall(request).execute();
                if (response.isSuccessful() && response.body() != null) {
                    String json = response.body().string();
                    if (!"".equals(json)) {
                        Product product = JSON.parseObject(json, Product.class);
                        tvDetails.post(() -> {
                            tvOnlineTime.setText(product.getOnlinetime());
                            tvTitle.setText(product.getName());
                            tvDetails.setText(product.getBrief());
                        });
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                tvTitle.post(() -> {
                    Toast.makeText(this, "请检查网络是否正常", Toast.LENGTH_SHORT).show();
                });
            }
        }).start();
    }

    public static void actionStart(Context context, String id) {
        Intent intent = new Intent(context, ProductInfoActivity.class);
        intent.putExtra("id", id);
        context.startActivity(intent);

    }

    @OnClick(R.id.toolbar_back)
    public void onViewClicked() {
        finish();
    }
}
