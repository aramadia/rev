package com.uwrev.reactiontest;

import com.uwrev.reactiontest.activity.GameActivity;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * Created by Joshua Lauer
 */
@Module(
    injects = {GameActivity.class, ReactionTestApplication.class},
    includes = {AndroidModule.class}
)
public class ReactionTestModule {

  @Provides
  Timer provideReactionTimer() {
    return new ReactionTimer();
  }

  @Provides
  @Singleton
  UserManager provideUserManager() {
    return new UserManager();
  }

}
