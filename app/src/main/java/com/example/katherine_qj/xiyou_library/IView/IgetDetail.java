package com.example.katherine_qj.xiyou_library.IView;

/**
 * Created by Katherine-qj on 2016/12/10.
 */

public interface IgetDetail {
    public void show(String title,String publisher,String date,String passage);
    public void showLayoutDetailSuccess();
    public void ShowLayoutDetailFaild();
    public void ShowLayoutDetailing();
    public void showSomeError(String massage);
}
