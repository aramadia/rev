package com.uwrev.reactiontest.fragment;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static junit.framework.Assert.assertNotNull;
import static org.robolectric.util.FragmentTestUtil.startFragment;

/**
 * Created by Joshua Lauer
 */
@RunWith(RobolectricTestRunner.class)
public class GameFragmentTest {

  @Test
  public void shouldNotBeNull() throws Exception {
    GameFragment myFragment = new GameFragment();
    startFragment(myFragment);

    assertNotNull(myFragment);
  }

}
