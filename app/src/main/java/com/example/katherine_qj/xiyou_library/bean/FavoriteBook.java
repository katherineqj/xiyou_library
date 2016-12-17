package com.example.katherine_qj.xiyou_library.bean;

/**
 * Created by Katherine-qj on 2016/12/17.
 */

public class FavoriteBook {
    private String Title;
    private String Pub;
    private String Sort;
    private String Author;
    private String ISBM;
    private String ID;
    private String Image;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getISBM() {
        return ISBM;
    }

    public void setISBM(String ISBM) {
        this.ISBM = ISBM;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getSort() {
        return Sort;
    }

    public void setSort(String sort) {
        Sort = sort;
    }

    public String getPub() {
        return Pub;
    }

    public void setPub(String pub) {
        Pub = pub;
    }
}
