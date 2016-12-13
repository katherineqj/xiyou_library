package com.example.katherine_qj.xiyou_library.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Katherine-qj on 2016/12/12.
 */

public  class DBService  extends SQLiteOpenHelper{
    private final static int VERSION = 1;
    private final static  String DATABASE_NAME = "xiyoulibrary.db";
    public static final  String  CREATE_PUZZLE_PHOTO = "create table SearchBook(key integer primary key,ID text ,Title text,Author text)";
    public  DBService(Context context){

        this(context,DATABASE_NAME,null,VERSION);
    }
    public DBService(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_PUZZLE_PHOTO);
        Log.e("dbs","建表成功");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
