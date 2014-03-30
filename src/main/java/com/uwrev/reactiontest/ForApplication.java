package com.uwrev.reactiontest;

import javax.inject.Qualifier;
import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Joshua Lauer
 */
@Qualifier
@Retention(RUNTIME)
public @interface ForApplication {
}
