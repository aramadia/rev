package com.uwrev.reactiontest.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.uwrev.reactiontest.R;
import com.uwrev.reactiontest.ReactionApplication;
import com.uwrev.reactiontest.fragment.GameFragment;
import com.uwrev.reactiontest.fragment.ScoreFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joshua Lauer
 */
public class MainActivity extends ReactionBaseActivity implements ActionBar.TabListener {

  @InjectView(R.id.pager) ViewPager pager;
  private ActionBar actionBar;
  private ReactionPagerAdapter pagerAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.main_layout);

    ButterKnife.inject(this);

    ArrayList<Fragment> fragments = new ArrayList<Fragment>();
    fragments.add(new GameFragment());
    ScoreFragment scoreFragment = new ScoreFragment();

    // THis is fucking retarded.
    ((ReactionApplication) getApplication()).inject(scoreFragment);
    fragments.add(scoreFragment);

    pagerAdapter = new ReactionPagerAdapter(getSupportFragmentManager(), fragments);

    actionBar = getSupportActionBar();
    actionBar.setHomeButtonEnabled(false);
    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

    // Adding Tabs
    for (int i = 0; i < fragments.size(); i++) {
      actionBar.addTab(
          actionBar.newTab()
              .setTabListener(this)
              .setText(pagerAdapter.getPageTitle(i))
      );
    }

    pager.setAdapter(pagerAdapter);
  }

  @Override
  public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    // on tab selected
    // show respected fragment view
    pager.setCurrentItem(tab.getPosition());
  }

  @Override
  public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
  }

  @Override
  public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
  }

  public static class ReactionPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> fragments;

    public ReactionPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
      super(fm);
      this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int i) {
      return fragments.get(i);
    }

    @Override
    public int getCount() {
      return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
      Fragment fragment = fragments.get(position);
      if (fragment instanceof GameFragment) {
        return "Game";
      }

      if (fragment instanceof ScoreFragment) {
        return "Score";
      }

      return "Unknown";
    }
  }
}
