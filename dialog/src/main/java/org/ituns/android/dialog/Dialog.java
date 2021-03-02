package org.ituns.android.dialog;

import androidx.fragment.app.FragmentActivity;

public abstract class Dialog {

    public abstract void show();

    public abstract void dismiss();

    public static ToastBuilder toast(FragmentActivity activity) {
        return new ToastBuilder(activity);
    }

    public static final class ToastBuilder {
        private FragmentActivity activity;
        private ToastStyle style;
        private String text;
        private long duration;
        private boolean darkMode;
        private boolean cancelable;

        public ToastBuilder(FragmentActivity activity) {
            this.activity = activity;
            this.style = ToastStyle.ERROR;
            this.text = "";
            this.duration = 2500;
            this.darkMode = false;
            this.cancelable = false;
        }

        public ToastBuilder style(ToastStyle style) {
            this.style = style;
            return this;
        }

        public ToastBuilder text(String text) {
            this.text = text;
            return this;
        }

        public ToastBuilder duration(long duration) {
            this.duration = duration;
            return this;
        }

        public ToastBuilder darkMode(boolean darkMode) {
            this.darkMode = darkMode;
            return this;
        }

        public ToastBuilder cancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        public Dialog create() {
            return new ToastDialog(activity) {

                @Override
                protected ToastStyle style() {
                    return style;
                }

                @Override
                protected String text() {
                    return text;
                }

                @Override
                protected long duration() {
                    return duration;
                }

                @Override
                protected boolean darkMode() {
                    return darkMode;
                }

                @Override
                protected boolean cancelable() {
                    return cancelable;
                }
            };
        }

        public void show() {
            create().show();
        }
    }

    public static ProgressBuilder progress(FragmentActivity activity) {
        return new ProgressBuilder(activity);
    }

    public static final class ProgressBuilder {
        private FragmentActivity activity;
        private ProgressStyle style;
        private String text;
        private boolean darkMode;
        private boolean cancelable;

        public ProgressBuilder(FragmentActivity activity) {
            this.activity = activity;
        }

        public ProgressBuilder style(ProgressStyle style) {
            this.style = style;
            return this;
        }

        public ProgressBuilder text(String text) {
            this.text = text;
            return this;
        }

        public ProgressBuilder darkMode(boolean darkMode) {
            this.darkMode = darkMode;
            return this;
        }

        public ProgressBuilder cancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        public Dialog create() {
            return new ProgressDialog(activity) {

                @Override
                protected ProgressStyle style() {
                    return ProgressStyle.DEFAULT;
                }

                @Override
                protected String text() {
                    return text;
                }

                @Override
                protected boolean darkMode() {
                    return darkMode;
                }

                @Override
                protected boolean cancelable() {
                    return cancelable;
                }
            };
        }

        public Dialog show() {
            Dialog dialog = create();
            dialog.show();
            return dialog;
        }
    }

    public static ConfirmBuilder confirm(FragmentActivity activity) {
        return new ConfirmBuilder(activity);
    }

    public static final class ConfirmBuilder {
        private FragmentActivity activity;
        private ConfirmStyle style;
        private String title;
        private String content;
        private Action negative;
        private Action neutral;
        private Action positive;
        private boolean darkMode;
        private boolean cancelable;

        public ConfirmBuilder(FragmentActivity activity) {
            this.activity = activity;
        }

        public ConfirmBuilder style(ConfirmStyle style) {
            this.style = style;
            return this;
        }

        public ConfirmBuilder title(String title) {
            this.title = title;
            return this;
        }

        public ConfirmBuilder content(String content) {
            this.content = content;
            return this;
        }

        public ConfirmBuilder negative(Action negative) {
            this.negative = negative;
            return this;
        }

        public ConfirmBuilder neutral(Action neutral) {
            this.neutral = neutral;
            return this;
        }

        public ConfirmBuilder positive(Action positive) {
            this.positive = positive;
            return this;
        }

        public ConfirmBuilder darkMode(boolean darkMode) {
            this.darkMode = darkMode;
            return this;
        }

        public ConfirmBuilder cancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        public Dialog create() {
            return new ConfirmDialog(activity) {
                @Override
                protected ConfirmStyle style() {
                    return style;
                }

                @Override
                protected String title() {
                    return title;
                }

                @Override
                protected String content() {
                    return content;
                }

                @Override
                protected Action negative() {
                    return negative;
                }

                @Override
                protected Action neutral() {
                    return neutral;
                }

                @Override
                protected Action positive() {
                    return positive;
                }

                @Override
                protected boolean darkMode() {
                    return darkMode;
                }

                @Override
                protected boolean cancelable() {
                    return cancelable;
                }
            };
        }

        public Dialog show() {
            Dialog dialog = create();
            dialog.show();
            return dialog;
        }
    }

    public static Dialog input() {
        return null;
    }
}