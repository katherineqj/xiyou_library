package com.example.katherine_qj.xiyou_library.IView;

import com.example.katherine_qj.xiyou_library.bean.HistoryBook;
import com.example.katherine_qj.xiyou_library.bean.RentBook;

import java.util.List;

/**
 * Created by Katherine-qj on 2016/12/16.
 */

public interface IMyHistoryActivity {
    public void ShowSomeMassage(String massage);
    public void SetHistoryNull();
    public void SetHistoryLoding();
    public void SetHistorySuccess(List<HistoryBook> historyBooks);
    public void SetHistoryFaild();
}
