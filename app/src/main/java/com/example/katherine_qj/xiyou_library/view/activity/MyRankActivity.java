package com.example.katherine_qj.xiyou_library.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import com.example.katherine_qj.xiyou_library.IView.IMyRankActivity;
import com.example.katherine_qj.xiyou_library.R;
import com.example.katherine_qj.xiyou_library.bean.RankBook;
import com.example.katherine_qj.xiyou_library.model.RankRecycleViewAdapter;
import com.example.katherine_qj.xiyou_library.model.ToastMassage;
import com.example.katherine_qj.xiyou_library.presenter.RankActivityPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Katherine-qj on 2016/12/17.
 */

public class MyRankActivity extends Activity implements IMyRankActivity {
    private ImageButton rankBack;
    private RecyclerView rankRecycleView;
    private RelativeLayout rankLoding;
    private RelativeLayout rankFaild;
    private ToastMassage toastMassage;
    private List<RankBook> rankBooks;
    private List<RankBook> rankBooks_more;
    private RankActivityPresenter rankActivityPresenter ;
    boolean isBottom = false;
    private int sum = 0;
    private RecyclerView.OnScrollListener myRecycleViewScrollList;
    private RankRecycleViewAdapter rankRecycleViewAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        rankInitView();
        rankActivityPresenter.getJsonString();
    }

    public void rankInitView(){
        rankBooks_more = new ArrayList<>();
        toastMassage = new ToastMassage();
        rankBack = (ImageButton)findViewById(R.id.rank_back);
        rankBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rankLoding = (RelativeLayout)findViewById(R.id.rank_loding);
        rankFaild = (RelativeLayout)findViewById(R.id.rank_faild);
        rankRecycleView = (RecyclerView)findViewById(R.id.rank_recycleview);
        rankRecycleView.setLayoutManager(new LinearLayoutManager(this));
        rankRecycleView.addOnScrollListener(myRecycleViewScrollList = new RecyclerViewOnScrollerChanged());
        rankActivityPresenter = new RankActivityPresenter(this,this);
    }

    @Override
    public void ShowSomeMassage(String massage) {
        toastMassage.showMassage(massage,this);
    }

    @Override
    public void SetRankLoding() {
        rankLoding.setVisibility(View.VISIBLE);
        rankFaild.setVisibility(View.INVISIBLE);
        rankRecycleView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void SetRankSuccess(List<RankBook> rankBooks) {
        this.rankBooks = rankBooks;
        initDates_more();
        rankLoding.setVisibility(View.INVISIBLE);
        rankFaild.setVisibility(View.INVISIBLE);
        rankRecycleView.setVisibility(View.VISIBLE);
        rankRecycleView.setAdapter(rankRecycleViewAdapter = new RankRecycleViewAdapter(this,rankBooks_more));
    }

    @Override
    public void SetRankFaild() {
        rankLoding.setVisibility(View.INVISIBLE);
        rankFaild.setVisibility(View.VISIBLE);
        rankRecycleView.setVisibility(View.INVISIBLE);
    }
    public class RecyclerViewOnScrollerChanged extends RecyclerView.OnScrollListener{
        private int lastSeeItem;
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            lastSeeItem = ((LinearLayoutManager)recyclerView.getLayoutManager()).findLastVisibleItemPosition();
            if (isBottom){
                rankRecycleViewAdapter.setLoadItemVisible(View.GONE);
            }

        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE &&lastSeeItem+1==rankRecycleViewAdapter.getItemCount()){
                initDates_more();
                rankRecycleViewAdapter.notifyDataSetChanged();
            }
        }
    }

    public void initDates_more(){
        if (sum==50){
            toastMassage.showMassage("只能查看排名前50啦~~~~",this);
            rankRecycleViewAdapter.setLoadItemVisible(View.GONE);
            return;
        }
        for (int j = 0;j<=9;j++) {
            rankBooks_more.add(rankBooks.get(sum));
            sum++;
        }

    }

}
