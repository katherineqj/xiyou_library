package com.example.katherine_qj.xiyou_library.model;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.katherine_qj.xiyou_library.IModel.IBookDetailModel;
import com.example.katherine_qj.xiyou_library.httpUtils.GetHttpResponseString;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by Katherine-qj on 2016/12/14.
 */

public class BookDetailModel implements IBookDetailModel {
    @Override
    public void getSearchListJsonString(String id, final Handler handler) {
        RequestBody formBody = new FormEncodingBuilder()
                .add("", "")
                .build();
        final Request request = new Request.Builder()
                .url("https://api.xiyoumobile.com/xiyoulibv2/book/detail/id/"+id)
                .post(formBody)
                .tag("BookDetail")
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
