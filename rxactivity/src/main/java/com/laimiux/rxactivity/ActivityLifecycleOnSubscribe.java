package com.laimiux.rxactivity;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;


import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

import static rx.android.MainThreadSubscription.verifyMainThread;

class ActivityLifecycleOnSubscribe implements Observable.OnSubscribe<LifecycleEvent> {
  private final Application app;

  public ActivityLifecycleOnSubscribe(Application app) {
    this.app = app;
  }

  @Override public void call(final Subscriber<? super LifecycleEvent> subscriber) {
    verifyMainThread();

    final Application.ActivityLifecycleCallbacks callbacks = new Application.ActivityLifecycleCallbacks() {
      @Override public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        if(!subscriber.isUnsubscribed()) {
          subscriber.onNext(new ActivityCreatedEvent(activity));
        }
      }

      @Override public void onActivityStarted(Activity activity) {
        if(!subscriber.isUnsubscribed()) {
          subscriber.onNext(new ActivityStartedEvent(activity));
        }
      }

      @Override public void onActivityResumed(Activity activity) {
        if(!subscriber.isUnsubscribed()) {
          subscriber.onNext(new ActivityResumedEvent(activity));
        }
      }

      @Override public void onActivityPaused(Activity activity) {
        if(!subscriber.isUnsubscribed()) {
          subscriber.onNext(new ActivityPausedEvent(activity));
        }
      }

      @Override public void onActivityStopped(Activity activity) {
        if(!subscriber.isUnsubscribed()) {
          subscriber.onNext(new ActivityStoppedEvent(activity));
        }
      }

      @Override public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        if(!subscriber.isUnsubscribed()) {
          subscriber.onNext(new ActivitySaveInstanceEvent(activity));
        }
      }

      @Override public void onActivityDestroyed(Activity activity) {
        if(!subscriber.isUnsubscribed()) {
          subscriber.onNext(new ActivityDestroyEvent(activity));
        }
      }
    };

    app.registerActivityLifecycleCallbacks(callbacks);

    subscriber.add(new MainThreadSubscription() {
      @Override protected void onUnsubscribe() {
        app.unregisterActivityLifecycleCallbacks(callbacks);
      }
    });
  }
}
