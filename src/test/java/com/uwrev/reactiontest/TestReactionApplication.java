package com.uwrev.reactiontest;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Joshua Lauer
 */
public class TestReactionApplication extends ReactionApplication {

  @Override
  public void onCreate() {
    super.onCreate();
  }

  @Override
  protected List<Object> getModules() {
    return Arrays.asList(
        new AndroidModule(this),
        new ReactionModule(),
        new ReactionTestModule()
    );

  }
}
