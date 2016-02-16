package com.laimiux.rxactivity;

import android.app.Activity;

public class ActivityCreatedEvent extends LifecycleEvent {
  public ActivityCreatedEvent(Activity activity) {
    super(Kind.CREATE, activity);
  }
}
