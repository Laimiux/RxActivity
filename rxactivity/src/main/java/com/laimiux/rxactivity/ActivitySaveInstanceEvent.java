package com.laimiux.rxactivity;

import android.app.Activity;

public class ActivitySaveInstanceEvent extends LifecycleEvent {
  public ActivitySaveInstanceEvent(Activity activity) {
    super(Kind.SAVE_INSTANCE, activity);
  }
}
