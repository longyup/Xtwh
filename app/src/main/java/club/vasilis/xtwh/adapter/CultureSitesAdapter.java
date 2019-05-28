package club.vasilis.xtwh.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import club.vasilis.xtwh.R;
import club.vasilis.xtwh.application.MyApplication;
import club.vasilis.xtwh.domain.CultureSites;

//CultureSitesAdapter的适配器
public class CultureSitesAdapter extends RecyclerView.Adapter<CultureSitesAdapter.ViewHolder> {

    private List<CultureSites> cultureSitesList;
    private Context context;

    public CultureSitesAdapter() {
    }

    public CultureSitesAdapter(List<CultureSites> cultureSitesList) {
        this.cultureSitesList = cultureSitesList;
    }

    @NonNull
    @Override
    public CultureSitesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (context == null) {
            context = viewGroup.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.item_culture_sites, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CultureSitesAdapter.ViewHolder viewHolder, int i) {
        CultureSites cultureSites = cultureSitesList.get(i);
        viewHolder.tvCultureSitesName.setText(cultureSites.getTitle());

        String url = MyApplication.HOST + cultureSites.getImg();
        Glide.with(context).load(url).into(viewHolder.ivCultureSitesImg);
    }

    @Override
    public int getItemCount() {
        if (cultureSitesList != null) {
            return cultureSitesList.size();
        }
        return 0;

    }

    public void refresh(List<CultureSites> cultureSitesList ) {
        this.cultureSitesList = cultureSitesList;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_tv_culture_sites_name)
        TextView tvCultureSitesName;

        @BindView(R.id.item_iv_culture_sites)
        ImageView ivCultureSitesImg;

        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            cardView = (CardView) itemView;
        }
    }
}