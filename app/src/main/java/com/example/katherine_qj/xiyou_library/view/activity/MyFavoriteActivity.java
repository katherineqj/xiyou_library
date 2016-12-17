package com.example.katherine_qj.xiyou_library.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.example.katherine_qj.xiyou_library.IView.IMyFavoriteActivity;
import com.example.katherine_qj.xiyou_library.R;
import com.example.katherine_qj.xiyou_library.bean.FavoriteBook;
import com.example.katherine_qj.xiyou_library.model.FavoriteRecycleViewAdapter;
import com.example.katherine_qj.xiyou_library.model.ToastMassage;
import com.example.katherine_qj.xiyou_library.presenter.FavorityActivityPresenter;

import java.util.List;

/**
 * Created by Katherine-qj on 2016/12/17.
 */

public class MyFavoriteActivity extends Activity implements IMyFavoriteActivity {
    private ImageButton favoriteBack;
    private RecyclerView favoriteRecycleView;
    private RelativeLayout favoriteLoding;
    private RelativeLayout favoriteNull;
    private RelativeLayout favoriteFaild;
    private ToastMassage toastMassage;
    private List<FavoriteBook> favoriteBooks;
    private FavorityActivityPresenter favorityActivityPresenter;
    private FavoriteRecycleViewAdapter favoriteRecycleViewAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myfavorite);
    }
    public void historyInitView(){
        toastMassage = new ToastMassage();
        favoriteBack = (ImageButton)findViewById(R.id.history_back);
        favoriteBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        favoriteLoding = (RelativeLayout)findViewById(R.id.rent_loding);
        favoriteNull  =(RelativeLayout)findViewById(R.id.rent_null);
        favoriteFaild = (RelativeLayout)findViewById(R.id.rent_faild);
        favoriteRecycleView = (RecyclerView)findViewById(R.id.rent_recycleview);
        favoriteRecycleView.setLayoutManager(new LinearLayoutManager(this));
        favorityActivityPresenter = new FavorityActivityPresenter(this,this);
    }

    @Override
    public void ShowSomeMassage(String massage) {
        toastMassage.showMassage(massage,this);
    }

    @Override
    public void SetFavoriteNull() {
        favoriteLoding.setVisibility(View.INVISIBLE);
        favoriteNull.setVisibility(View.VISIBLE);
        favoriteFaild.setVisibility(View.INVISIBLE);
        favoriteRecycleView.setVisibility(View.INVISIBLE);

    }

    @Override
    public void SetFavoriteLoding() {
        favoriteLoding.setVisibility(View.VISIBLE);
        favoriteNull.setVisibility(View.INVISIBLE);
        favoriteFaild.setVisibility(View.INVISIBLE);
        favoriteRecycleView.setVisibility(View.INVISIBLE);

    }

    @Override
    public void SetFavoriteSuccess(List<FavoriteBook> favoriteBooks) {
        this.favoriteBooks = favoriteBooks;
        favoriteLoding.setVisibility(View.INVISIBLE);
        favoriteNull.setVisibility(View.INVISIBLE);
        favoriteFaild.setVisibility(View.INVISIBLE);
        favoriteRecycleView.setVisibility(View.VISIBLE);
        favoriteRecycleView.setAdapter(favoriteRecycleViewAdapter = new FavoriteRecycleViewAdapter(this,favoriteBooks));
    }

    @Override
    public void SetFavoriteFaild() {
        favoriteLoding.setVisibility(View.INVISIBLE);
        favoriteNull.setVisibility(View.INVISIBLE);
        favoriteFaild.setVisibility(View.VISIBLE);
        favoriteRecycleView.setVisibility(View.INVISIBLE);
    }
}
