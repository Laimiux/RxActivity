package com.laimiux.rxactivity;

import android.app.Activity;

import rx.Observable;
import rx.functions.Func1;

public class RxActivity {


  public static Observable<LifecycleEvent> trackActivity(Activity activity) {
    return RxApp.lifecycle(activity).filter(Filters.onlyActivity(activity));
  }

  public static Observable<LifecycleEvent> trackActivityEvent(
      Activity activity, LifecycleEvent.Kind kind) {

    return RxApp.lifecycle(activity).filter(Filters.activityEvent(activity, kind));
  }


  public static Observable<ActivityDestroyEvent> onDestroy(Activity activity) {
    return trackActivityEvent(activity, LifecycleEvent.Kind.DESTROY)
        .cast(ActivityDestroyEvent.class);
  }

  public static Observable<ActivityDestroyEvent> finish(final Activity activity) {
    return onDestroy(activity).filter(new Func1<ActivityDestroyEvent, Boolean>() {
      @Override public Boolean call(ActivityDestroyEvent event) {
        return event.activity().isFinishing();
      }
    });
  }
}
