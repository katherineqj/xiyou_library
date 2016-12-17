package com.example.katherine_qj.xiyou_library.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.katherine_qj.xiyou_library.R;
import com.example.katherine_qj.xiyou_library.bean.FavoriteBook;
import com.example.katherine_qj.xiyou_library.bean.HistoryBook;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Katherine-qj on 2016/12/17.
 */

public class FavoriteRecycleViewAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private List<FavoriteBook> favoriteBooks = new ArrayList<>();
    public FavoriteRecycleViewAdapter(Context context,List<FavoriteBook> list){
        this.context = context;
        this.favoriteBooks = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_favorite,parent,false);
        return  new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof  ViewHolder){
            ((ViewHolder) holder).item_favorite_title.setText(favoriteBooks.get(position).getTitle());
            ((ViewHolder) holder).item_favorite_anthor.setText(favoriteBooks.get(position).getAuthor());
            ((ViewHolder) holder).item_favorite_pub.setText(favoriteBooks.get(position).getPub());
            if (favoriteBooks.get(position).getImage()!=null){
                Picasso.with(context).load(favoriteBooks.get(position).getImage()).into(((ViewHolder) holder).item_favorite_image);
            }
        }

    }

    @Override
    public int getItemCount() {
        return favoriteBooks.size() ;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView item_favorite_title;
        TextView item_favorite_pub;
        TextView item_favorite_anthor;
        ImageView item_favorite_image;
        public ViewHolder(View v){
            super(v);
            item_favorite_title = (TextView)v.findViewById(R.id.favorite_title_to);
            item_favorite_pub = (TextView)v.findViewById(R.id.favorite_item_pub);
            item_favorite_anthor = (TextView)v.findViewById(R.id.favorite_item_anthor);
            item_favorite_image = (ImageView)v.findViewById(R.id.item_favorite_image);

        }
    }
}
