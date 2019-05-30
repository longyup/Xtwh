package club.vasilis.xtwh.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import club.vasilis.xtwh.R;
import club.vasilis.xtwh.ui.fragment.CultureSitesContentFragment;

public class CultureSitesContentActivity extends AppCompatActivity {

    public static void actionStart(Context context, String Title, String Content){
        Intent intent = new Intent(context,CultureSitesContentActivity.class);
        intent.putExtra("Title",Title);
        intent.putExtra("Content",Content);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置内容布局
        setContentView(R.layout.activity_culture_sites_content);
        //用string来获取传入的标题
        String Title = getIntent().getStringExtra("Title");
        //获取传入的内容
        String Content = getIntent().getStringExtra("Content");
        //刷新ContentFragment界面
        CultureSitesContentFragment cultureSitesContentFragment = (CultureSitesContentFragment) getSupportFragmentManager().findFragmentById(R.id.culture_sites_content_fragment);
        cultureSitesContentFragment.refresh(Title,Content);
    }
}
