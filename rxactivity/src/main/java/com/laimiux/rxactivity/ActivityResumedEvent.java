package com.laimiux.rxactivity;

import android.app.Activity;

public class ActivityResumedEvent extends LifecycleEvent {
  public ActivityResumedEvent(Activity activity) {
    super(Kind.RESUME, activity);
  }
}
