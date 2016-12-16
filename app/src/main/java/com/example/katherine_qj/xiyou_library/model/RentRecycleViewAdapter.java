package com.example.katherine_qj.xiyou_library.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.katherine_qj.xiyou_library.R;
import com.example.katherine_qj.xiyou_library.bean.RentBook;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Katherine-qj on 2016/12/15.
 */

public class RentRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<RentBook> rentBooks = new ArrayList<>();
    public RentRecycleViewAdapter(Context context, List<RentBook> list){
        this.context = context;
        this.rentBooks = list;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_rentbook,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder){
            ((ViewHolder)holder).item_rent_title.setText(rentBooks.get(position).getTitle());
            ((ViewHolder)holder).item_rent_deptment.setText(rentBooks.get(position).getDepartment());
            ((ViewHolder)holder).item_rent_date.setText(rentBooks.get(position).getDate());
            ((ViewHolder) holder).item_rent_state.setText(rentBooks.get(position).getState());
        }

    }

    @Override
    public int getItemCount() {
        return rentBooks.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView item_rent_deptment;
        TextView item_rent_title;
        TextView item_rent_date;
        TextView item_rent_state;
        public ViewHolder(View v){
            super(v);
            item_rent_title = (TextView)v.findViewById(R.id.rentbook_title_to);
            item_rent_deptment = (TextView)v.findViewById(R.id.rent_item_deptment);
            item_rent_date = (TextView)v.findViewById(R.id.rent_item_date);
            item_rent_state = (TextView)v.findViewById(R.id.rent_item_state);
        }
    }
}
