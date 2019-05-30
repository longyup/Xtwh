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
import club.vasilis.xtwh.domain.FolkCustom;
import club.vasilis.xtwh.listener.OnItemClickListener;

public class FolkCustomAdapter extends RecyclerView.Adapter<FolkCustomAdapter.ViewHolder> {

    private List<FolkCustom> folkCustoms;
    private Context context;
    private OnItemClickListener listener;

    public FolkCustomAdapter() {
    }

    public FolkCustomAdapter(List<FolkCustom> folkCustoms) {
        this.folkCustoms = folkCustoms;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (context == null){
            context = parent.getContext();
        }

        View view = LayoutInflater.from(context).inflate(R.layout.item_folk_custom,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FolkCustom folkCustom = folkCustoms.get(position);
        holder.tv_passage_title.setText(folkCustom.getName());

//        String url = MyApplication.HOST+folkCustom.getImg();
//        Glide.with(context).load(url).into(holder.iv_fs_img);
    }

    @Override
    public int getItemCount() {
        if (folkCustoms != null){
            return folkCustoms.size();
        }
        return 0;
    }

    public void AddOnItemListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void refresh(List<FolkCustom> folkCustomList) {
        this.folkCustoms = folkCustomList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_fs_tv_passage)
        TextView tv_passage_title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
