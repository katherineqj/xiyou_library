package com.example.katherine_qj.xiyou_library.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.katherine_qj.xiyou_library.IView.IMyFavoriteActivity;
import com.example.katherine_qj.xiyou_library.bean.FavoriteBook;
import com.example.katherine_qj.xiyou_library.bean.User;
import com.example.katherine_qj.xiyou_library.model.FavoriteModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Katherine-qj on 2016/12/17.
 */

public class FavorityActivityPresenter {
    private IMyFavoriteActivity iMyFavoriteActivity;
    private FavoriteModel favoriteModel;
    private Context context;
    private List<FavoriteBook> list_favorite;
    private FavoriteBook favoriteBook;
    private User user = User.getInstance();
    public FavorityActivityPresenter(Context context,IMyFavoriteActivity iMyFavoriteActivity){
        this.iMyFavoriteActivity = iMyFavoriteActivity;
        this.context = context;
        favoriteModel = new FavoriteModel();
    }
    Handler handler  =new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    Log.e("rent",msg.obj.toString());
                    if (  getFavoritelistByJson(msg.obj.toString())!=null)
                        iMyFavoriteActivity.SetFavoriteSuccess( getFavoritelistByJson(msg.obj.toString()));
                    break;
                case 1:
                    iMyFavoriteActivity.SetFavoriteFaild();
                    iMyFavoriteActivity.ShowSomeMassage("网络错误");
            }
        }
    };
    public void getJsonString(){
        favoriteModel.getRentJsonString(user.getSession(),handler);
    }
    public List<FavoriteBook> getFavoritelistByJson(String jsonString){
        list_favorite = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            if (jsonObject.optBoolean("Result")||jsonObject.optString("Detail").equals("NO_RECORD")){
                JSONArray jsonArray = jsonObject.getJSONArray("Detail");
                for (int i = 0;i<jsonArray.length();i++){
                    favoriteBook =new FavoriteBook();
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    favoriteBook.setTitle(jsonObject1.optString("Title"));
                    favoriteBook.setID(jsonObject1.optString("ID"));
                    favoriteBook.setAuthor(jsonObject1 .optString("Author"));
                    favoriteBook.setPub(jsonObject1.optString("Pub"));
                    favoriteBook.setISBM(jsonObject1.optString("ISBN"));
                    favoriteBook.setSort(jsonObject1.optString("Sort"));
                    if (jsonObject1.length()==7){
                        JSONObject jsonObject2= jsonObject1.optJSONObject("Images");
                        favoriteBook.setImage(jsonObject2.optString("medium"));
                    }
                    list_favorite.add(favoriteBook);
                }
            }
            else {
                list_favorite = null;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list_favorite;
    }


}
