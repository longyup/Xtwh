package club.vasilis.xtwh.web;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import club.vasilis.xtwh.BuildConfig;
import club.vasilis.xtwh.R;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ShowActivityDails extends AppCompatActivity{

    private static final String TAG = "ShowActivityDails";

    private static final String HOST = "http://10.0.2.2:8080/10_NULL_war_exploded/activity?method=getJsonActivityAll";

    private static OkHttpClient client = new OkHttpClient.Builder()
            .readTimeout(2000, TimeUnit.MILLISECONDS)
            .callTimeout(2000,TimeUnit.MILLISECONDS)
            .build();


    //黄油刀
    @BindView(R.id.rv_show_activity)
    RecyclerView rv;

    private ActivityAdapter activityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_dails);
        ButterKnife.bind(this);
        rv.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        activityAdapter = new ActivityAdapter();
        rv.setAdapter(activityAdapter);

    }

    @OnClick(R.id.btn_get_activity_data)
    public void onClick() {
        inntData();
    }



    private void inntData() {
        String url = HOST;

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

                            try {
                                JSONArray jsonArray = new JSONArray(body);
                                final List<Activity> activityList = new ArrayList<>();
                                for (Activity activity : activityList){
                                    JSONObject obj = new JSONObject();
                                    obj.put("name",activity.getName());
                                    obj.put("info",activity.getInfo());
                                    obj.put("launchTime",activity.getLaunchTime());
                                    jsonArray.put(obj);

                                }
                                runOnUiThread(new Runnable() {
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
    }

     class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.Holder> {
        private List<Activity> activityList;

         public ActivityAdapter() {
         }

         @NonNull
         @Override
         public ActivityAdapter.Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
             return new Holder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.show_activity_list_data,viewGroup,false));
         }

         @Override
         public void onBindViewHolder(@NonNull ActivityAdapter.Holder holder, int i) {

             Activity activity = activityList.get(i);
             if (activityList != null){
                 holder.tvName.setText(activity.getName());
                 holder.tvInfo.setText(activity.getInfo());
                 holder.tvLaunchTime.setText(activity.getLaunchTime());
             }
         }

         @Override
         public int getItemCount() {

             if (activityList != null){
                 return activityList.size();
             }
             return 0;
         }


         public ActivityAdapter(List<Activity> activityList) {
             this.activityList = activityList;
         }



         public void setActivity(List<Activity> activityList) {
             this.activityList = activityList;
             notifyDataSetChanged();

         }

         public class Holder extends RecyclerView.ViewHolder {
             @BindView(R.id.activity_json_name)
             TextView tvName;
             @BindView(R.id.activity_json_info)
             TextView tvInfo;
             @BindView(R.id.activity_json_launchTime)
             TextView tvLaunchTime;

             public Holder(View view) {
                 super(view);
                 ButterKnife.bind(this,view);


             }
         }
     }
