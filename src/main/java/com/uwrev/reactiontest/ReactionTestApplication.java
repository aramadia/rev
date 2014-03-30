package com.uwrev.reactiontest;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseFacebookUtils;
import dagger.ObjectGraph;
import com.parse.*;
import com.uwrev.reactiontest.model.RevUser;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

public class ReactionTestApplication extends Application {

  public static final String TAG = "ReactionTestApplication";

  @Inject
  UserManager manager;

  @Override
  public void onCreate() {
    super.onCreate();

    graph = ObjectGraph.create(getModules().toArray());
    inject(this);

    // Register parse subclasses
    ParseObject.registerSubclass(RevUser.class);

    Parse.initialize(this, getString(R.string.parse_application_id),
        getString(R.string.parse_client_key));

    //createParseUser();

    manager.createParseUser();

    // Set your Facebook App Id in strings.xml
    ParseFacebookUtils.initialize(getString(R.string.app_id));
  }

  private ObjectGraph graph;

  protected List<Object> getModules() {
    return Arrays.asList(
        new AndroidModule(this),
        new ReactionTestModule()
    );
  }

  public void inject(Object object) {
    graph.inject(object);
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
