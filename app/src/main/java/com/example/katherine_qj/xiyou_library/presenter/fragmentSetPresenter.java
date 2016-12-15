package com.example.katherine_qj.xiyou_library.presenter;

import com.example.katherine_qj.xiyou_library.IView.IfragmentSet;
import com.example.katherine_qj.xiyou_library.bean.User;

/**
 * Created by Katherine-qj on 2016/12/3.
 */

public class FragmentSetPresenter {
    User user = User.getInstance();
    private IfragmentSet ifragmentSet;
    public FragmentSetPresenter(IfragmentSet ifragmentSet){
        this.ifragmentSet = ifragmentSet;
    }
    public void loginOut(){
        user.setOnline(false);
        ifragmentSet.showMassageOut();
    }
}
