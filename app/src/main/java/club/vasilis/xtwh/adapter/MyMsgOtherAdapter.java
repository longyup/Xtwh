package club.vasilis.xtwh.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import club.vasilis.xtwh.R;

public class MyMsgOtherAdapter extends RecyclerView.Adapter<MyMsgOtherAdapter.ViewHolder> {

    private static final String TAG = "MyMsgOtherAdapter";
    private String[] mOtherList;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_msg_other_content_item,viewGroup,false);
        final ViewHolder holder = new ViewHolder(view);
        //点击事件
        holder.tv_otherTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Log.e(TAG, "onClick: "+"You have clicked view"+mOtherList[position]);
                Toast.makeText(v.getContext(),"You have clicked view"+mOtherList[position],Toast.LENGTH_SHORT).show();
            }
        });

//        holder.iv_otherImg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int position = holder.getAdapterPosition();
//                MyMsgOther other = mOtherList.get(position);
//                Log.e(TAG, "onClick: "+"You have clicked view"+other.getImageId() );
//                Toast.makeText(v.getContext(),"You have clicked view"+other.getImageId(),Toast.LENGTH_SHORT).show();
//            }
//        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tv_otherTitle.setText(mOtherList[i]);
    }

    @Override
    public int getItemCount() {
        return mOtherList.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        View otherView;
        View vLine;
        TextView tv_otherTitle;
        ImageView iv_otherImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            otherView = itemView;
            tv_otherTitle = itemView.findViewById(R.id.my_msg_other_tv_title);
            iv_otherImg = itemView.findViewById(R.id.my_msg_other_iv_icon);
            vLine = itemView.findViewById(R.id.my_msg_other_view_line);
        }
    }

    public MyMsgOtherAdapter(String[] mOtherList) {
        this.mOtherList = mOtherList;
    }
}
