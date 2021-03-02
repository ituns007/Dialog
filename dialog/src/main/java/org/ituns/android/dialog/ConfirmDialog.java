package org.ituns.android.dialog;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.FragmentActivity;

import java.lang.ref.WeakReference;

public abstract class ConfirmDialog extends DialogCreator {
    private final WeakReference<DialogProxy> mProxy;
    private final WeakReference<FragmentActivity> mActivity;

    public ConfirmDialog(FragmentActivity activity) {
        mProxy = new WeakReference<>(new DialogProxy(this));
        mActivity = new WeakReference<>(activity);
    }

    @Override
    protected android.app.Dialog createDialog(@Nullable Bundle savedInstanceState) {
        Activity activity = mActivity.get();
        if(activity == null) {
            return null;
        }

        ConfirmStyle style = style();
        if(style == null) {
            style = ConfirmStyle.DEFAULT;
        }

        String title = title();
        String content = content();
        Action negative = negative();
        Action neutral = neutral();
        Action positive = positive();

        View rootView = LayoutInflater.from(activity).inflate(style.layoutId(), null);
        View htLineView = rootView.findViewById(R.id.line_ht);
        View hbLineView = rootView.findViewById(R.id.line_hb);
        View vlLineView = rootView.findViewById(R.id.line_vl);
        View vrLineView = rootView.findViewById(R.id.line_vr);
        LinearLayout actionLayout = rootView.findViewById(R.id.action);
        AppCompatTextView titleView = rootView.findViewById(R.id.title);
        AppCompatTextView contentView = rootView.findViewById(R.id.content);
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

        // setup content view
        if(contentView != null) {
            contentView.setText(content);
        }

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
            negativeBtn.setText(actionText(negative));
            negativeBtn.setVisibility(negativeVisible);
            negativeBtn.setOnClickListener(v -> {
                dismiss();
                Callback callback = actionCallback(negative);
                if(callback != null) {
                    callback.onClick(this);
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
            neutralBtn.setText(actionText(neutral));
            neutralBtn.setVisibility(neutralVisible);
            neutralBtn.setOnClickListener(v -> {
                dismiss();
                Callback callback = actionCallback(neutral);
                if(callback != null) {
                    callback.onClick(this);
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
            positiveBtn.setText(actionText(positive));
            positiveBtn.setVisibility(positiveVisible);
            positiveBtn.setOnClickListener(v -> {
                dismiss();
                Callback callback = actionCallback(positive);
                if(callback != null) {
                    callback.onClick(this);
                }
            });
        }

        // setup dark mode
        rootView.setSelected(darkMode());

        AlertDialog dialog = new AlertDialog.Builder(activity, R.style.iTuns_Dialog).create();
        dialog.setView(rootView);
        return dialog;
    }

    private String actionText(Action action) {
        if(action != null) {
            return action.text();
        }
        return null;
    }

    private Callback actionCallback(Action action) {
        if(action != null) {
            return action.callback();
        }
        return null;
    }

    protected ConfirmStyle style() {
        return ConfirmStyle.DEFAULT;
    }

    protected String title() {
        return null;
    }

    protected String content() {
        return null;
    }

    protected Action negative() {
        return null;
    }

    protected Action neutral() {
        return null;
    }

    protected Action positive() {
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
        proxy.show(activity.getSupportFragmentManager(), "confirm");
    }

    @Override
    public void dismiss() {
        DialogProxy proxy = mProxy.get();
        if(proxy != null) {
            proxy.dismiss();
        }
    }
}