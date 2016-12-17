package com.example.katherine_qj.xiyou_library.IView;

import com.example.katherine_qj.xiyou_library.bean.RankBook;

import java.util.List;

/**
 * Created by Katherine-qj on 2016/12/17.
 */

public interface IMyRankActivity {
    public void ShowSomeMassage(String massage);
    public void SetRankLoding();
    public void SetRankSuccess(List<RankBook> rankBooks);
    public void SetRankFaild();
}
