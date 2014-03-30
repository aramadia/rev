package com.uwrev.reactiontest;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseFacebookUtils;
import dagger.ObjectGraph;
import android.util.Log;
import com.parse.*;
import com.uwrev.reactiontest.model.RevUser;

import java.util.Arrays;
import java.util.List;

public class ReactionTestApplication extends Application {

	public static final String TAG = "ReactionTestApplication";

	@Override
	public void onCreate() {
		super.onCreate();

    // Register parse subclasses
    ParseObject.registerSubclass(RevUser.class);

		Parse.initialize(this, getString(R.string.parse_application_id),
				getString(R.string.parse_client_key));

//    createParseUser();

    //testParse();

		// Set your Facebook App Id in strings.xml
		ParseFacebookUtils.initialize(getString(R.string.app_id));

    graph = ObjectGraph.create(getModules().toArray());
  }

  private ObjectGraph graph;

  protected List<Object> getModules() {
    return Arrays.asList(
        new AndroidModule(this),
        new TimerModule()
    );
  }

  public void inject(Object object) {
    graph.inject(object);
  }

  private void createParseUser() {
    // First check if there is a current user
    RevUser user = (RevUser)ParseUser.getCurrentUser();
    if (user == null) {

      Log.v(TAG, "Creating new user...");

      for (int i = 0 ; i < 3; i++) {
        // Create dummy user
        user = new RevUser();
        user.setPassword("password");

        int numId = (int) (Math.random() * 1000);
        String userId = "Guest" + numId;

        user.setUsername(userId);
        user.setVisiblerName(userId);

        try {
          user.signUp();
          Log.v(TAG, "Successfully created user " + user.getUsername());
          break;
        } catch (ParseException e) {
          e.printStackTrace();
        }

      }

    } else {
      Log.v(TAG, "Reusing user " + user.getUsername());
    }

  }


  private void testParse() {
    ParseObject gameScore = new ParseObject("GameScore");
    gameScore.put("score", 1337);
    gameScore.put("playerName", "Sean Plott");
    gameScore.put("cheatMode", false);
    gameScore.put("facebookId", "awefawef");
    gameScore.saveInBackground();
  }

}
