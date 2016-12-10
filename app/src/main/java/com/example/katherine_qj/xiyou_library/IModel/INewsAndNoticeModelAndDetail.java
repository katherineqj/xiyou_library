package com.example.katherine_qj.xiyou_library.IModel;

import android.os.Handler;

import com.example.katherine_qj.xiyou_library.bean.News;

import java.util.List;

/**
 * Created by Katherine-qj on 2016/12/7.
 */

public interface INewsAndNoticeModelAndDetail {
    public void getNewsJsonString(String type, int page, Handler handler);
    public void getDetailJsonString(String type,String ID,Handler handler);
}
