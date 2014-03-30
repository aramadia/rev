package com.uwrev.reactiontest;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseFacebookUtils;
import com.parse.ParseObject;
import com.uwrev.reactiontest.model.RevUser;

public class ReactionTestApplication extends Application {

	public static final String TAG = "ReactionTestApplication";

	@Override
	public void onCreate() {
		super.onCreate();

    // Register parse subclasses
    ParseObject.registerSubclass(RevUser.class);

		Parse.initialize(this, getString(R.string.parse_application_id),
				getString(R.string.parse_client_key));

		// Set your Facebook App Id in strings.xml
		ParseFacebookUtils.initialize(getString(R.string.app_id));



    testParse();
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
