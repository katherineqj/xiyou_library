package com.example.katherine_qj.xiyou_library.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.katherine_qj.xiyou_library.R;
import com.example.katherine_qj.xiyou_library.bean.HistoryBook;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Katherine-qj on 2016/12/16.
 */

public class HistoryRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<HistoryBook> historyBooks = new ArrayList<>();
    public HistoryRecycleViewAdapter(Context context, List<HistoryBook> list){
        this.context = context;
        this.historyBooks = list;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_historybook,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder){
            ((ViewHolder)holder).item_history_barcode.setText(historyBooks.get(position).getBarcode());
            ((ViewHolder)holder).item_history_date.setText(historyBooks.get(position).getDate());
            ((ViewHolder)holder).item_history_title.setText(historyBooks.get(position).getTitle());
            ((ViewHolder) holder).item_history_type.setText(historyBooks.get(position).getType());
        }

    }

    @Override
    public int getItemCount() {
        return historyBooks.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView item_history_barcode;
        TextView item_history_title;
        TextView item_history_date;
        TextView item_history_type;
        public ViewHolder(View v){
            super(v);
            item_history_barcode = (TextView)v.findViewById(R.id.history_item_barcode);
            item_history_title = (TextView)v.findViewById(R.id.historybook_title_to);
            item_history_date = (TextView)v.findViewById(R.id.history_item_date);
            item_history_type = (TextView)v.findViewById(R.id.history_item_type);
        }
    }
}
