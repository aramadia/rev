package com.uwrev.reactiontest;

import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * Created by Joshua Lauer
 */
@Module(
    addsTo = ReactionModule.class,
    injects = TestReactionApplication.class,
    overrides = true
)
public class ReactionTestModule {

  @Provides
  @Singleton
  UserManager provideUserManager() {
    return new FakeUserManager();
  }

}
