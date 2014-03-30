package com.uwrev.reactiontest;

import com.uwrev.reactiontest.activity.GameActivity;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Joshua Lauer
 */
@Module(
    injects = GameActivity.class,
    includes = AndroidModule.class
)
public class TimerModule {

  @Provides
  Timer provideApplicationContext() {
    return new ReactionTimer();
  }

}
