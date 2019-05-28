package club.vasilis.xtwh.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import club.vasilis.xtwh.R;
import club.vasilis.xtwh.domain.CultureSites;
import club.vasilis.xtwh.listener.OnItemClickListener;

//CultureSitesAdapter的适配器
public class CultureSitesAdapter extends RecyclerView.Adapter<CultureSitesAdapter.ViewHolder> {
    private Context context;
    private List<CultureSites> mCultureSites;
    private OnItemClickListener listener;

    public CultureSitesAdapter(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.culture_introduction_item, viewGroup, false);
       ViewHolder holder = new ViewHolder(view);

        /*view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CultureSites cultureSites = mCultureSites.get(holder.getAdapterPosition());
                CultureIntroductionContentActivity.actionStart(context, cultureSites.getTitle(), cultureSites.getContent());
            }
        });*/
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CultureSites cultureSites = mCultureSites.get(position);
        holder.tvCultureIntroductionTitle.setText(cultureSites.getTitle()!=null ?cultureSites.getTitle():"null");
    }

    @Override
    public int getItemCount() {
        return mCultureSites.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.tv_culture_introduction_title)
        TextView tvCultureIntroductionTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            if (listener != null){
                itemView.setOnClickListener(this);
            }
            ButterKnife.bind(this,itemView);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(v,getLayoutPosition());
        }
    }

    public CultureSitesAdapter(Context context,List<CultureSites> cultureSitesList) {
        this.context = context;
        mCultureSites = cultureSitesList;
    }
    public void refresh(List<CultureSites> cultureSitesList){
        mCultureSites = cultureSitesList;
        notifyDataSetChanged();
    }
}