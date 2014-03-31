package com.uwrev.reactiontest.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.uwrev.reactiontest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joshua Lauer
 */
public class MainActivity extends ReactionBaseActivity implements ActionBar.TabListener {

  @InjectView(R.id.pager) ViewPager pager;
  private ActionBar actionBar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.main_layout);

    ButterKnife.inject(this);

    Fragment fragment = new MyFragment();
    Fragment fragment1 = new MyFragment();

    ArrayList<Fragment> fragments = new ArrayList<Fragment>();
    fragments.add(fragment);
    fragments.add(fragment1);

    actionBar = getSupportActionBar();
    actionBar.setHomeButtonEnabled(false);
    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

    // Adding Tabs
    for (Fragment frag : fragments) {
      actionBar.addTab(
          actionBar.newTab()
              .setTabListener(this)
              .setText("1"));
    }

    pager.setAdapter(new MyAdapter(getSupportFragmentManager(), fragments));
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

  public static class MyAdapter extends FragmentPagerAdapter {

    private final List<Fragment> fragments;

    public MyAdapter(FragmentManager fm, List<Fragment> fragments) {
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
      return String.valueOf(position);
    }
  }

  public static class MyFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      View v = inflater.inflate(R.layout.myfragment_layout, container, false);
      return v;
    }

  }
}
