package org.ituns.android.dialog;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.FragmentActivity;

import java.lang.ref.WeakReference;

public abstract class ProgressDialog extends DialogCreator {
    private final WeakReference<DialogProxy> mProxy;
    private final WeakReference<FragmentActivity> mActivity;

    public ProgressDialog(FragmentActivity activity) {
        mProxy = new WeakReference<>(new DialogProxy(this));
        mActivity = new WeakReference<>(activity);
    }

    @Override
    protected android.app.Dialog createDialog(@Nullable Bundle savedInstanceState) {
        FragmentActivity activity = mActivity.get();
        if(activity == null) {
            return null;
        }

        ProgressStyle style = style();
        if(style == null) {
            style = ProgressStyle.DEFAULT;
        }

        String text = text();
        View rootView = LayoutInflater.from(activity).inflate(style.layoutId(), null);
        ProgressBar progressView = rootView.findViewById(R.id.progress);
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

    protected ProgressStyle style() {
        return ProgressStyle.DEFAULT;
    }

    protected String text() {
        return "";
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
        proxy.show(activity.getSupportFragmentManager(), "progress");
    }

    @Override
    public void dismiss() {
        DialogProxy proxy = mProxy.get();
        if(proxy != null) {
            proxy.dismiss();
        }
    }
}
