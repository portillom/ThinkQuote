package com.readthinkwrite.android.thinkquote;

/**
 * Created by USER on 7/19/18.
 */

public class Quote {

    private int mTextResId;

    public Quote(int textResId){
        mTextResId = textResId;
    }


    public int getTextResId() {
        return mTextResId;
    }
}
