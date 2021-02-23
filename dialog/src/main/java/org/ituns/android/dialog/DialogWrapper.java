package org.ituns.android.dialog;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DialogWrapper extends DialogFragment {
    private DialogProxy mProxy;

    public DialogWrapper(DialogProxy proxy) {
        mProxy = proxy;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        DialogProxy proxy = mProxy;
        if(proxy != null) {
            return proxy.createDialog(savedInstanceState);
        }
        return super.onCreateDialog(savedInstanceState);
    }
}
