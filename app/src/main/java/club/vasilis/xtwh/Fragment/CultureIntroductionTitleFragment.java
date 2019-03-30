package club.vasilis.xtwh.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import club.vasilis.xtwh.R;
import club.vasilis.xtwh.activity.CultureIntroductionContentActivity;
import club.vasilis.xtwh.domain.CultureIntroduction;

public class CultureIntroductionTitleFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.culture_introduction_title_frag,container,false);
        RecyclerView cultureIntroductionTitleRecyclerView = view.findViewById(R.id.rv_culture_introduction_title);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        cultureIntroductionTitleRecyclerView.setLayoutManager(layoutManager);
        CultureIntroductionAdapter adapter = new CultureIntroductionAdapter(getCultureIntroduction());
        cultureIntroductionTitleRecyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    //CultureIntroduction的适配器
    class CultureIntroductionAdapter extends RecyclerView.Adapter<CultureIntroductionAdapter.ViewHolder>{

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
                    CultureIntroductionContentActivity.actionStart(getActivity(), cultureIntroductions.getTitle(), cultureIntroductions.getContent());
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            CultureIntroduction cultureIntroductions = mCultureIntroductionList.get(i);
            viewHolder.tv_culture_introduction_title.setText(cultureIntroductions.getTitle());
        }

        @Override
        public int getItemCount() {
            return mCultureIntroductionList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            TextView tv_culture_introduction_title;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                tv_culture_introduction_title = itemView.findViewById(R.id.tv_culture_introduction_title);
            }
        }

        public CultureIntroductionAdapter(List<CultureIntroduction> CultureIntroductionList){
            mCultureIntroductionList = CultureIntroductionList;
        }
    }

    private List<CultureIntroduction> getCultureIntroduction() {
        List<CultureIntroduction> mCultureIntroductionList = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            CultureIntroduction cultureIntroduction= new CultureIntroduction();
            cultureIntroduction.setTitle("This is news title " + i);
            cultureIntroduction.setContent(getRandomLengthContent("This is news content" + i + "."));
            mCultureIntroductionList.add(cultureIntroduction);
        }
        return mCultureIntroductionList;
    }

    private String getRandomLengthContent(String content) {
        Random random = new Random();
        int length = random.nextInt(20) + 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(content);

        }
        return builder.toString();
    }
}
