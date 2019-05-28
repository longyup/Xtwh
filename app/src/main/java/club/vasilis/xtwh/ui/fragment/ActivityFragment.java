package club.vasilis.xtwh.ui.fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import butterknife.BindView;
import butterknife.ButterKnife;
import club.vasilis.xtwh.BuildConfig;
import club.vasilis.xtwh.R;
import club.vasilis.xtwh.adapter.ActivityAdapter;
import club.vasilis.xtwh.application.MyApplication;
import club.vasilis.xtwh.domain.Activity;
import club.vasilis.xtwh.listener.OnItemClickListener;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ActivityFragment extends Fragment implements OnItemClickListener {

    private static final String TAG = "ShowActivityDails";



    private static OkHttpClient client = new OkHttpClient.Builder()
            .readTimeout(2000, TimeUnit.MILLISECONDS)
            .callTimeout(2000,TimeUnit.MILLISECONDS)
            .build();


    //黄油刀
    @BindView(R.id.rv_show_activity)
    RecyclerView rv;
    private ActivityAdapter activityAdapter;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_show_dails,container,false);
        ButterKnife.bind(this,view);
        rv.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        activityAdapter = new ActivityAdapter();
        activityAdapter.AddOnItemListener(this);
        rv.setAdapter(activityAdapter);
        inntData();
        return view;
    }


    private void inntData() {
        String url = MyApplication.HOST + "activity?method=getJsonActivityAll&typeId=";
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        client.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        if (BuildConfig.DEBUG){
                            Log.e(TAG,"onFailure",e);
                        }
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful() && response.body() != null){
                            String body = response.body().string();
                            Log.e("456",""+body);

                            try {
                                JSONArray jsonArray = new JSONArray(body);
                                List<Activity> activityList = new ArrayList<>();
                                for (int i =0;i<jsonArray.length();i++){
                                    Activity activity = new Activity();
                                    JSONObject obj = jsonArray.getJSONObject(i);
                                    activity.setName(obj.getString("name"));
                                    activity.setStartTime(obj.getString("startTime"));
                                    activity.setLaunchTime(obj.getString("launchTime"));
                                    activity.setId(obj.getString("id"));
                                    activity.setImg(MyApplication.HOST + obj.getString("img"));
                                    activityList.add(activity);
//                                    Intent intent = new Intent(getContext(), ActivityDailsActivity.class);
//                                    intent.putExtra("activity_data",activity);
//                                    getContext().startActivity(intent);
                                    Log.e("123",""+obj);
                                }

                                rv.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        activityAdapter.setActivity(activityList);
                                    }
                                });
                            }catch (Exception e){
                                if (BuildConfig.DEBUG) {
                                    Log.e(TAG, "onFailure: ", e);
                                }

                            }
                        }
                    }
                });
        }

    @Override
    public void onClick(View v, int position) {
        Toast.makeText(getContext(), "onClick:"+position, Toast.LENGTH_SHORT).show();
    }
}