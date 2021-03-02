package com.ituns.dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import org.ituns.android.dialog.Button;
import org.ituns.android.dialog.Dialog;
import org.ituns.android.dialog.ProgressStyle;
import org.ituns.android.dialog.ToastStyle;
import org.ituns.android.dialog.Watcher;

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

    public void clickProgress(View view) {
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
                .negative(Button.with("Negative"))
                .neutral(Button.with("Neutral"))
                .positive(Button.with("Positive"))
                .darkMode(false)
                .cancelable(false)
                .show();
//        handler.postDelayed(dialog::dismiss, 5000);
    }

    public void clickInput(View view) {
        Dialog dialog = Dialog.input(this)
                .title("Input Title")
                .negative(Button.with("Negative", (dialog1, text) -> Log.e("wangxiulong", "Text:" + text)))
                .darkMode(true)
                .show();
    }
}