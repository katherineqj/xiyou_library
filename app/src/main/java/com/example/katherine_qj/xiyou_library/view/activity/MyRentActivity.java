package com.example.katherine_qj.xiyou_library.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.example.katherine_qj.xiyou_library.IView.IRentActivity;
import com.example.katherine_qj.xiyou_library.R;
import com.example.katherine_qj.xiyou_library.bean.RentBook;
import com.example.katherine_qj.xiyou_library.model.RentRecycleViewAdapter;
import com.example.katherine_qj.xiyou_library.model.ToastMassage;
import com.example.katherine_qj.xiyou_library.presenter.RentActivityPresenter;

import java.util.List;

/**
 * Created by Katherine-qj on 2016/12/15.
 */

public class MyRentActivity extends Activity implements IRentActivity {
    private ImageButton rentBack;
    private RecyclerView rentRecycleView;
    private RelativeLayout rentLoding;
    private RelativeLayout rentNull;
    private RelativeLayout rentFaild;
    private ToastMassage toastMassage;
    private List<RentBook> rentBooks;
    private RentActivityPresenter rentActivityPresenter;
    private RentRecycleViewAdapter rentRecycleViewAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myrent);
        rentinitView();
        rentActivityPresenter.getJsonString();
    }
    public void rentinitView(){
        toastMassage = new ToastMassage();
        rentBack = (ImageButton)findViewById(R.id.rent_back);
        rentLoding = (RelativeLayout)findViewById(R.id.rent_loding);
        rentNull  =(RelativeLayout)findViewById(R.id.rent_null);
        rentFaild = (RelativeLayout)findViewById(R.id.rent_faild);
        rentRecycleView = (RecyclerView)findViewById(R.id.rent_recycleview);
        rentRecycleView.setLayoutManager(new LinearLayoutManager(this));
        rentActivityPresenter = new RentActivityPresenter(this,this);
    }

    @Override
    public void ShowSomeMassage(String massage) {
        toastMassage.showMassage(massage,this);
    }

    @Override
    public void SetRentNull() {
        rentLoding.setVisibility(View.INVISIBLE);
        rentNull.setVisibility(View.VISIBLE);
        rentFaild.setVisibility(View.INVISIBLE);
        rentRecycleView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void SetRentLoding() {
        rentLoding.setVisibility(View.VISIBLE);
        rentNull.setVisibility(View.INVISIBLE);
        rentFaild.setVisibility(View.INVISIBLE);
        rentRecycleView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void SetRentSuccess(List<RentBook> rentBooks) {
        this.rentBooks = rentBooks;
        rentLoding.setVisibility(View.INVISIBLE);
        rentNull.setVisibility(View.INVISIBLE);
        rentFaild.setVisibility(View.INVISIBLE);
        rentRecycleView.setVisibility(View.VISIBLE);
        rentRecycleView.setAdapter(rentRecycleViewAdapter=new RentRecycleViewAdapter(this,rentBooks));

    }

    @Override
    public void SetRentFaild() {
        rentLoding.setVisibility(View.INVISIBLE);
        rentNull.setVisibility(View.INVISIBLE);
        rentFaild.setVisibility(View.VISIBLE);
        rentRecycleView.setVisibility(View.INVISIBLE);
    }
}
