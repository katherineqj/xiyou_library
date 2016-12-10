package com.example.katherine_qj.xiyou_library.presenter;

import android.os.Handler;
import android.os.Message;

import com.example.katherine_qj.xiyou_library.IView.IgetDetail;
import com.example.katherine_qj.xiyou_library.model.JsonUtils;
import com.example.katherine_qj.xiyou_library.model.NewsAndNoticeModelAndDetailAndDetail;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Katherine-qj on 2016/12/10.
 */

public class getDetailPresenter {
    private IgetDetail igetDetail;
    private NewsAndNoticeModelAndDetailAndDetail detailModel;
    private String Title;
    private String Publisher;
    private String Date;
    private String Passage;
    private JsonUtils jsonUtils = new JsonUtils();
    public getDetailPresenter(IgetDetail igetDetail){
        this.igetDetail = igetDetail;
        detailModel = new NewsAndNoticeModelAndDetailAndDetail();
    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:{
                    getMassageByJson((String)msg.obj);
                    igetDetail.showLayoutDetailSuccess();
                    igetDetail.show(Title,Publisher,Date,Passage);
                    break;
                }
                case 1:{
                    igetDetail.ShowLayoutDetailFaild();
                    igetDetail.showSomeError("网络错误");
                    break;
                }

            }
        }
    };

    public void getMassageByJson(String jsonString){
        if (jsonUtils.JsonGetSuccessFlag(jsonString)){
            String Detail = jsonUtils.JsonGetNewsDetail(jsonString);
            try {
                JSONObject jsonObject = new JSONObject(Detail);
                Title = jsonObject.getString("Title");
                Publisher  = jsonObject.getString("Publisher");
                Date = jsonObject.getString("Date");
                Passage = jsonObject.getString("Passage");
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }else {
            igetDetail.ShowLayoutDetailFaild();
            igetDetail.showSomeError("接口挂了还是什么鬼的我也不叽到啊");
        }

    }
    public void getDetail(String type,String ID){
        detailModel.getDetailJsonString(type,ID,handler);

    }
}
