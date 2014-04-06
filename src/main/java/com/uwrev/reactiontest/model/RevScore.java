package com.uwrev.reactiontest.model;

import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;

import java.util.Date;

@ParseClassName("RevScore")
public class RevScore extends ParseObject {

  public static final String SCORE = "score";
  public static final String USER = "user";
  public static final String DATE = "date";
  public static final String GAME_MODE = "gameMode";


  public int getScore() {
    return getInt(SCORE);
  }
  public void setScore(int value) {
    put(SCORE, value);
  }

  public RevUser getUser() { return (RevUser)get(USER); }
  public void setUser(RevUser value) { put(USER, value); }

  public Date getDate() { return getDate(DATE); }
  public void setDate(Date value) { put(DATE, value); }

  public String getGameMode() { return getString(GAME_MODE); }
  public void setGameMode(String value) { put(GAME_MODE, value); }

  // todo fix this: fetch in toString, wtfx
  @Override
  public String toString() {
    try {
      RevUser user = getUser();
      user.fetchIfNeeded();

      return "Score: " + getScore() + " by " + user.getVisibleName();
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return "Failed to network";
  }

}
