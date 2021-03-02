package org.ituns.android.dialog;

import android.text.TextUtils;

public class Action {
    private String text;
    private Callback callback;

    private Action(String text, Callback callback) {
        this.text = text;
        this.callback = callback;
    }

    public String text() {
        return text;
    }

    public Callback callback() {
        return callback;
    }

    public static Action with(String text) {
        if(!TextUtils.isEmpty(text)) {
            return new Action(text, null);
        }
        return null;
    }

    public static Action with(String text, Callback callback) {
        if(!TextUtils.isEmpty(text)) {
            return new Action(text, callback);
        }
        return null;
    }

}
