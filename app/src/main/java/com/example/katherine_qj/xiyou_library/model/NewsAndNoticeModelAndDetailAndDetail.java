package com.example.katherine_qj.xiyou_library.model;

import android.os.Handler;
import android.os.Message;

import com.example.katherine_qj.xiyou_library.IModel.INewsAndNoticeModelAndDetail;
import com.example.katherine_qj.xiyou_library.httpUtils.GetHttpResponseString;

/**
 * Created by Katherine-qj on 2016/12/7.
 */

public class NewsAndNoticeModelAndDetailAndDetail implements INewsAndNoticeModelAndDetail {
    GetHttpResponseString getHttpResponseString = new GetHttpResponseString();
    private String newsJsonString = null;
    @Override
    public void  getNewsJsonString(final String type, final int page ,final Handler handler) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String s = getHttpResponseString.getResponString(getHttpResponseString.getListUrl(type,page));
                if (!s.equals("un")){
                    Message message =handler.obtainMessage();
                    message.what = 0;
                    message.obj = s;
                    handler.sendMessage(message);
                }else {
                    handler.sendEmptyMessageAtTime(1,0);
                }
            }
        }).start();
    }

    @Override
    public void getDetailJsonString(final String type, final String ID,final Handler handler) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String s = getHttpResponseString.getResponString(getHttpResponseString.getDetailUrl(type,ID));
                if (!s.equals("un")){
                    Message message =handler.obtainMessage();
                    message.what = 0;
                    message.obj = s;
                    handler.sendMessage(message);
                }else {
                    handler.sendEmptyMessageAtTime(1,0);
                }
            }
        }).start();
    }

}
