package com.example.katherine_qj.xiyou_library.IView;

import com.example.katherine_qj.xiyou_library.bean.FavoriteBook;

import java.util.List;

/**
 * Created by Katherine-qj on 2016/12/17.
 */

public interface IMyFavoriteActivity {
    public void ShowSomeMassage(String massage);
    public void SetFavoriteNull();
    public void SetFavoriteLoding();
    public void SetFavoriteSuccess(List<FavoriteBook> favoriteBooks);
    public void SetFavoriteFaild();
}
