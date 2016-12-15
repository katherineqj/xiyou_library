package com.example.katherine_qj.xiyou_library.model;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.katherine_qj.xiyou_library.R;
import com.example.katherine_qj.xiyou_library.bean.CirrculationInfo;

import java.util.List;

/**
 * Created by Katherine-qj on 2016/12/15.
 */

public class RecycleviewCirculationinfoAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private List<CirrculationInfo> list;
    public RecycleviewCirculationinfoAdapter(Context context,List<CirrculationInfo> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.circulationinfo_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder){
            ((ViewHolder) holder).item_cirrcu_barcode.setText(list.get(position).getBarcode());
            ((ViewHolder) holder).item_cirrcu_status.setText(list.get(position).getStatus());
            if (!(list.get(position).getStatus().equals("在架可借"))){
                ((ViewHolder) holder).linearLayout_date.setVisibility(View.VISIBLE);
                ((ViewHolder) holder).item_cirrcu_date.setText(list.get(position).getDate());
                ((ViewHolder) holder).item_cirrcu_dapartment.setText(list.get(position).getDapartment());
            }else {
                ((ViewHolder) holder).linearLayout_date.setVisibility(View.GONE);
                ((ViewHolder) holder).item_cirrcu_dapartment.setText(list.get(position).getDapartment());
            }

        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout linearLayout_date;
        TextView item_cirrcu_date;
        TextView item_cirrcu_dapartment;
        TextView item_cirrcu_status;
        TextView item_cirrcu_barcode;
        public ViewHolder(View v){
            super(v);
            linearLayout_date = (LinearLayout)v.findViewById(R.id.item_cirrcu_layout_date);
            item_cirrcu_date = (TextView)v.findViewById(R.id.item_cirrcu_date);
            item_cirrcu_dapartment = (TextView)v.findViewById(R.id.item_cirrcu_dapartment);
            item_cirrcu_status = (TextView)v.findViewById(R.id.item_cirrcu_status);
            item_cirrcu_barcode = (TextView)v.findViewById(R.id.item_cirrcu_barcode);
        }
    }
}
