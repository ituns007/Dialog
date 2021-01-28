package org.ituns.android.dialog;

public abstract class Dialog {

    public abstract void show();

    public abstract void dismiss();

    public static TipsFactory tips() {
        return new TipsFactory();
    }

    public static Dialog message() {
        return null;
    }

    public static Dialog input() {
        return null;
    }
}