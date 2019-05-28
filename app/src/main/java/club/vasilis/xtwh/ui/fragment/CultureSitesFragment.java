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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import club.vasilis.xtwh.R;
import club.vasilis.xtwh.adapter.CultureSitesAdapter;
import club.vasilis.xtwh.application.MyApplication;
import club.vasilis.xtwh.domain.CultureSites;
import club.vasilis.xtwh.listener.OnItemClickListener;
import okhttp3.Request;
import okhttp3.Response;

public class CultureSitesFragment extends Fragment implements OnItemClickListener {

    @BindView(R.id.rv_culture_sites_list)
    RecyclerView rvCultureSitesList;
    private CultureSitesAdapter adapter;
    private List<CultureSites> mCultureSites;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.culture_sites_frag, container, false);
        ButterKnife.bind(this, view);

        adapter = new CultureSitesAdapter(getContext(),getCultureSites());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvCultureSitesList.setLayoutManager(layoutManager);
        rvCultureSitesList.setAdapter(adapter);

        return view;
    }


    //文化遗址
    public List<CultureSites> getCultureSites() {

        List<CultureSites> cultureSitesList = new ArrayList<>();
        new Thread(() -> {
            String url = MyApplication.HOST + "/product?method=findbytype&id=T002";
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            try {
                Response response = MyApplication.client.newCall(request).execute();
                if (response.isSuccessful() && response.body() != null) {
                    Log.e("test", "response.isSuccessful");
                    String json = response.body().string();

                    try {
                        JSONArray array = new JSONArray(json);
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject object = array.getJSONObject(i);
                            String name = object.getString("name");
                            CultureSites cultureSites = new CultureSites();
                            cultureSites.setTitle(name);
                            cultureSitesList.add(cultureSites);

                        }
                        rvCultureSitesList.post(() -> {
                                    adapter.refresh(cultureSitesList);
                                }
                        );
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }


            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        ).start();

            return cultureSitesList;
    }

    @Override
    public void onClick(View v, int position) {
    }
}
