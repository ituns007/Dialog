package org.ituns.android.dialog;

import android.content.Context;

import androidx.annotation.IdRes;

public class TipsFactory {

    public TipsFactory(Context context) {

    }

    public TipsFactory icon(@IdRes int resId) {
        return this;
    }

    public TipsFactory icon(Icon icon) {
        return this;
    }

    public TipsFactory message(String message) {
        return this;
    }

    public TipsFactory duration(long duration) {
        return this;
    }

    public TipsDialog build() {
        return new TipsDialog(this);
    }
}
