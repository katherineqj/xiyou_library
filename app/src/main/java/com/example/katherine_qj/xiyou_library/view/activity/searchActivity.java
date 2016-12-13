package com.example.katherine_qj.xiyou_library.view.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.example.katherine_qj.xiyou_library.IView.IsearchActivity;
import com.example.katherine_qj.xiyou_library.R;
import com.example.katherine_qj.xiyou_library.bean.searchbook;
import com.example.katherine_qj.xiyou_library.httpUtils.GetHttpResponseString;
import com.example.katherine_qj.xiyou_library.model.DBService;
import com.example.katherine_qj.xiyou_library.model.ToastMassage;
import com.example.katherine_qj.xiyou_library.model.searchRecycleViewAdapter;
import com.example.katherine_qj.xiyou_library.presenter.searchActivityPresenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Katherine-qj on 2016/12/10.
 */

public class searchActivity extends Activity implements IsearchActivity {
    public static int key = 0;
    private Intent intent;
    private ImageButton searchBack;
    private EditText searchEditText;
    private RecyclerView searchRecyclerView;
    private RelativeLayout searchNothing;
    private RelativeLayout searchFaild;
    private RelativeLayout searchLoding;
    private List<searchbook> listSearch;
    private List<searchbook> historySearch;
    private searchRecycleViewAdapter searchRecycleViewAdapter;
    public searchActivityPresenter searchActivitypresenter;
    private ToastMassage toastMassage = new ToastMassage();
    private DBService dbService;
    private SQLiteDatabase database;
    private Cursor cursor;
    private boolean ishistory = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
    }

    public void initView() {
        Great_DBS();
        intent = new Intent();
        intent.setClass(this,BookDetailActivity.class);
        searchActivitypresenter = new searchActivityPresenter(this, getApplicationContext());
        listSearch = new ArrayList<>();
        historySearch = new ArrayList<>();
        searchBack = (ImageButton) findViewById(R.id.search_back);
        searchBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        searchEditText = (EditText) findViewById(R.id.search_edittext);
        searchLoding = (RelativeLayout)findViewById(R.id.layout_search_loding);
        searchRecyclerView = (RecyclerView) findViewById(R.id.search_recycleview);

        searchRecyclerView.setLayoutManager(new LinearLayoutManager(searchRecyclerView.getContext()));
        searchFaild = (RelativeLayout) findViewById(R.id.layout_search_faild);
        searchNothing = (RelativeLayout) findViewById(R.id.layout_search_nothing);
        listSearch = gethistoryList();
        Collections.reverse(listSearch);
        setRecycleView(listSearch);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                if (s != null && s.toString().length() > 0) {
                    ishistory = false;
                    searchActivitypresenter.getSearchList(s.toString());
                    searchLoding();
                } else {
                    ishistory = true;
                    listSearch = gethistoryList();
                    Collections.reverse(listSearch);
                    setRecycleView(listSearch);

                }
            }
        });
    }

    @Override
    public void showSomeMassage(String massage) {
        toastMassage.showMassage(massage, getApplicationContext());
    }

    @Override
    public void searchFaild() {
        searchRecyclerView.setVisibility(View.INVISIBLE);
        searchFaild.setVisibility(View.VISIBLE);
        searchNothing.setVisibility(View.INVISIBLE);
        searchLoding.setVisibility(View.INVISIBLE);

    }

    @Override
    public void searchNothing() {
        searchRecyclerView.setVisibility(View.INVISIBLE);
        searchFaild.setVisibility(View.INVISIBLE);
        searchNothing.setVisibility(View.VISIBLE);
        searchLoding.setVisibility(View.INVISIBLE);
    }

    @Override
    public void searchSuccess() {
        searchRecyclerView.setVisibility(View.VISIBLE);
        searchFaild.setVisibility(View.INVISIBLE);
        searchNothing.setVisibility(View.INVISIBLE);
        searchLoding.setVisibility(View.INVISIBLE);
    }
    public void searchLoding(){
        searchRecyclerView.setVisibility(View.INVISIBLE);
        searchFaild.setVisibility(View.INVISIBLE);
        searchNothing.setVisibility(View.INVISIBLE);
        searchLoding.setVisibility(View.VISIBLE);
    }

    @Override
    public void setRecycleView(List<searchbook> list1) {
        //设置RecycleView
        Log.e("list",list1.size()+"");
        searchRecyclerView.setAdapter(searchRecycleViewAdapter = new searchRecycleViewAdapter(this, list1));
        searchRecycleViewAdapter.setOnItemClickListener(new searchRecycleViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, searchbook searchbook) {
                intent.putExtra("ID",searchbook.getID());
                saveList(searchbook.getID(),searchbook.getTitle(),searchbook.getAuthor());
                startActivity(intent);
            }
        });
        searchRecycleViewAdapter.setOnLongItemClickListener(new searchRecycleViewAdapter.OnRecyclerViewLongItemClickListener() {
            @Override
            public void onLongItemClick(View view, searchbook searchbook,int position) {
                if (ishistory) {
                    Log.e("searchbook", searchbook.getAuthor());
                    intent.putExtra("ID", searchbook.getID());
                    toastMassage.showMassage(searchbook.getID() + "", getApplicationContext());
                    int deleteKey = searchbook.getKey();
                    historySearch.remove(position);
                   searchRecycleViewAdapter.notifyItemRemoved(position);
                   searchRecycleViewAdapter.notifyDataSetChanged();
                   deletesearchlist(deleteKey);
                    //从数据库删除

                }
            }
        });

    }
    public void Great_DBS(){
        dbService = new DBService(getApplicationContext());
        database =  dbService.getWritableDatabase();
    }
    public  void saveList(String id,String title,String author){
        ContentValues values = new ContentValues();

        values.put("ID",id);
        Log.d("dbs",id);
        values.put("Title",title);
        Log.d("dbs",title);
        values.put("Author",author);
        Log.d("dbs",author);
        database = dbService.getWritableDatabase();
        database.insert("SearchBook",null,values);
        database.close();
        dbService.close();
        Log.d("dbs","插入成功");
    }
    public List<searchbook> gethistoryList(){
        List<searchbook> list = new ArrayList<>();
        database  =dbService.getWritableDatabase();
        cursor = database.query("SearchBook",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do{
                int key = cursor.getInt(cursor.getColumnIndex("key"));
                String id = cursor.getString(cursor.getColumnIndex("ID"));
                String title = cursor.getString(cursor.getColumnIndex("Title"));
                String anthor = cursor.getString(cursor.getColumnIndex("Author"));
                searchbook s = new searchbook();
                s.setKey(key);
                s.setID(id);
                s.setTitle(title);
                s.setAuthor(anthor);
                historySearch.add(s);
                Log.d("dbs","查询成功");
            }while (cursor.moveToNext());
            cursor.close();
            database.close();
            dbService.close();
        }
        return historySearch;
    }
    public void deletesearchlist( int key){
       /* String delete  ="DELETE FROM SearchBook WHERE key="+key;
        database.execSQL(delete);*/

        Great_DBS();

        database.delete("SearchBook","key=?",new String[]{String.valueOf(key)});

    //    database.delete("delete","where key="+key,null);
     //   database.close();
     //   dbService.close();
    }
}
