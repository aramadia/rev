package com.uwrev.reactiontest.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.Date;

@ParseClassName("RevScore")
public class RevScore extends ParseObject {

  private static final String SCORE = "score";
  private static final String USER_ID = "userId";
  private static final String DATE = "date";
  private static final String GAME_MODE = "gameMode";


  public int getScore() {
    return getInt(SCORE);
  }
  public void setScore(int value) {
    put(SCORE, value);
  }

  public String getUserId() { return getString(USER_ID); }
  public void setUserId(String value) { put(USER_ID, value); }

  public Date getDate() { return getDate(DATE); }
  public void setDate(Date value) { put(DATE, value); }

  public String getGameMode() { return getString(GAME_MODE); }
  public void setGameMode(String value) { put(GAME_MODE, value); }
}
