package com.laimiux.rxactivity;

import android.app.Activity;

public class ActivityStartedEvent extends LifecycleEvent {
  public ActivityStartedEvent(Activity activity) {
    super(Kind.START, activity);
  }
}
