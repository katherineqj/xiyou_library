package com.example.katherine_qj.xiyou_library.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.katherine_qj.xiyou_library.IView.IMyRankActivity;
import com.example.katherine_qj.xiyou_library.bean.RankBook;
import com.example.katherine_qj.xiyou_library.model.RankModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Katherine-qj on 2016/12/17.
 */

public class RankActivityPresenter {
    private IMyRankActivity iMyRankActivity;
    private RankModel rankModel;
    private Context context;
    private List<RankBook> list_rank;
    private RankBook rankBook;
    public RankActivityPresenter (IMyRankActivity iMyRankActivity,Context context){
        this.context = context;
        this.iMyRankActivity = iMyRankActivity;
        rankModel = new RankModel();
    }
    Handler handler  =new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    Log.e("rent",msg.obj.toString());
                    if (  getRanklistByJson(msg.obj.toString())!=null)
                        iMyRankActivity.SetRankSuccess( getRanklistByJson(msg.obj.toString()));
                    break;
                case 1:
                    iMyRankActivity.SetRankFaild();
                    iMyRankActivity.ShowSomeMassage("网络错误");
            }
        }
    };
    public void getJsonString(){
        rankModel.getRankJsonString(handler);
    }
    public List<RankBook> getRanklistByJson(String jsonString){
        list_rank = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            if (jsonObject.optBoolean("Result")||jsonObject.optString("Detail").equals("NO_RECORD")){
                JSONArray jsonArray = jsonObject.getJSONArray("Detail");
                for (int i = 0;i<jsonArray.length();i++){
                    rankBook =new RankBook();
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    rankBook.setTitle(jsonObject1.optString("Title"));
                    rankBook.setID(jsonObject1.optString("ID"));
                    rankBook.setSort(jsonObject1 .optString("Sort"));
                    rankBook.setBorNum(jsonObject1.optString("BorNum"));
                    rankBook.setRank(jsonObject1.optString("Rank"));
                    list_rank.add(rankBook);
                }
            }
            else {
                list_rank = null;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list_rank;
    }
}
