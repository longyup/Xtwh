package club.vasilis.xtwh.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import club.vasilis.xtwh.R;

public class AddOrganizationAdapter extends RecyclerView.Adapter<AddOrganizationAdapter.ViewHolder> {


    private List<String> mDatas;
    private List<String> regionDatas;
    private List<String> HourDatas;
    private List<String> PeoplenumberDatas;
    private Context mContext;
    private LayoutInflater inflater;

    public AddOrganizationAdapter(Context context, List<String> datas,List<String> regionDatas, List<String>  HourDatas,List<String> PeoplenumberDatas  ){
        this. mContext=context;
        this. mDatas=datas;
        this.regionDatas = regionDatas;
        this.HourDatas = HourDatas;
        this.PeoplenumberDatas = PeoplenumberDatas;
        inflater=LayoutInflater. from(mContext);
    }

    @Override
    public int getItemCount() {

        return mDatas.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.add_activity_item,viewGroup,false);
        ViewHolder holder= new ViewHolder(view);

        //点击事件

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNormalDialog();
            }
        });






        return holder;
    }

    private void showNormalDialog(){
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(mContext);
        normalDialog.setIcon(R.drawable.back);
        normalDialog.setTitle("志愿余杭");
        normalDialog.setMessage("是否查看？");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });
        normalDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        // 显示
        normalDialog.show();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tv.setText( mDatas.get(i));
        viewHolder.iv.setImageResource(R.drawable.back);
        viewHolder.regiontv.setText(regionDatas.get(i));
        viewHolder.hourtv.setText(HourDatas.get(i));
        viewHolder.peoplenumbertv.setText(PeoplenumberDatas.get(i));
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        TextView regiontv;
        TextView hourtv;
        TextView peoplenumbertv;
        ImageView iv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.add_activity_tv);
            iv = itemView.findViewById(R.id.add_activity_iv);
            regiontv = itemView.findViewById(R.id.add_activity_regiontv);
            hourtv = itemView.findViewById(R.id.add_activity_hourtv);
            peoplenumbertv = itemView.findViewById(R.id.add_activity_peplenumbertv);
        }
    }
}
