package org.ituns.android.dialog;

import android.text.TextUtils;

public class Button {
    private String text;
    private Watcher watcher;
    private Callback callback;

    private Button(String text, Watcher watcher, Callback callback) {
        this.text = text;
        this.watcher = watcher;
        this.callback = callback;
    }

    public String text() {
        return text;
    }

    public Watcher watcher() {
        return watcher;
    }

    public Callback callback() {
        return callback;
    }

    public static Button with(String text) {
        if(!TextUtils.isEmpty(text)) {
            return new Button(text, null, null);
        }
        return null;
    }

    public static Button with(String text, Watcher watcher) {
        if(!TextUtils.isEmpty(text)) {
            return new Button(text, watcher, null);
        }
        return null;
    }

    public static Button with(String text, Callback callback) {
        if(!TextUtils.isEmpty(text)) {
            return new Button(text, null, callback);
        }
        return null;
    }
}
