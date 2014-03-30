package com.uwrev.reactiontest;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joshua Lauer
 */
public class ReactionTimer implements Timer {

  private static final String TAG = ReactionTimer.class.getSimpleName();

  private List<Long> times = new ArrayList<Long>();
  private long startTime = 0;

  @Override
  public void startTimer() {
    startTime = System.nanoTime();
  }

  @Override
  public void stopTimer() {
    if (startTime == 0) {
      // timer was never started, do not record time
      Log.e(TAG, "Attempting to stop timer before starting it");
    }

    times.add((System.nanoTime() - startTime) / 1000000L);
    // reset the time
    startTime = 0;
  }

  @Override
  public long getLastTimeMs() {
    if (times.isEmpty()) {
      return 0;
    }

    return times.get(times.size() - 1);
  }

  @Override
  public void reset() {
    times.clear();
  }

  @Override
  public List<Long> getTimes() {
    return times;
  }

}
