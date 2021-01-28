package org.ituns.android.dialog;

import androidx.annotation.IdRes;

public class  Icon {
    public static final Icon ERROR = new Icon(0);
    public static final Icon WARNING = new Icon(1);
    public static final Icon COMPLETE = new Icon(2);

    @IdRes
    private final int resId;

    private Icon(int resId) {
        this.resId = resId;
    }

    int resId() {
        return resId;
    }

    public static Icon create(@IdRes int resId) {
        return new Icon(resId);
    }
}
