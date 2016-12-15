package com.example.katherine_qj.xiyou_library.IView;

import com.example.katherine_qj.xiyou_library.bean.RentBook;

import java.util.List;

/**
 * Created by Katherine-qj on 2016/12/15.
 */

public interface IRentActivity {
    public void ShowSomeMassage(String massage);
    public void SetRentNull();
    public void SetRentLoding();
    public void SetRentSuccess(List<RentBook> rentBooks);
    public void SetRentFaild();
}
