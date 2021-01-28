package org.ituns.android.dialog;

public class Duration {
    public static final Duration SHORT = new Duration(2.5f);
    public static final Duration LONG = new Duration(3.5f);

    private final float duration;

    Duration(float duration) {
        this.duration = duration;
    }

    float duration() {
        return duration;
    }

    public static Duration create(float duration) {
        return new Duration(duration);
    }
}
