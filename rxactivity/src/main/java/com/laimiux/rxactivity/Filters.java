package com.laimiux.rxactivity;

import android.app.Activity;

import rx.functions.Func1;

class Filters {

  public static Func1<LifecycleEvent, Boolean> onlyActivity(final Activity activity) {
    return new Func1<LifecycleEvent, Boolean>() {
      @Override public Boolean call(LifecycleEvent lifecycleEvent) {
        return lifecycleEvent.activity() == activity;
      }
    };
  }

  public static <T extends Activity> Func1<LifecycleEvent, Boolean> onlyActivity(final Class<T> clazz) {
    return new Func1<LifecycleEvent, Boolean>() {
      @Override public Boolean call(LifecycleEvent lifecycleEvent) {
        return lifecycleEvent.activity().getClass().equals(clazz);
      }
    };
  }

  public static Func1<LifecycleEvent, Boolean> activityEvent(
      final Activity activity, final LifecycleEvent.Kind kind) {

    return new Func1<LifecycleEvent, Boolean>() {
      @Override public Boolean call(LifecycleEvent lifecycleEvent) {
        return lifecycleEvent.activity() == activity && lifecycleEvent.kind() == kind;
      }
    };
  }

  public static <T extends Activity> Func1<LifecycleEvent, Boolean> activityEvent(
      final Class<T> clazz, final LifecycleEvent.Kind kind) {

    return new Func1<LifecycleEvent, Boolean>() {
      @Override public Boolean call(LifecycleEvent lifecycleEvent) {
        return lifecycleEvent.activity().getClass().equals(clazz) && lifecycleEvent.kind() == kind;
      }
    };
  }

}
