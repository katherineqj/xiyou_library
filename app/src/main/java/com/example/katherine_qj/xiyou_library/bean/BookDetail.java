package com.example.katherine_qj.xiyou_library.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Katherine-qj on 2016/12/13.
 */

public class BookDetail {
    private String Sort;
    private String Anthor;
    private String Form;
    private int Avaliable;
    private int RentTimes;
    private int FavTimes;
    private int BrowseTimes;
    private int Total;
    private String BookDetailTitle;
    private String Subject;
    private String Pub;
    private String Author_Info;
    private String Summary;
    private List<CirrculationInfo> list_cirrculationinfo = new ArrayList<>();
    private List<ReferBooks> list_referbooks = new ArrayList<>();

    public List<CirrculationInfo> getList_cirrculationinfo() {
        return list_cirrculationinfo;
    }

    public void setList_cirrculationinfo(List<CirrculationInfo> list_cirrculationinfo) {
        this.list_cirrculationinfo = list_cirrculationinfo;
    }

    public List<ReferBooks> getList_referbooks() {
        return list_referbooks;
    }

    public void setList_referbooks(List<ReferBooks> list_referbooks) {
        this.list_referbooks = list_referbooks;
    }

    public String getSort() {
        return Sort;
    }

    public void setSort(String sort) {
        Sort = sort;
    }

    public String getAnthor() {
        return Anthor;
    }

    public void setAnthor(String anthor) {
        Anthor = anthor;
    }

    public String getForm() {
        return Form;
    }

    public void setForm(String form) {
        Form = form;
    }

    public int getRentTimes() {
        return RentTimes;
    }

    public void setRentTimes(int rentTimes) {
        RentTimes = rentTimes;
    }

    public int getFavTimes() {
        return FavTimes;
    }

    public void setFavTimes(int favTimes) {
        FavTimes = favTimes;
    }

    public int getAvaliable() {
        return Avaliable;
    }

    public void setAvaliable(int avaliable) {
        Avaliable = avaliable;
    }

    public int getBrowseTimes() {
        return BrowseTimes;
    }

    public void setBrowseTimes(int browseTimes) {
        BrowseTimes = browseTimes;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public int getTotal() {
        return Total;
    }

    public void setTotal(int total) {
        Total = total;
    }

    public String getPub() {
        return Pub;
    }

    public void setPub(String pub) {
        Pub = pub;
    }

    public String getAuthor_Info() {
        return Author_Info;
    }

    public void setAuthor_Info(String author_Info) {
        Author_Info = author_Info;
    }

    public String getSummary() {
        return Summary;
    }

    public void setSummary(String summary) {
        Summary = summary;
    }

    public String getBookDetailTitle() {
        return BookDetailTitle;
    }

    public void setBookDetailTitle(String bookDetailTitle) {
        BookDetailTitle = bookDetailTitle;
    }
}
