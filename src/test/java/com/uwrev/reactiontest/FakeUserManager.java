package com.uwrev.reactiontest;

import com.uwrev.reactiontest.model.RevScore;
import com.uwrev.reactiontest.model.RevUser;

/**
 * Created by Joshua Lauer
 */
public class FakeUserManager extends UserManager {

  @Override
  public RevUser getUser() {
    return null;
  }

  @Override
  public void createParseUser() {
  }

  @Override
  public void changeName(String newName) {
  }

  @Override
  public void reportScore(RevScore score) {
  }
}
