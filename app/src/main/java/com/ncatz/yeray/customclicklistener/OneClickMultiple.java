package com.ncatz.yeray.customclicklistener;

import android.view.View;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Class that implements OnClickListener and avoid calling more than one time onClick(View v) if it has not finished yet
 *
 * @author yeray697
 * @version 1.0
 */
public class OneClickMultiple implements Serializable{
    private boolean clicked;
    private HashMap<String,View.OnClickListener> listeners;

    /**
     * It will not be able to be clicked again until you call setClicked(false)
     */
    public OneClickMultiple() {
        this.clicked = false;
        this.listeners = new HashMap<>();
    }

    public View.OnClickListener addListener(String viewName, final OneClickMultipleListener listener){
        View.OnClickListener newListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!clicked) {
                    clicked = true;
                    listener.onClick();
                } else {
                    listener.onDoubleClick();
                }
            }
        };
        listeners.put(viewName,newListener);
        return newListener;
    }

    public View.OnClickListener getListener(String viewName){
        return listeners.get(viewName);
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


}