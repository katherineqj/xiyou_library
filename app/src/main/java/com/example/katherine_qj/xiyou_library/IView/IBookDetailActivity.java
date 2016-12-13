package com.example.katherine_qj.xiyou_library.IView;

import com.example.katherine_qj.xiyou_library.bean.BookDetail;

/**
 * Created by Katherine-qj on 2016/12/13.
 */

public interface IBookDetailActivity {
    public void showBookDetailLoding();
    public void showBookDetailFaild();
    public void showBookDetailSuccess();
    public void setBookDetailMassage(BookDetail bookDetail);
    public void showSomeMassage(String massage);
}
