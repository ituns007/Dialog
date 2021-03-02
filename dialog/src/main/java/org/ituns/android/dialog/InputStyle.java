package org.ituns.android.dialog;

public abstract class InputStyle {

    public abstract int layoutId();

    public abstract int lineDrawable();

    public abstract int leftBtnDrawable();

    public abstract int centerBtnDrawable();

    public abstract int rightBtnDrawable();

    public abstract int wholeBtnDrawable();

    public static final InputStyle DEFAULT = new InputStyle() {
        @Override
        public int layoutId() {
            return R.layout.ituns_dialog_input;
        }

        @Override
        public int lineDrawable() {
            return R.drawable.ituns_selector_line;
        }

        @Override
        public int leftBtnDrawable() {
            return R.drawable.ituns_selector_btn_left;
        }

        @Override
        public int centerBtnDrawable() {
            return R.drawable.ituns_selector_btn_center;
        }

        @Override
        public int rightBtnDrawable() {
            return R.drawable.ituns_selector_btn_right;
        }

        @Override
        public int wholeBtnDrawable() {
            return R.drawable.ituns_selector_btn_whole;
        }
    };
}
