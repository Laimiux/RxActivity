package com.laimiux.rxactivity;

import android.app.Application;

import rx.Observable;
import rx.functions.Func1;

public class LifecycleObservable extends Observable<LifecycleEvent> {
  /**
   * Creates an Observable with a Function to execute when it is subscribed to.
   * <p/>
   * <em>Note:</em> Use {@link #create(OnSubscribe)} to create an Observable, instead of this constructor,
   * unless you specifically have a need for inheritance.
   */
  protected LifecycleObservable(Application app) {
    super(new ActivityLifecycleOnSubscribe(app));
  }

  public Observable<ActivityDestroyEvent> destroyEvents() {
    return filter(new Func1<LifecycleEvent, Boolean>() {
      @Override public Boolean call(LifecycleEvent lifecycleEvent) {
        return lifecycleEvent instanceof ActivityDestroyEvent;
      }
    }).cast(ActivityDestroyEvent.class);
  }
}
