package com.example.katherine_qj.xiyou_library.model;

import android.os.Handler;
import android.os.Message;

import com.example.katherine_qj.xiyou_library.IModel.IRankModel;
import com.example.katherine_qj.xiyou_library.httpUtils.GetHttpResponseString;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by Katherine-qj on 2016/12/17.
 */

public class RankModel implements IRankModel {
    @Override
    public void getRankJsonString(final Handler handler) {
        RequestBody formBody = new FormEncodingBuilder()
                .add("size", "50")
                .build();
        final Request request = new Request.Builder()
                .url("https://api.xiyoumobile.com/xiyoulibv2/book/rank")
                .post(formBody)
                .tag("rank")
                .build();
        Call call = GetHttpResponseString.client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                e.printStackTrace();
                handler.sendEmptyMessage(1);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String result = response.body().string();
                Message message = Message.obtain();
                message.what = 0;
                message.obj = result;
                handler.sendMessage(message);
            }
        });
    }
}