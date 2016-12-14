package com.example.katherine_qj.xiyou_library.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.katherine_qj.xiyou_library.IView.IfragmentNotice;
import com.example.katherine_qj.xiyou_library.R;
import com.example.katherine_qj.xiyou_library.bean.Notice;
import com.example.katherine_qj.xiyou_library.model.RecycleViewAdapter;
import com.example.katherine_qj.xiyou_library.model.ToastMassage;
import com.example.katherine_qj.xiyou_library.presenter.FragmentNoticePresenter;
import com.example.katherine_qj.xiyou_library.view.activity.GetDetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Katherine-qj on 2016/12/3.
 */

public class FragmentNotice extends Fragment implements IfragmentNotice {
    private View view;
    private int Pager=2;
    private ToastMassage toastMassage;
    private RecyclerView notice_recycleview;
    private RelativeLayout loading_relativeLayout;
    private RelativeLayout loadingfaild_relativeLayout;
    private boolean ishave=true;
    private List<Notice> list = new ArrayList<>();
    private SwipeRefreshLayout notice_swipeRefreshLayout;
    private FragmentNoticePresenter FragmentNoticePresenter;
    private RecycleViewAdapter recycleViewAdapter ;
    private RecyclerView.OnScrollListener mRecycleViewOnScrollerChanged;
    boolean isBottom = false;
    private Intent intent;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = (View)inflater.inflate(R.layout.fragment_notice,container,false);
        InitView();
        FragmentNoticePresenter.getNoticeList("announce",1);
        return view;
    }
    public void InitView(){
        toastMassage = new ToastMassage();
         intent = new Intent(getContext(),GetDetailActivity.class);
        notice_recycleview = (RecyclerView)view.findViewById(R.id.notice_recyclerView);
        loading_relativeLayout = (RelativeLayout)view.findViewById(R.id.loading_now);
        loadingfaild_relativeLayout=(RelativeLayout)view.findViewById(R.id.loading_faild);
        notice_recycleview.addOnScrollListener(mRecycleViewOnScrollerChanged = new RecycleViewOnScrollerChanged());
        notice_swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.notice_swiplayout);
        notice_swipeRefreshLayout.setColorSchemeResources(R.color.library_red);
        notice_swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                list.clear();
                FragmentNoticePresenter.getNoticeList("announce",1);
            }
        });
        FragmentNoticePresenter = new FragmentNoticePresenter(getContext(),this,list);

    }

    @Override
    public void showMassage(String massage) {
        toastMassage.showMassage(massage,getContext());
    }

    @Override
    public void setNoHaveMore() {
        toastMassage.showMassage("没有更多数据了~~",getContext());
        recycleViewAdapter.setLoadItemVisiable(View.GONE);
        ishave = false;
    }

    @Override
    public void setRefreshing(boolean statue) {
        notice_swipeRefreshLayout.setRefreshing(statue);

    }

    @Override
    public void setRecycleView(List<Notice> list, final boolean isLoad) {
        if (isLoad) {
            this.list = list;
            notice_recycleview.setLayoutManager(new LinearLayoutManager(notice_recycleview.getContext()));
            notice_recycleview.setAdapter(recycleViewAdapter = new RecycleViewAdapter(getContext(), list,1));
            recycleViewAdapter.setOnItemClickListener(new RecycleViewAdapter.OnRecyclerViewItemClickListener() {
                @Override
                public void onItemClick(View view, String ID) {
                    intent.putExtra("type","announce");
                    intent.putExtra("ID",ID);
                    startActivity(intent);

                }
            });

        }else {
            recycleViewAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void setLoadingFaild() {
        loadingfaild_relativeLayout.setVisibility(View.VISIBLE);
        notice_recycleview.setVisibility(View.INVISIBLE);
        loading_relativeLayout.setVisibility(View.INVISIBLE);


    }

    @Override
    public void setLoading() {
        loading_relativeLayout.setVisibility(View.VISIBLE);
        loadingfaild_relativeLayout.setVisibility(View.INVISIBLE);
        notice_recycleview.setVisibility(View.INVISIBLE);

    }

    @Override
    public void setRecycleView() {
        notice_recycleview.setVisibility(View.VISIBLE);
        loading_relativeLayout.setVisibility(View.INVISIBLE);
        loadingfaild_relativeLayout.setVisibility(View.INVISIBLE);
    }

    public class RecycleViewOnScrollerChanged extends RecyclerView.OnScrollListener{
        private int laseSeeItem;


        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            laseSeeItem = ((LinearLayoutManager)notice_recycleview.getLayoutManager()).findLastVisibleItemPosition();
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
        FragmentNoticePresenter.addNoticeListDate("announce",pager);

    }
}
