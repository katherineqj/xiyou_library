package com.example.katherine_qj.xiyou_library.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.katherine_qj.xiyou_library.IView.IgetDetail;
import com.example.katherine_qj.xiyou_library.R;
import com.example.katherine_qj.xiyou_library.model.ToastMassage;
import com.example.katherine_qj.xiyou_library.presenter.getDetailPresenter;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Katherine-qj on 2016/12/10.
 */

public class getDetailActivity extends Activity implements IgetDetail{
    private String detailType;
    private String detailId;
    private ImageButton detailBack;
    private LinearLayout layouGetDetaillSuccess;
    private RelativeLayout layoutGetDetailFaild;
    private RelativeLayout layoutGetDetailing;
    private TextView detailTitle;
    private TextView detailPublisher;
    private TextView detailDate;
    private WebView detailPassage;
    private ToastMassage toastMassage = new ToastMassage();
    private getDetailPresenter getDetailPresenter ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getdetail);
        initView();
        getDetailPresenter.getDetail(detailType,detailId);
    }
    public void initView(){
        detailType = getIntent().getStringExtra("type");
        detailId = getIntent().getStringExtra("ID");
        detailBack = (ImageButton)findViewById(R.id.detail_back);
        detailBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        layouGetDetaillSuccess = (LinearLayout)findViewById(R.id.layout_detail_success);
        layoutGetDetailFaild  = (RelativeLayout)findViewById(R.id.layout_detail_faild);
        layoutGetDetailing = (RelativeLayout)findViewById(R.id.layout_detail_ing);
        detailTitle = (TextView)findViewById(R.id.detail_title);
        detailPublisher = (TextView)findViewById(R.id.detail_publisher);
        detailDate = (TextView)findViewById(R.id.detail_date);
        detailPassage = (WebView)findViewById(R.id.detail_passage);
        getDetailPresenter = new getDetailPresenter(this);
    }

    @Override
    public void show(String title, String publisher, String date, String passage) {
        detailTitle.setText(title);
        detailPublisher.setText(publisher);
        detailDate.setText(date);
        detailPassage.loadData(passage,"text/html","utf-8");    }

    @Override
    public void showLayoutDetailSuccess() {
        layouGetDetaillSuccess.setVisibility(View.VISIBLE);
        layoutGetDetailFaild.setVisibility(View.INVISIBLE);
        layoutGetDetailing.setVisibility(View.INVISIBLE);

    }

    @Override
    public void ShowLayoutDetailFaild() {
        layouGetDetaillSuccess.setVisibility(View.INVISIBLE);
        layoutGetDetailFaild.setVisibility(View.VISIBLE);
        layoutGetDetailing.setVisibility(View.INVISIBLE);

    }

    @Override
    public void ShowLayoutDetailing() {
        layouGetDetaillSuccess.setVisibility(View.INVISIBLE);
        layoutGetDetailFaild.setVisibility(View.INVISIBLE);
        layoutGetDetailing.setVisibility(View.VISIBLE);

    }

    @Override
    public void showSomeError(String massage) {
         toastMassage.showMassage(massage,getApplicationContext());
    }

    private String fmtString(String str){
        String notice = "";
        try{
            notice = URLEncoder.encode(str, "utf-8");
        }catch(UnsupportedEncodingException ex){
        }
        return notice;
    }

}
