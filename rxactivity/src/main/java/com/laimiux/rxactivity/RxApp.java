package com.laimiux.rxactivity;

import android.app.Application;
import android.support.annotation.CheckResult;

import rx.Observable;

public class RxApp {
  @CheckResult
  public static Observable<LifecycleEvent> lifecycle(final Application app) {
    return Observable.create(new ActivityLifecycleOnSubscribe(app));
  }
}
