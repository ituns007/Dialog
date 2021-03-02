package org.ituns.android.dialog;

public abstract class ToastStyle {

    public abstract int layoutId();

    public abstract int iconDrawable();

    public static final ToastStyle ERROR = new ToastStyle() {
        @Override
        public int layoutId() {
            return R.layout.ituns_dialog_toast;
        }

        @Override
        public int iconDrawable() {
            return R.drawable.ituns_selector_toast_error;
        }
    };

    public static final ToastStyle FINISH = new ToastStyle() {
        @Override
        public int layoutId() {
            return R.layout.ituns_dialog_toast;
        }

        @Override
        public int iconDrawable() {
            return R.drawable.ituns_selector_toast_finish;
        }
    };

    public static final ToastStyle WARNNING = new ToastStyle() {
        @Override
        public int layoutId() {
            return R.layout.ituns_dialog_toast;
        }

        @Override
        public int iconDrawable() {
            return R.drawable.ituns_selector_toast_warning;
        }
    };
}
