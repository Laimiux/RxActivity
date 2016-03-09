package com.laimiux.rxactivity.internal;

public class Preconditions {

  private Preconditions() {
    throw new AssertionError("No instances.");
  }

  public static void checkArgument(boolean assertion, String message) {
    if (!assertion) {
      throw new IllegalArgumentException(message);
    }
  }

  public static <T> T checkNotNull(T value, String message) {
    if (value == null) {
      throw new NullPointerException(message);
    }
    return value;
  }


}
