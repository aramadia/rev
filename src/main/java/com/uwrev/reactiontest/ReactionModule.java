package com.uwrev.reactiontest;

import com.uwrev.reactiontest.activity.GameActivity;
import com.uwrev.reactiontest.activity.MainActivity;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * Created by Joshua Lauer
 */
@Module(
    injects = {GameActivity.class, MainActivity.class, ReactionApplication.class},
    includes = {AndroidModule.class}
)
public class ReactionModule {

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
