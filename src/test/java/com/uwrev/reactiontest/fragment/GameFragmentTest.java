package com.uwrev.reactiontest.fragment;

import android.view.View;
import com.uwrev.reactiontest.R;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.robolectric.util.FragmentTestUtil.startFragment;

/**
 * Created by Joshua Lauer
 */
@RunWith(RobolectricTestRunner.class)
public class GameFragmentTest {

  @Test
  public void shouldNotBeNull() throws Exception {
    GameFragment gameFragment = new GameFragment();
    startFragment(gameFragment);

    assertNotNull(gameFragment);
  }

  @Test
  public void shouldGoToErrorStateIfPressedTooEarly() throws Exception {
    GameFragment gameFragment = new GameFragment();
    startFragment(gameFragment);

    assertThat(gameFragment.getGameState(), is(GameFragment.GameState.START_STATE));

    View gameBackground = gameFragment.getView().findViewById(R.id.game_background);
    Assert.assertNotNull(gameBackground);

    // starts the game
    gameBackground.performClick();

    assertThat(gameFragment.getGameState(), is(GameFragment.GameState.WAIT_STATE));

    // pressed before the color changed
    gameBackground.performClick();

    assertThat(gameFragment.getGameState(), is(GameFragment.GameState.TOO_SOON_STATE));

    // clicking again restarts this round
    gameBackground.performClick();

    assertThat(gameFragment.getGameState(), is(GameFragment.GameState.WAIT_STATE));
  }

  @Test
  public void shouldShowResultStateIfPressedAfterChange() throws Exception {
    GameFragment gameFragment = new GameFragment();
    startFragment(gameFragment);

    assertThat(gameFragment.getGameState(), is(GameFragment.GameState.START_STATE));

    View gameBackground = gameFragment.getView().findViewById(R.id.game_background);
    Assert.assertNotNull(gameBackground);

    // starts the game
    gameBackground.performClick();

    assertThat(gameFragment.getGameState(), is(GameFragment.GameState.WAIT_STATE));

    // trigger the wait
    Robolectric.runUiThreadTasksIncludingDelayedTasks();

    // todo more test with real timings, can use shadowlooper
//    ShadowLooper.idleMainLooper(100);
//    assertThat(gameFragment.getGameState(), is(GameFragment.GameState.WAIT_STATE));
//    ShadowLooper.idleMainLooper(1000);

    assertThat(gameFragment.getGameState(), is(GameFragment.GameState.CLICK_STATE));

    // clicked in the click state
    gameBackground.performClick();

    assertThat(gameFragment.getGameState(), is(GameFragment.GameState.RESULT_STATE));

    // clicking again restarts this round
    gameBackground.performClick();

    assertThat(gameFragment.getGameState(), is(GameFragment.GameState.WAIT_STATE));
  }

}
