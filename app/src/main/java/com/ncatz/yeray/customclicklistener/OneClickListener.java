package com.ncatz.yeray.customclicklistener;

import android.os.Parcelable;
import android.view.View;

import java.io.Serializable;

/**
 * Class that implements OnClickListener and avoid calling more than one time onClick(View v) if it has not finished yet
 *
 * @author yeray697
 * @version 1.0
 */
public abstract class OneClickListener implements View.OnClickListener, Serializable{
    private boolean clicked;

    /**
     * It will not be able to be clicked again if it has not finished
     */
    public OneClickListener() {
        this.clicked = false;
    }

    @Override
    public void onClick(View v) {
        if (!clicked) {
            clicked = true;
            onOneClick();
        } else {
            onDoubleClick();
        }
    }

    /**
     * Check if now it is clicked
     * @return Return true if it is clicked
     */
    public boolean isClicked() {
        return clicked;
    }

    /**
     * Set if it is clicked.
     * @param clicked If you pass 'false' you will be able to click again
     */
    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    /**
     * It works as {@link android.view.View.OnClickListener}
     */
    protected abstract void onOneClick();

    /**
     * Handle if user click again if method has not finished yet
     */
    protected void onDoubleClick(){}
}