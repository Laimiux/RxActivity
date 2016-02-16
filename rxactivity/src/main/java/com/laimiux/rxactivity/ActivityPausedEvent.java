package com.laimiux.rxactivity;

import android.app.Activity;

public class ActivityPausedEvent extends LifecycleEvent {
  public ActivityPausedEvent(Activity activity) {
    super(Kind.PAUSE, activity);
  }
}
