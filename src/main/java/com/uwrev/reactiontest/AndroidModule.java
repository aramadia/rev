package com.uwrev.reactiontest;


import android.content.Context;
import android.location.LocationManager;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

import static android.content.Context.LOCATION_SERVICE;

/**
 * Created by Joshua Lauer
 */

/**
 * A module for Android-specific dependencies which require a {@link Context} or
 * {@link android.app.Application} to create.
 */
@Module(library = true)
public class AndroidModule {
  private final ReactionApplication application;

  public AndroidModule(ReactionApplication application) {
    this.application = application;
  }

  /**
   * Allow the application context to be injected but require that it be annotated with
   * {@link ForApplication @Annotation} to explicitly differentiate it from an activity context.
   */
  @Provides
  @Singleton
  @ForApplication
  Context provideApplicationContext() {
    return application;
  }

  @Provides
  @Singleton
  LocationManager provideLocationManager() {
    return (LocationManager) application.getSystemService(LOCATION_SERVICE);
  }
}
