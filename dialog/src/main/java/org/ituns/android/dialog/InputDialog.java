package org.ituns.android.dialog;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.FragmentActivity;

import java.lang.ref.WeakReference;

public class InputDialog extends DialogCreator {
    private final WeakReference<DialogProxy> mProxy;
    private final WeakReference<FragmentActivity> mActivity;

    public InputDialog(FragmentActivity activity) {
        mProxy = new WeakReference<>(new DialogProxy(this));
        mActivity = new WeakReference<>(activity);
    }

    @Override
    protected android.app.Dialog createDialog(@Nullable Bundle savedInstanceState) {
        Activity activity = mActivity.get();
        if(activity == null) {
            return null;
        }

        InputStyle style = style();
        if(style == null) {
            style = InputStyle.DEFAULT;
        }

        String title = title();
        Button negative = negative();
        Button neutral = neutral();
        Button positive = positive();

        View rootView = LayoutInflater.from(activity).inflate(style.layoutId(), null);
        View htLineView = rootView.findViewById(R.id.line_ht);
        View hbLineView = rootView.findViewById(R.id.line_hb);
        View vlLineView = rootView.findViewById(R.id.line_vl);
        View vrLineView = rootView.findViewById(R.id.line_vr);
        LinearLayout actionLayout = rootView.findViewById(R.id.action);
        AppCompatTextView titleView = rootView.findViewById(R.id.title);
        AppCompatEditText inputView = rootView.findViewById(R.id.input);
        AppCompatTextView negativeBtn = rootView.findViewById(R.id.btn_negative);
        AppCompatTextView neutralBtn = rootView.findViewById(R.id.btn_neutral);
        AppCompatTextView positiveBtn = rootView.findViewById(R.id.btn_positive);

        // setup title view
        int titleVisible = View.GONE;
        if(!TextUtils.isEmpty(title) && titleView != null) {
            titleVisible = View.VISIBLE;
        }
        if(titleView != null) {
            titleView.setText(title);
            titleView.setVisibility(titleVisible);
        }

        // setup horizontal top line
        if(htLineView != null) {
            htLineView.setVisibility(titleVisible);
            htLineView.setBackgroundResource(style.lineDrawable());
        }

        // setup input view

        // calculate action weight
        int weight = 0;
        if(negative != null && negativeBtn != null) {
            weight += 4;
        }
        if(neutral != null && neutralBtn != null) {
            weight += 2;
        }
        if(positive != null && positiveBtn != null) {
            weight += 1;
        }

        // setup action layout
        int actionVisible = weight == 0 ? View.GONE : View.VISIBLE;
        if(actionLayout != null) {
            actionLayout.setVisibility(actionVisible);
        }

        // setup horizontal bottom line
        if(hbLineView != null) {
            hbLineView.setVisibility(actionVisible);
            hbLineView.setBackgroundResource(style.lineDrawable());
        }

        // setup negative button
        int negativeVisible = View.GONE;
        if(weight == 4) {
            negativeVisible = View.VISIBLE;
            negativeBtn.setBackgroundResource(style.wholeBtnDrawable());
        } else if(5 <= weight && weight <= 7) {
            negativeVisible = View.VISIBLE;
            negativeBtn.setBackgroundResource(style.leftBtnDrawable());
        }
        if(negativeBtn != null) {
            negativeBtn.setText(buttonText(negative));
            negativeBtn.setVisibility(negativeVisible);
            negativeBtn.setOnClickListener(v -> {
                dismiss();
                Watcher watcher = buttonWatcher(negative);
                if(watcher != null) {
                    watcher.onText(this, inputText(inputView));
                }
            });
        }

        // setup vertical left line
        int vlLineVisible = View.GONE;
        if(5 <= weight && weight <= 7) {
            vlLineVisible = View.VISIBLE;
        }
        if(vlLineView != null) {
            vlLineView.setVisibility(vlLineVisible);
            vlLineView.setBackgroundResource(style.lineDrawable());
        }

        // setup neutral button
        int neutralVisible = View.GONE;
        if(weight == 2) {
            neutralVisible = View.VISIBLE;
            neutralBtn.setBackgroundResource(style.wholeBtnDrawable());
        } else if(weight == 3) {
            neutralVisible = View.VISIBLE;
            neutralBtn.setBackgroundResource(style.leftBtnDrawable());
        } else if(weight == 6) {
            neutralVisible = View.VISIBLE;
            neutralBtn.setBackgroundResource(style.rightBtnDrawable());
        } else if(weight == 7) {
            neutralVisible = View.VISIBLE;
            neutralBtn.setBackgroundResource(style.centerBtnDrawable());
        }
        if(neutralBtn != null) {
            neutralBtn.setText(buttonText(neutral));
            neutralBtn.setVisibility(neutralVisible);
            neutralBtn.setOnClickListener(v -> {
                dismiss();
                Watcher watcher = buttonWatcher(neutral);
                if(watcher != null) {
                    watcher.onText(this, inputText(inputView));
                }
            });
        }

        // setup vertical right line
        int vrLineVisible = View.GONE;
        if(weight == 3 || weight == 7) {
            vrLineVisible = View.VISIBLE;
        }
        if(vrLineView != null) {
            vrLineView.setVisibility(vrLineVisible);
            vrLineView.setBackgroundResource(style.lineDrawable());
        }

        // setup positive button
        int positiveVisible = View.GONE;
        if(weight == 1) {
            positiveVisible = View.VISIBLE;
            positiveBtn.setBackgroundResource(style.wholeBtnDrawable());
        } else if(weight == 3 || weight == 5 || weight == 7) {
            positiveVisible = View.VISIBLE;
            positiveBtn.setBackgroundResource(style.rightBtnDrawable());
        }
        if(positiveBtn != null) {
            positiveBtn.setText(buttonText(positive));
            positiveBtn.setVisibility(positiveVisible);
            positiveBtn.setOnClickListener(v -> {
                dismiss();
                Watcher watcher = buttonWatcher(positive);
                if(watcher != null) {
                    watcher.onText(this, inputText(inputView));
                }
            });
        }

        // setup dark mode
        rootView.setSelected(darkMode());

        AlertDialog dialog = new AlertDialog.Builder(activity, R.style.iTuns_Dialog).create();
        dialog.setView(rootView);
        return dialog;
    }

    private String inputText(AppCompatEditText inputView) {
        if(inputView != null) {
            return inputView.getText().toString();
        }
        return "";
    }

    private String buttonText(Button button) {
        if(button != null) {
            return button.text();
        }
        return null;
    }

    private Watcher buttonWatcher(Button button) {
        if(button != null) {
            return button.watcher();
        }
        return null;
    }

    protected InputStyle style() {
        return InputStyle.DEFAULT;
    }

    protected String title() {
        return null;
    }

    protected Button negative() {
        return null;
    }

    protected Button neutral() {
        return null;
    }

    protected Button positive() {
        return null;
    }

    protected boolean darkMode() {
        return false;
    }

    protected boolean cancelable() {
        return false;
    }

    @Override
    public void show() {
        FragmentActivity activity = mActivity.get();
        if(activity == null) {
            return;
        }

        DialogProxy proxy = mProxy.get();
        if(proxy == null) {
            return;
        }

        proxy.setCancelable(cancelable());
        proxy.show(activity.getSupportFragmentManager(), "input");
    }

    @Override
    public void dismiss() {
        DialogProxy proxy = mProxy.get();
        if(proxy != null) {
            proxy.dismiss();
        }
    }
}
