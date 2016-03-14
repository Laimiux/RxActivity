package com.laimiux.rxactivity;


import android.app.Activity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Matchers;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.util.ActivityController;

import rx.Observer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(RobolectricGradleTestRunner.class)
public class RxActivityTest {


  @Test
  public void trackActivity_notifiesOfAllEvents() {
    ActivityController<Activity> controller = Robolectric.buildActivity(Activity.class);

    final Activity activity = controller.get();

    final Observer<LifecycleEvent> observer = mock(Observer.class);
    RxActivity.trackActivity(activity).subscribe(observer);



    final SingleActivityVerifier verifier = new SingleActivityVerifier(activity, observer);

    controller.create();
    controller.start();
    controller.resume();
    controller.pause();
    controller.stop();
    controller.destroy();

    verifier.nextWas(LifecycleEvent.Kind.CREATE);
    verifier.nextWas(LifecycleEvent.Kind.START);
    verifier.nextWas(LifecycleEvent.Kind.RESUME);
    verifier.nextWas(LifecycleEvent.Kind.PAUSE);
    verifier.nextWas(LifecycleEvent.Kind.STOP);
    verifier.nextWas(LifecycleEvent.Kind.DESTROY);
  }

  @Test
  public void trackActivity_ignoresOtherActivities() {
    final Activity activity = Robolectric.buildActivity(Activity.class).get();

    final Observer<LifecycleEvent> observer = mock(Observer.class);
    RxActivity.trackActivity(activity).subscribe(observer);

    ActivityController<Activity> current = Robolectric.buildActivity(Activity.class);
    current.create();
    current.start();
    current.resume();
    current.pause();
    current.stop();
    current.destroy();

    verifyZeroInteractions(observer);
  }

  @Test
  public void trackActivityEvent_ignoresOtherEvents() {
    ActivityController<Activity> controller = Robolectric.buildActivity(Activity.class);

    final Activity activity = controller.get();

    final Observer<LifecycleEvent> observer = mock(Observer.class);
    RxActivity.trackActivityEvent(activity, LifecycleEvent.Kind.CREATE).subscribe(observer);



    final SingleActivityVerifier verifier = new SingleActivityVerifier(activity, observer);

    controller.create();
    controller.start();
    controller.resume();
    controller.pause();
    controller.stop();
    controller.destroy();

    verifier.nextWas(LifecycleEvent.Kind.CREATE);
  }

  static class SingleActivityVerifier {
    private final Activity activity;
    final Observer<LifecycleEvent> observer;

    public SingleActivityVerifier(Activity activity, Observer<LifecycleEvent> observer) {
      this.activity = activity;
      this.observer = observer;
    }

    public void nextWas(final LifecycleEvent.Kind event) {

      verify(observer).onNext(Matchers.argThat(new Matcher<LifecycleEvent>() {
        @Override public boolean matches(Object item) {
          final LifecycleEvent lifecycleEvent = (LifecycleEvent) item;
          return Filters.activityEvent(activity, event).call(lifecycleEvent);
        }

        @Override public void describeMismatch(Object item, Description mismatchDescription) {

        }

        @Override public void _dont_implement_Matcher___instead_extend_BaseMatcher_() {

        }

        @Override public void describeTo(Description description) {

        }
      }));
    }
  }
}