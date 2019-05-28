package club.vasilis.xtwh.web;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
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
import club.vasilis.xtwh.activity.ShowWebActivityDailsActivity;
import club.vasilis.xtwh.application.MyApplication;
import club.vasilis.xtwh.domain.Activity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ShowActivityDails extends AppCompatActivity{

    private static final String TAG = "ShowActivityDails";



    private static OkHttpClient client = new OkHttpClient.Builder()
            .readTimeout(2000, TimeUnit.MILLISECONDS)
            .callTimeout(2000,TimeUnit.MILLISECONDS)
            .build();


    //黄油刀
    @BindView(R.id.rv_show_activity)
    RecyclerView rv;
    @BindView(R.id.btn_show_activity_data)
    EditText etShowData;
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
        String typeId = etShowData.getText().toString().trim();
        String url = MyApplication.HOST + "activity?method=getJsonActivityAll&typeId=" + typeId;
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
                                    Log.e("123",""+obj);
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
        Context context;

         public ActivityAdapter() {
         }

         @NonNull
         @Override
         public ActivityAdapter.Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
             return new Holder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.show_web_activity_list_data,viewGroup,false));
         }

         @Override
         public void onBindViewHolder(@NonNull ActivityAdapter.Holder holder, int i) {

             Activity activity = activityList.get(i);
             if (activityList != null){
                 holder.tvName.setText(activity.getName());
                 holder.tvStartTime.setText(activity.getStartTime());
                 holder.tvLaunchTime.setText(activity.getLaunchTime());
                 holder.tvId.setText(activity.getId());
                 Glide.with(holder.ivImg).load(activity.getImg()).into(holder.ivImg);

             }

             holder.itemView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                    Activity activity = activityList.get(holder.getAdapterPosition());
                     ShowWebActivityDailsActivity.actionStart(context,activity.getName(),activity.getStartTime(),activity.getLaunchTime(),activity.getInfo());
                 }
             });

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
             @BindView(R.id.activity_json_startTime)
             TextView tvStartTime;
             @BindView(R.id.activity_json_launchTime)
             TextView tvLaunchTime;
             @BindView(R.id.activity_json_id)
             TextView tvId;
             @BindView(R.id.activity_json_img)
             ImageView ivImg;

             public Holder(View view) {
                 super(view);
                 ButterKnife.bind(this,view);
             }
         }
     }
