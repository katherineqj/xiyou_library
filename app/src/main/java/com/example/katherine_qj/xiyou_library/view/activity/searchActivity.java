package com.example.katherine_qj.xiyou_library.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.example.katherine_qj.xiyou_library.IView.IsearchActivity;
import com.example.katherine_qj.xiyou_library.R;
import com.example.katherine_qj.xiyou_library.bean.searchbook;
import com.example.katherine_qj.xiyou_library.httpUtils.GetHttpResponseString;
import com.example.katherine_qj.xiyou_library.model.ToastMassage;
import com.example.katherine_qj.xiyou_library.model.searchRecycleViewAdapter;
import com.example.katherine_qj.xiyou_library.presenter.searchActivityPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Katherine-qj on 2016/12/10.
 */

public class searchActivity extends Activity implements IsearchActivity {
    private Intent intent;
    private ImageButton searchBack;
    private EditText searchEditText;
    private RecyclerView searchRecyclerView;
    private RelativeLayout searchNothing;
    private RelativeLayout searchFaild;
    private RelativeLayout searchLoding;
    private List<searchbook> listSearch;
    private searchRecycleViewAdapter searchRecycleViewAdapter;
    public searchActivityPresenter searchActivitypresenter;
    private ToastMassage toastMassage = new ToastMassage();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
    }

    public void initView() {
        intent = new Intent();
        intent.setClass(this,BookDetailActivity.class);
        searchActivitypresenter = new searchActivityPresenter(this, getApplicationContext());
        listSearch = new ArrayList<>();
        searchBack = (ImageButton) findViewById(R.id.search_back);
        searchBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        searchEditText = (EditText) findViewById(R.id.search_edittext);
        searchLoding = (RelativeLayout)findViewById(R.id.layout_search_loding);
        searchRecyclerView = (RecyclerView) findViewById(R.id.search_recycleview);
        searchRecyclerView.setLayoutManager(new LinearLayoutManager(searchRecyclerView.getContext()));
        searchFaild = (RelativeLayout) findViewById(R.id.layout_search_faild);
        searchNothing = (RelativeLayout) findViewById(R.id.layout_search_nothing);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                if (s != null && s.toString().length() > 0) {
                    searchActivitypresenter.getSearchList(s.toString());
                    searchLoding();
                } else {

                }
            }
        });
    }

    @Override
    public void showSomeMassage(String massage) {
        toastMassage.showMassage(massage, getApplicationContext());
    }

    @Override
    public void searchFaild() {
        searchRecyclerView.setVisibility(View.INVISIBLE);
        searchFaild.setVisibility(View.VISIBLE);
        searchNothing.setVisibility(View.INVISIBLE);
        searchLoding.setVisibility(View.INVISIBLE);

    }

    @Override
    public void searchNothing() {
        searchRecyclerView.setVisibility(View.INVISIBLE);
        searchFaild.setVisibility(View.INVISIBLE);
        searchNothing.setVisibility(View.VISIBLE);
        searchLoding.setVisibility(View.INVISIBLE);
    }

    @Override
    public void searchSuccess() {
        searchRecyclerView.setVisibility(View.VISIBLE);
        searchFaild.setVisibility(View.INVISIBLE);
        searchNothing.setVisibility(View.INVISIBLE);
        searchLoding.setVisibility(View.INVISIBLE);
    }
    public void searchLoding(){
        searchRecyclerView.setVisibility(View.INVISIBLE);
        searchFaild.setVisibility(View.INVISIBLE);
        searchNothing.setVisibility(View.INVISIBLE);
        searchLoding.setVisibility(View.VISIBLE);
    }

    @Override
    public void setRecycleView(List<searchbook> list1) {
        //设置RecycleView
        Log.e("list",list1.size()+"");
        searchRecyclerView.setAdapter(searchRecycleViewAdapter = new searchRecycleViewAdapter(this, list1));
        searchRecycleViewAdapter.setOnItemClickListener(new searchRecycleViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, searchbook searchbook) {
                intent.putExtra("ID",searchbook.getID());
                startActivity(intent);
            }
        });
    }
}
