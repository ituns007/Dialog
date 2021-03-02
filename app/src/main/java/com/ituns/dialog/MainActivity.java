package com.ituns.dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import org.ituns.android.dialog.Action;
import org.ituns.android.dialog.Dialog;
import org.ituns.android.dialog.ProgressStyle;
import org.ituns.android.dialog.ToastStyle;

public class MainActivity extends AppCompatActivity {
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new Handler(getMainLooper());
    }

    public void clickTips(View view) {
        Dialog.toast(this)
                .style(ToastStyle.ERROR)
                .text("Error Message...")
                .darkMode(true)
                .show();
    }

    public void clickLoading(View view) {
        Dialog dialog = Dialog.progress(this)
                .style(ProgressStyle.DEFAULT)
                .text("Please Wait...")
                .darkMode(false)
                .show();
        handler.postDelayed(dialog::dismiss, 5000);
    }

    public void clickConfirm(View view) {
        Dialog dialog = Dialog.confirm(this)
                .title("New Title")
                .content("New Content")
                .negative(Action.with("Negative"))
                .neutral(Action.with("Neutral"))
                .positive(Action.with("Positive"))
                .darkMode(false)
                .cancelable(false)
                .show();
//        handler.postDelayed(dialog::dismiss, 5000);
    }
}