package org.ituns.android.dialog;

public abstract class ProgressStyle {

    public abstract int layoutId();

    public static final ProgressStyle DEFAULT = new ProgressStyle() {
        @Override
        public int layoutId() {
            return R.layout.ituns_dialog_progress;
        }
    };
}
