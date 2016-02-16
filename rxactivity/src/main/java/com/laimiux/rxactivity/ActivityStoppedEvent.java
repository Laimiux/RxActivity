package com.laimiux.rxactivity;

import android.app.Activity;

public class ActivityStoppedEvent extends LifecycleEvent {
  public ActivityStoppedEvent(Activity activity) {
    super(Kind.STOP, activity);
  }
}
