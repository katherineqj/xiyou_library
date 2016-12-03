package com.example.katherine_qj.xiyou_library.presenter;

import com.example.katherine_qj.xiyou_library.IView.IfragmentSet;
import com.example.katherine_qj.xiyou_library.bean.User;

/**
 * Created by Katherine-qj on 2016/12/3.
 */

public class fragmentSetPresenter {
    User user = User.getInstance();
    private IfragmentSet ifragmentSet;
    public fragmentSetPresenter(IfragmentSet ifragmentSet){
        this.ifragmentSet = ifragmentSet;
    }
    public void loginOut(){
        user = null;
        ifragmentSet.showMassageOut();
    }
}
