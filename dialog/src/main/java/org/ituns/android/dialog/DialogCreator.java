package org.ituns.android.dialog;

import android.os.Bundle;

import androidx.annotation.Nullable;

public abstract class DialogCreator extends Dialog {

    protected abstract android.app.Dialog createDialog(@Nullable Bundle savedInstanceState);

    @Override
    public void show() {}

    @Override
    public void dismiss() {}
}
