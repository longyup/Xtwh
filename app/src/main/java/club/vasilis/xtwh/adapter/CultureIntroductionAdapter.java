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
import club.vasilis.xtwh.ui.activity.CultureIntroductionContentActivity;
import club.vasilis.xtwh.domain.CultureIntroduction;

//CultureIntroduction的适配器
public class CultureIntroductionAdapter extends RecyclerView.Adapter<CultureIntroductionAdapter.ViewHolder> {
    private Context context;
   private List<CultureIntroduction> mCultureIntroductionList;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.culture_introduction_item, viewGroup, false);
        final ViewHolder holder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CultureIntroduction cultureIntroductions = mCultureIntroductionList.get(holder.getAdapterPosition());
                CultureIntroductionContentActivity.actionStart(context, cultureIntroductions.getTitle(), cultureIntroductions.getContent());
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        CultureIntroduction cultureIntroductions = mCultureIntroductionList.get(i);
        viewHolder.tvCultureIntroductionTitle.setText(cultureIntroductions.getTitle()!=null ?cultureIntroductions.getTitle():"null");
    }

    @Override
    public int getItemCount() {
        return mCultureIntroductionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_culture_introduction_title)
        TextView tvCultureIntroductionTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);
        }
    }

    public CultureIntroductionAdapter(Context context,List<CultureIntroduction> CultureIntroductionList) {
        this.context = context;
        mCultureIntroductionList = CultureIntroductionList;
    }
    public void refresh(List<CultureIntroduction> cultureIntroductionList){
        mCultureIntroductionList = cultureIntroductionList;
        notifyDataSetChanged();
    }
}