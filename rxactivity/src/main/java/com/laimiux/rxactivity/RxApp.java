package com.laimiux.rxactivity;

import android.app.Application;
import android.content.Context;
import android.support.annotation.CheckResult;

import rx.Observable;

public class RxApp {

  @CheckResult
  static Observable<LifecycleEvent> lifecycleInternal(final Application app) {
    return Observable.create(new ActivityLifecycleOnSubscribe(app));
  }

  @CheckResult
  public static Observable<LifecycleEvent> lifecycle(Context context) {
    return lifecycleInternal((Application) context.getApplicationContext());
  }
}
