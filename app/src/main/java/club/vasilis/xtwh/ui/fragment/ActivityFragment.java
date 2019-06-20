package club.vasilis.xtwh.ui.fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import club.vasilis.xtwh.BuildConfig;
import club.vasilis.xtwh.R;
import club.vasilis.xtwh.adapter.ActivityAdapter;
import club.vasilis.xtwh.application.MyApplication;
import club.vasilis.xtwh.domain.Activity;
import club.vasilis.xtwh.listener.OnItemClickListener;
import club.vasilis.xtwh.ui.activity.ActivityDailsActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;
//implements OnItemClickListener
public class ActivityFragment extends Fragment implements OnItemClickListener {

    private static final String TAG = "ShowActivityDails";

    @BindView(R.id.rv_show_activity)
    RecyclerView rv;
    @BindView(R.id.activity_swip)
    SwipeRefreshLayout swip;

    private ActivityAdapter activityAdapter;
    List<Activity> activityList;
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
        initData();
        swip.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
                swip.setRefreshing(false);
            }
        });

        return view;
    }


    private void initData() {
        String url = MyApplication.HOST + "activity?method=getJsonActivityAll";
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

       MyApplication.client.newCall(request)
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
                            Activity activity = null;
                            try {
                                JSONArray jsonArray = new JSONArray(body);
                                activityList = new ArrayList<>();
                                for (int i =0;i<jsonArray.length();i++){
                                    activity = new Activity();
                                    JSONObject obj = jsonArray.getJSONObject(i);
                                    activity.setName(obj.getString("name"));
                                    activity.setStartTime(obj.getString("startTime"));
                                    activity.setLaunchTime(obj.getString("launchTime"));
                                    activity.setId(obj.getString("id"));
                                    activity.setImg(MyApplication.HOST + obj.getString("img"));
                                    activity.setInfo(obj.getString("info"));
                                    activityList.add(activity);
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
        Activity activity = activityList.get(position);
        ActivityDailsActivity.actionStart(getContext(),activity.getImg(),activity.getName(),activity.getStartTime(),activity.getLaunchTime(),activity.getInfo());



    }
}