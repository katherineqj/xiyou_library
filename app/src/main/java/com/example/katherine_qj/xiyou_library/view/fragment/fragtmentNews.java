package com.example.katherine_qj.xiyou_library.view.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
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
    private int Pager=2;
    private boolean ishave=true;
    private RecyclerView recyclerView_news;
    private List<News> list = new ArrayList<>();
    private RecycleViewAdapter recycleViewAdapter ;
    private fragementNewsPresenter fragementNewsPresenter;
    private RecyclerView.OnScrollListener mRecycleViewOnScrollerChanged;
    private ToastMassage toastMassage = new ToastMassage();
    boolean isBottom = false;

    @Override
    public void showNetWorkError() {
        toastMassage.showMassage("网络错误",getContext());

    }

    @Override
    public void showSomethingError() {
        toastMassage.showMassage("接口挂了吧还是什么乱七八糟的",getContext());
    }




    @Override
    public void showNoHaveDate() {
        toastMassage.showMassage("没有更多数据了",getContext());
        recycleViewAdapter.setLoadItemVisiable(View.GONE);
        ishave = false;
    }


    @Override
    public void setRecycleView(List<News> list,boolean isLoad) {
        if (isLoad) {
            this.list = list;
            recyclerView_news.setLayoutManager(new LinearLayoutManager(recyclerView_news.getContext()));
            recyclerView_news.setAdapter(recycleViewAdapter = new RecycleViewAdapter(getContext(), list));
        }else {
            recycleViewAdapter.notifyDataSetChanged();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = (View)inflater.inflate(R.layout.fragment_news,container,false);
        initView();
        fragementNewsPresenter.getNewsList("news",1);
        return view;
    }
    public void initView(){
        recyclerView_news = (RecyclerView)view.findViewById(R.id.news_recyclerView);
        recycleViewAdapter = new RecycleViewAdapter(getContext(),list);
        fragementNewsPresenter = new fragementNewsPresenter(getContext(),this,list);
        recyclerView_news.addOnScrollListener(mRecycleViewOnScrollerChanged = new RecycleViewOnScrollerChanged());
    }

    public class RecycleViewOnScrollerChanged extends RecyclerView.OnScrollListener{
        private int laseSeeItem;


        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            laseSeeItem = ((LinearLayoutManager)recyclerView_news.getLayoutManager()).findLastVisibleItemPosition();
            if (isBottom){
                recycleViewAdapter.setLoadItemVisiable(View.GONE);
            }
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState==RecyclerView.SCROLL_STATE_IDLE&&laseSeeItem+1==recycleViewAdapter.getItemCount()&&ishave){
                getNewDate(Pager++);

            }
        }
    }

    public void getNewDate( int pager){

        fragementNewsPresenter.addListDate("news",pager);

    }
}
