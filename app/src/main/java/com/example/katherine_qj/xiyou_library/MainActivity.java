package com.example.katherine_qj.xiyou_library;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.katherine_qj.xiyou_library.model.TransfromAnm;
import com.example.katherine_qj.xiyou_library.view.fragment.fragmentHome;
import com.example.katherine_qj.xiyou_library.view.fragment.fragmentMy;
import com.example.katherine_qj.xiyou_library.view.fragment.fragmentSet;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager fragment_viewpager;
    private RadioGroup radioGroup;
    private RadioButton radioButton_home;
    private RadioButton radioButton_my;
    private RadioButton radioButton_set;

    private List<Fragment> fragmentList;
    private fragmentHome fragment_home;
    private fragmentMy fragment_my;
    private fragmentSet fragment_set;
    private FragmentPagerAdapter fragmentPagerAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initViewPager();
    }
    public void  initView(){
        fragment_viewpager = (ViewPager)findViewById(R.id.fragment_viewpager);
        radioGroup = (RadioGroup)findViewById(R.id.radio_group);
        radioButton_home = (RadioButton)findViewById(R.id.radiobutton_home);
        radioButton_my = (RadioButton)findViewById(R.id.radiobutton_my);
        radioButton_set = (RadioButton)findViewById(R.id.radiobutton_set);

        fragmentList = new ArrayList<Fragment>();
        fragment_home = new fragmentHome();
        fragment_my = new fragmentMy();
        fragment_set = new fragmentSet();
        fragmentList.add(fragment_home);
        fragmentList.add(fragment_my);
        fragmentList.add(fragment_set);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentPagerAdapter = new FragmentPagerAdapter(fragmentManager) {
            @Override
            public android.support.v4.app.Fragment getItem(int position) {
                return fragmentList.get(position);
            }
            @Override
            public int getCount() {
                return fragmentList.size();
            }
        };
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radiobutton_home:
                        fragment_viewpager.setCurrentItem(0);
                        radioButton_home.setChecked(true);
                        radioButton_home.setTextColor(getResources().getColor(R.color.library_red));
                        radioButton_my.setTextColor(getResources().getColor(R.color.library_norall));
                        radioButton_set.setTextColor(getResources().getColor(R.color.library_norall));
                        break;
                    case R.id.radiobutton_my:
                        fragment_viewpager.setCurrentItem(1);
                        radioButton_my.setChecked(true);
                        radioButton_my.setTextColor(getResources().getColor(R.color.library_red));
                        radioButton_home.setTextColor(getResources().getColor(R.color.library_norall));
                        radioButton_set.setTextColor(getResources().getColor(R.color.library_norall));
                        break;
                    case R.id.radiobutton_set:
                        fragment_viewpager.setCurrentItem(2);
                        radioButton_set.setChecked(true);
                        radioButton_set.setTextColor(getResources().getColor(R.color.library_red));
                        radioButton_my.setTextColor(getResources().getColor(R.color.library_norall));
                        radioButton_home.setTextColor(getResources().getColor(R.color.library_norall));
                        break;
                }

            }
        });


    }
    public void initViewPager(){
        fragment_viewpager.setPageTransformer(true,new TransfromAnm());
        fragment_viewpager.setAdapter(fragmentPagerAdapter);
        fragment_viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        radioButton_home.setChecked(true);
                        radioButton_home.setTextColor(getResources().getColor(R.color.library_red));
                        radioButton_my.setTextColor(getResources().getColor(R.color.library_norall));
                        radioButton_set.setTextColor(getResources().getColor(R.color.library_norall));
                        break;
                    case 1:
                        radioButton_my.setChecked(true);
                        radioButton_my.setTextColor(getResources().getColor(R.color.library_red));
                        radioButton_home.setTextColor(getResources().getColor(R.color.library_norall));
                        radioButton_set.setTextColor(getResources().getColor(R.color.library_norall));
                        break;
                    case 2:
                        radioButton_set.setChecked(true);
                        radioButton_set.setTextColor(getResources().getColor(R.color.library_red));
                        radioButton_my.setTextColor(getResources().getColor(R.color.library_norall));
                        radioButton_home.setTextColor(getResources().getColor(R.color.library_norall));
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }


        });
    }
}
