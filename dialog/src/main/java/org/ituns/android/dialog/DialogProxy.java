package org.ituns.android.dialog;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DialogProxy extends DialogFragment {
    private DialogCreator mCreator;

    public DialogProxy(DialogCreator creator) {
        mCreator = creator;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        DialogCreator creator = mCreator;
        if(creator == null) {
            return super.onCreateDialog(savedInstanceState);
        }

        Dialog dialog = creator.createDialog(savedInstanceState);
        if(dialog == null) {
            dialog = super.onCreateDialog(savedInstanceState);
        }

        return dialog;
    }
}
