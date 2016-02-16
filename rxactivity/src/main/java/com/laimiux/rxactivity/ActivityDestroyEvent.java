package com.laimiux.rxactivity;

import android.app.Activity;

public class ActivityDestroyEvent extends LifecycleEvent {
  public ActivityDestroyEvent(Activity activity) {
    super(Kind.DESTROY, activity);
  }
}
