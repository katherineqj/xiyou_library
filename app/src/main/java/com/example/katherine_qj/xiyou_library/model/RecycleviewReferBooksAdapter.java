package com.example.katherine_qj.xiyou_library.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.katherine_qj.xiyou_library.R;
import com.example.katherine_qj.xiyou_library.bean.CirrculationInfo;
import com.example.katherine_qj.xiyou_library.bean.ReferBooks;

import java.util.List;

/**
 * Created by Katherine-qj on 2016/12/15.
 */

public class RecycleviewReferBooksAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<ReferBooks> list;
    public RecycleviewReferBooksAdapter(Context context,List<ReferBooks> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.referbook_item,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder){
            ((ViewHolder) holder).item_refer_title.setText(list.get(position).getTitle());
            ((ViewHolder) holder).item_refer_author.setText(list.get(position).getAuthor());
            ((ViewHolder) holder).item_refer_id.setText(list.get(position).getID());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView item_refer_author;
        TextView item_refer_id;
        TextView item_refer_title;

        public ViewHolder(View v) {
            super(v);
            item_refer_author = (TextView) v.findViewById(R.id.refer_item_author);
            item_refer_id = (TextView) v.findViewById(R.id.refer_item_id);
            item_refer_title = (TextView) v.findViewById(R.id.refer_item_title);
        }
    }
}
