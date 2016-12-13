package com.example.katherine_qj.xiyou_library.bean;

/**
 * Created by Katherine-qj on 2016/12/13.
 */

public class CirrculationInfo {
    private String Barcode;
    private String Sort;
    private String Dapartment;
    private String Status;
    private String Date;

    public String getBarcode() {
        return Barcode;
    }

    public void setBarcode(String barcode) {
        Barcode = barcode;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getDapartment() {
        return Dapartment;
    }

    public void setDapartment(String dapartment) {
        Dapartment = dapartment;
    }

    public String getSort() {
        return Sort;
    }

    public void setSort(String sort) {
        Sort = sort;
    }
}
