package com.uwrev.reactiontest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by Joshua Lauer
 */
@RunWith(RobolectricTestRunner.class)
public class ReactionTimeApplicationTest {

  @Test
  public void shouldPassWithHappySmiles() throws Exception {
      assertEquals("Reaction Time", Robolectric.application.getString(R.string.app_name));
  }

}
