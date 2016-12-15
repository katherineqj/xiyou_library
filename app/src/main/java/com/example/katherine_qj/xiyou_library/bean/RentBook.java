package com.example.katherine_qj.xiyou_library.bean;

/**
 * Created by Katherine-qj on 2016/12/15.
 */

public class RentBook {
    private String Title;
    private String Barcode;
    private String Department;
    private String State;
    private String Date;
    private String CanRenew;
    private String Department_id;

    public String getLibrary_id() {
        return Library_id;
    }

    public void setLibrary_id(String library_id) {
        Library_id = library_id;
    }

    public String getDepartment_id() {
        return Department_id;
    }

    public void setDepartment_id(String department_id) {
        Department_id = department_id;
    }

    public String getCanRenew() {
        return CanRenew;
    }

    public void setCanRenew(String canRenew) {
        CanRenew = canRenew;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getBarcode() {
        return Barcode;
    }

    public void setBarcode(String barcode) {
        Barcode = barcode;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    private String Library_id;


}
