package org.ituns.android.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.FragmentActivity;

import java.lang.ref.WeakReference;

public abstract class ToastDialog extends DialogCreator {
    private final WeakReference<FragmentActivity> mActivity;

    public ToastDialog(FragmentActivity activity) {
        mActivity = new WeakReference<>(activity);
    }

    @Override
    protected Dialog createDialog(@Nullable Bundle savedInstanceState) {
        FragmentActivity activity = mActivity.get();
        if(activity == null) {
            return null;
        }

        ToastStyle style = style();
        if(style == null) {
            style = ToastStyle.ERROR;
        }

        String text = text();

        View rootView = LayoutInflater.from(activity).inflate(style.layoutId(), null);
        AppCompatImageView iconView = rootView.findViewById(R.id.icon);
        iconView.setImageResource(style.iconDrawable());
        AppCompatTextView textView = rootView.findViewById(R.id.text);
        if(TextUtils.isEmpty(text)) {
            textView.setVisibility(View.GONE);
        } else {
            textView.setText(text);
            textView.setVisibility(View.VISIBLE);
        }
        rootView.setSelected(darkMode());

        AlertDialog dialog = new AlertDialog.Builder(activity, R.style.iTuns_Dialog).create();
        dialog.setView(rootView);
        return dialog;
    }

    protected ToastStyle style() {
        return ToastStyle.ERROR;
    }

    protected String text() {
        return "";
    }

    protected long duration() {
        return 2500;
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

        DialogProxy proxy = new DialogProxy(this);
        proxy.setCancelable(cancelable());
        proxy.show(activity.getSupportFragmentManager(), "toast");

        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(proxy::dismiss, duration());
    }
}