package com.uwrev.reactiontest.model;

// RevUser.java
import com.parse.ParseObject;
import com.parse.ParseClassName;
import com.parse.ParseUser;

/**
 * Repreesents a user playing a game
 */
@ParseClassName("_User")
public class RevUser extends ParseUser {

  private static final String VISIBLE_NAME = "visibleName";
  private static final String AGE = "age";
  private static final String COUNTRY = "country";
  private static final String HIGH_SCORE = "highScore";


  /**
   * see docs/project-rev
   * username (publically visible)
   Profile picture
   FB_ID should be included
   Age
   City/Location
   Country
   Highest score object
   Friends Graph to support function (get all user objects who are friends)

   */



  public String getVisibleName() {
    return getString(VISIBLE_NAME);
  }
  public void setVisibleName(String value) {
    put(VISIBLE_NAME, value);
  }
  public int getAge() {
    return getInt(AGE);
  }
  public void setAge(int value) {
    put(AGE, value);
  }
  public String getCountry() {
    return getString(COUNTRY);
  }
  public void setCountry(String value) {
    put(COUNTRY, value);
  }
  public Object getHighScore() {
    return get(HIGH_SCORE);
  }
  public void setHighScore(Object value) {
    put(HIGH_SCORE, value);
  }






}