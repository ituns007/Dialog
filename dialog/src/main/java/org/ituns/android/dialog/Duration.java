package org.ituns.android.dialog;

public enum  Duration {
    SHORT(2.5f), LONG(3.5f);

    float duration;

    Duration(float duration) {
        this.duration = duration;
    }

    float duration() {
        return duration;
    }
}
