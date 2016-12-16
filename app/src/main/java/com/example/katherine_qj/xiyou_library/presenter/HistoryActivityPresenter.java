package com.example.katherine_qj.xiyou_library.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;


import com.example.katherine_qj.xiyou_library.IModel.IHistoryModel;
import com.example.katherine_qj.xiyou_library.IView.IMyHistoryActivity;
import com.example.katherine_qj.xiyou_library.bean.HistoryBook;
import com.example.katherine_qj.xiyou_library.bean.RentBook;
import com.example.katherine_qj.xiyou_library.bean.User;
import com.example.katherine_qj.xiyou_library.model.HistoryModel;
import com.example.katherine_qj.xiyou_library.model.RentActivityModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Katherine-qj on 2016/12/16.
 */

public class HistoryActivityPresenter {
    private IMyHistoryActivity iHistoryActivity;
    private HistoryModel historyModel;
    private List<HistoryBook> list_histor;
    private HistoryBook historyBook;
    private User user = User.getInstance();
    private Context context;
    public HistoryActivityPresenter(Context context,IMyHistoryActivity iHistoryActivity) {
        this.iHistoryActivity = iHistoryActivity;
        this.context = context;
        historyModel  = new HistoryModel();
    }
    Handler handler  =new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    Log.e("rent",msg.obj.toString());
                    if (  getHistorylistByJson(msg.obj.toString())!=null)
                        iHistoryActivity.SetHistorySuccess( getHistorylistByJson(msg.obj.toString()));
                    break;
                case 1:
                    iHistoryActivity.SetHistoryFaild();
                    iHistoryActivity.ShowSomeMassage("网络错误");
            }
        }
    };

    public void getJsonString(){
        historyModel.getHistoryJsonString(user.getSession(),handler);
    }

    public List<HistoryBook> getHistorylistByJson(String jsonString){
        list_histor = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            if (jsonObject.optBoolean("Result")||jsonObject.optString("Detail").equals("NO_RECORD")){
                JSONArray jsonArray = jsonObject.getJSONArray("Detail");
                for (int i = 0;i<jsonArray.length();i++){
                    historyBook =new HistoryBook();
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    historyBook.setTitle(jsonObject1.optString("Title"));
                    historyBook.setBarcode(jsonObject1.optString("Barcode"));
                    historyBook.setType(jsonObject1 .optString("Type"));
                    historyBook.setDate(jsonObject1.optString("Date"));
                    list_histor.add(historyBook);
                }
            }
            else {
                list_histor = null;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list_histor;
    }


}
