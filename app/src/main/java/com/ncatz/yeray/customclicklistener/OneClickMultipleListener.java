package com.ncatz.yeray.customclicklistener;

import android.view.View;

/**
 * Created by yeray697 on 2/03/17.
 */
public interface OneClickMultipleListener {
    /**
     * It works as {@link View.OnClickListener}
     */
    void onClick();

    /**
     * Handle if user click again if method has not finished yet
     */
    void onDoubleClick();
}
