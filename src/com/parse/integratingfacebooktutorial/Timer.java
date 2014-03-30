package com.parse.integratingfacebooktutorial;

import java.util.List;

/**
 * Created by Joshua Lauer
 */
public interface Timer {

  /**
   * Resets the saved scores and current time
   */
  public void reset();

  /**
   * Starts the timer
   */
  public void startTimer();

  /**
   * Stops the timer and saves the time
   */
  public void stopTimer();

  /**
   * Returns the last time recorded
   */
  public long getLastTimeMs();

  /**
   * @return A list of times
   */
  public List<Long> getTimes();

}
