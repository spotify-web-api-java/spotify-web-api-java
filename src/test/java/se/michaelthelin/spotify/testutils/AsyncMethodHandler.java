package se.michaelthelin.spotify.testutils;

import se.michaelthelin.spotify.exceptions.BadFieldException;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class AsyncMethodHandler {

  private CountDownLatch signal;
  private Throwable assertionError;

  public AsyncMethodHandler() {
    signal = new CountDownLatch(1);
  }

  public void wait(long timeUnits, TimeUnit unit) throws InterruptedException {
    signal.await(timeUnits, unit);
  }

  public void done() {
    signal.countDown();
  }

  public void done(Throwable throwable) {
    assertionError = throwable;
    signal.countDown();
  }

  public void assertNoErrors() {
    if (assertionError != null) {
      throw new AssertionError(assertionError);
    }
  }

  public void assertErrors() {
    if (assertionError == null) {
      throw new AssertionError("No assertion errors occurred");
    }
  }

  public Throwable getThrowables() {
    return assertionError;
  }

  public boolean assertErrorType(Class<? extends Exception> exceptionClass) {
    return assertionError.getClass().equals(exceptionClass);
  }
}