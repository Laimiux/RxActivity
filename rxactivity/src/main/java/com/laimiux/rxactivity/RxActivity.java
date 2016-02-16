package com.laimiux.rxactivity;

import android.app.Activity;

import rx.Observable;
import rx.functions.Func1;

public class RxActivity {

  public static Observable<ActivityDestroyEvent> onDestroy(Activity activity) {
    return RxApp.lifecycle(activity.getApplication()).filter(new Func1<LifecycleEvent, Boolean>() {
      @Override public Boolean call(LifecycleEvent lifecycleEvent) {
        return lifecycleEvent.kind() == LifecycleEvent.Kind.DESTROY;
      }
    }).cast(ActivityDestroyEvent.class);
  }

  public static Observable<ActivityDestroyEvent> finish(final Activity activity) {
    return onDestroy(activity).filter(new Func1<ActivityDestroyEvent, Boolean>() {
      @Override public Boolean call(ActivityDestroyEvent event) {
        return event.activity().isFinishing();
      }
    });
  }
}
