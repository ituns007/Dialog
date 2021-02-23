package org.ituns.android.dialog;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.Nullable;

public abstract class DialogProxy {


    public abstract Dialog createDialog(@Nullable Bundle savedInstanceState);

}
