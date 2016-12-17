package com.example.katherine_qj.xiyou_library.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.katherine_qj.xiyou_library.R;
import com.example.katherine_qj.xiyou_library.bean.RankBook;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Katherine-qj on 2016/12/17.
 */

public class RankRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private List<RankBook> rankBooks = new ArrayList<>();
    private View loadView;
    public static final int TYPE_TYPE= 0xff00;
    public static final int TYPE_LODE_MORE = 0xff01;
    public RankRecycleViewAdapter(Context context,List<RankBook> list){
        this.context = context;
        this.rankBooks = list;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==TYPE_LODE_MORE){
            View v =LayoutInflater.from(context).inflate(R.layout.loadmore_item,parent,false);
            return  new LoadMoreHolder(v);
        }
        else{
            View v = LayoutInflater.from(context).inflate(R.layout.item_rank, parent, false);
            return new ViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof  ViewHolder){
            ((ViewHolder) holder).item_title.setText(rankBooks.get(position).getTitle());
            ((ViewHolder) holder).item_rank.setText(rankBooks.get(position).getRank());
            ((ViewHolder) holder).item_bornum.setText(rankBooks.get(position).getBorNum());
            ((ViewHolder) holder).item_sort.setText(rankBooks.get(position).getSort());
        }

    }

    @Override
    public int getItemViewType(int position) {
            if (position+1==getItemCount()){
                return TYPE_LODE_MORE;
            }else {
                return TYPE_TYPE;
            }
    }

    @Override
    public int getItemCount() {
        return rankBooks.size();
    }
    public class  ViewHolder extends RecyclerView.ViewHolder{
        TextView item_rank;
        TextView item_title;
        TextView item_bornum;
        TextView item_sort;
        public ViewHolder(View v){
            super(v);
            item_rank = (TextView)v.findViewById(R.id.rank_item);
            item_bornum = (TextView)v.findViewById(R.id.rank_item_bornum);
            item_sort = (TextView)v.findViewById(R.id.rank_item_sort);
            item_title = (TextView)v.findViewById(R.id.rank_title);
        }

    }
    public class LoadMoreHolder extends RecyclerView.ViewHolder {
        public LoadMoreHolder(View itemview) {
            super(itemview);
            loadView = itemview;


        }
    }
    public void setLoadItemVisible(int visibility) {
        if (loadView != null) {
            loadView.setVisibility(visibility);
        }
    }
}
