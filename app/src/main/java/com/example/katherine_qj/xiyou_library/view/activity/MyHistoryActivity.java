package com.example.katherine_qj.xiyou_library.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.example.katherine_qj.xiyou_library.IView.IMyHistoryActivity;
import com.example.katherine_qj.xiyou_library.R;
import com.example.katherine_qj.xiyou_library.bean.HistoryBook;
import com.example.katherine_qj.xiyou_library.model.HistoryRecycleViewAdapter;
import com.example.katherine_qj.xiyou_library.model.ToastMassage;
import com.example.katherine_qj.xiyou_library.presenter.HistoryActivityPresenter;


import java.util.List;

/**
 * Created by Katherine-qj on 2016/12/16.
 */

public class MyHistoryActivity  extends Activity implements IMyHistoryActivity{
    private ImageButton historyBack;
    private RecyclerView historyRecycleView;
    private RelativeLayout historyLoding;
    private RelativeLayout historyNull;
    private RelativeLayout historyFaild;
    private ToastMassage toastMassage;
    private List<HistoryBook> historyBooks;
    private HistoryActivityPresenter historyActivityPresenter;
    private HistoryRecycleViewAdapter historyRecycleViewAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        historyinitView();
        historyActivityPresenter.getJsonString();
    }
    public void historyinitView(){
        toastMassage = new ToastMassage();
        historyBack = (ImageButton)findViewById(R.id.history_back);
        historyBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        historyLoding = (RelativeLayout)findViewById(R.id.history_loding);
        historyNull  =(RelativeLayout)findViewById(R.id.history_null);
        historyFaild = (RelativeLayout)findViewById(R.id.history_faild);
        historyRecycleView = (RecyclerView)findViewById(R.id.history_recycleview);
        historyRecycleView.setLayoutManager(new LinearLayoutManager(this));
        historyActivityPresenter = new HistoryActivityPresenter(this,this);

    }

    @Override
    public void ShowSomeMassage(String massage) {
        toastMassage.showMassage(massage,this);
    }

    @Override
    public void SetHistoryNull() {
        historyLoding.setVisibility(View.INVISIBLE);
        historyNull.setVisibility(View.VISIBLE);
        historyFaild.setVisibility(View.INVISIBLE);
        historyRecycleView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void SetHistoryLoding() {
        historyLoding.setVisibility(View.VISIBLE);
        historyNull.setVisibility(View.INVISIBLE);
        historyFaild.setVisibility(View.INVISIBLE);
        historyRecycleView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void SetHistorySuccess(List<HistoryBook> historyBooks) {
        this.historyBooks = historyBooks;
        historyLoding.setVisibility(View.INVISIBLE);
        historyNull.setVisibility(View.INVISIBLE);
        historyFaild.setVisibility(View.INVISIBLE);
        historyRecycleView.setVisibility(View.VISIBLE);
        historyRecycleView.setAdapter(historyRecycleViewAdapter = new HistoryRecycleViewAdapter(this,historyBooks));
    }

    @Override
    public void SetHistoryFaild() {
        historyLoding.setVisibility(View.INVISIBLE);
        historyNull.setVisibility(View.INVISIBLE);
        historyFaild.setVisibility(View.VISIBLE);
        historyRecycleView.setVisibility(View.INVISIBLE);
    }
}
