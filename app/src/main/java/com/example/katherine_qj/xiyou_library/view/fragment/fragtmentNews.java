package com.example.katherine_qj.xiyou_library.view.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.katherine_qj.xiyou_library.R;

/**
 * Created by Katherine-qj on 2016/12/3.
 */

public class fragtmentNews extends Fragment{
    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = (View)inflater.inflate(R.layout.fragment_news,container,false);
        return view;
    }
}
