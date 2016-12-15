package com.example.katherine_qj.xiyou_library.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.LinearLayout;

import com.example.katherine_qj.xiyou_library.IView.IRentActivity;
import com.example.katherine_qj.xiyou_library.bean.RentBook;
import com.example.katherine_qj.xiyou_library.bean.User;
import com.example.katherine_qj.xiyou_library.model.RentActivityModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Katherine-qj on 2016/12/15.
 */

public class RentActivityPresenter {
    private IRentActivity iRentActivity;
    private RentActivityModel rentActivityModel;
    private List<RentBook> list_rent;
    private RentBook rentBook;
    private User user = User.getInstance();
    private Context context;
    public RentActivityPresenter(Context context,IRentActivity iRentActivity) {
        this.iRentActivity = iRentActivity;
        this.context = context;
        rentActivityModel  = new RentActivityModel();

    }
    Handler handler  =new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    Log.e("rent",msg.obj.toString());
                    if (  getRentlistByJson(msg.obj.toString())!=null)
                        iRentActivity.SetRentSuccess( getRentlistByJson(msg.obj.toString()));
                    break;
                case 1:
                    iRentActivity.SetRentFaild();
                    iRentActivity.ShowSomeMassage("网络错误");
            }
        }
    };
    public void getJsonString(){
        rentActivityModel.getRentJsonString(user.getSession(),handler);
    }
    public List<RentBook> getRentlistByJson(String jsonString){
        list_rent = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            if (jsonObject.optBoolean("Result")||jsonObject.optString("Detail").equals("NO_RECORD")){
                JSONArray jsonArray = jsonObject.getJSONArray("Detail");
                for (int i = 0;i<jsonArray.length();i++){
                    rentBook =new RentBook();
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    rentBook.setTitle(jsonObject1.optString("Title"));
                    rentBook.setBarcode(jsonObject1.optString("Barcode"));
                    rentBook.setDepartment(jsonObject.optString("Department"));
                    rentBook.setState(jsonObject1.optString("State"));
                    rentBook.setDate(jsonObject1.optString("Date"));
                    rentBook.setCanRenew(jsonObject1.optString("CanRenew"));
                    rentBook.setDepartment_id(jsonObject1.optString("Department_id"));
                    rentBook.setLibrary_id(jsonObject1.optString("Library_id"));
                    list_rent.add(rentBook);
                }
            }
            else {
                list_rent = null;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list_rent;
    }

}
