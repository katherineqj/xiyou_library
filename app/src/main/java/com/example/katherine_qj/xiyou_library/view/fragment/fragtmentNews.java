package com.example.katherine_qj.xiyou_library.view.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.katherine_qj.xiyou_library.IView.IfragmentNews;
import com.example.katherine_qj.xiyou_library.R;
import com.example.katherine_qj.xiyou_library.bean.News;
import com.example.katherine_qj.xiyou_library.model.RecycleViewAdapter;
import com.example.katherine_qj.xiyou_library.model.ToastMassage;
import com.example.katherine_qj.xiyou_library.presenter.fragementNewsPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Katherine-qj on 2016/12/3.
 */

public class fragtmentNews extends Fragment implements IfragmentNews{
    private View view;
    private RecyclerView recyclerView_news;
    private List<News> list = new ArrayList<>();
    private RecycleViewAdapter recycleViewAdapter ;
    private fragementNewsPresenter fragementNewsPresenter; ;

    private ToastMassage toastMassage = new ToastMassage();

    @Override
    public void showNetWorkError() {
        toastMassage.showMassage("网络错误",getContext());

    }

    @Override
    public void showSomethingError() {
        toastMassage.showMassage("接口挂了吧还是什么乱七八糟的",getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = (View)inflater.inflate(R.layout.fragment_news,container,false);
        initView();
        fragementNewsPresenter.getNewsList("news",1);
        //list = fragementNewsPresenter.getNewsList("news",1);
        return view;
    }
    public void initView(){
        recyclerView_news = (RecyclerView)view.findViewById(R.id.news_recyclerView);
        recycleViewAdapter = new RecycleViewAdapter(getContext(),list);
        fragementNewsPresenter = new fragementNewsPresenter(getContext(),this);
    }
}
