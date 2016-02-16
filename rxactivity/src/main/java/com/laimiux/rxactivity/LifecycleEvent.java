package com.laimiux.rxactivity;

import android.app.Activity;

public abstract class LifecycleEvent {
  private final Kind kind;
  private final Activity activity;

  public LifecycleEvent(Kind kind, Activity activity) {
    this.kind = kind;
    this.activity = activity;
  }

  public Activity activity() {
    return activity;
  }

  public Kind kind() {
    return kind;
  }

  public static enum Kind {
    CREATE,
    START,
    RESUME,
    PAUSE,
    SAVE_INSTANCE,
    STOP,
    DESTROY;
  }

}
