package com.uwrev.reactiontest.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
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
    startMyFragment(myFragment);

    assertNotNull(myFragment);
  }

  public void startMyFragment(Fragment fragment) {
    FragmentActivity activity = Robolectric.buildActivity(FragmentActivity.class)
        .create()
        .start()
        .resume()
        .get();

    FragmentManager fragmentManager = activity.getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    fragmentTransaction.add(fragment, null);
    fragmentTransaction.commit();
  }

}
