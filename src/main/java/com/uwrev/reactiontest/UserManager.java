package com.uwrev.reactiontest;

import android.util.Log;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.uwrev.reactiontest.model.RevScore;
import com.uwrev.reactiontest.model.RevUser;

import javax.inject.Singleton;

/**
 * Created by aramadia on 2014-03-30.
 */

@Singleton
public class UserManager {

  public static final String TAG = "UserManager";

  RevUser user;

  public RevUser getUser() {
    return user;
  }

  public void createParseUser() {
    // First check if there is a current user
    user = (RevUser) ParseUser.getCurrentUser();
    if (user == null) {

      Log.v(TAG, "Creating new user...");

      attemptSignupUser(0);

    } else {
      Log.v(TAG, "Reusing user " + user.getUsername());
    }
  }

  public void changeName(String newName) {
    user.setVisibleName(newName);
  }

  /**
   * Call this when the user complete a run of scores and we need to submit it to the server.
   * @param score
   */
  public void reportScore(RevScore score) {

    // Assumption, assume the user is logged in
    if (!user.isAuthenticated()) {
      Log.e(TAG, "Error; user isn't authenticated");
    }
    score.setUser(user);

    score.saveInBackground();

  }

  /**
   * Create a parse user
   * @param attempt
   */
  private void attemptSignupUser(final int attempt) {
    if (attempt == 3) {
      Log.e(TAG, "Failed to create a parse user");
      return;
    }

    // Create dummy user
    user = new RevUser();
    user.setPassword("password");

    int numId = (int) (Math.random() * 1000);
    String userId = "Guest" + numId;

    user.setUsername(userId);
    user.setVisibleName(userId);


    user.signUpInBackground(new SignUpCallback() {
      @Override
      public void done(ParseException e) {
        if (e == null) {

          Log.v(TAG, "Successfully created user " + user.getUsername());


        } else {
          Log.e(TAG, "failed to create user ");
          e.printStackTrace();
          attemptSignupUser(attempt + 1);
        }

      }
    });
  }
}
