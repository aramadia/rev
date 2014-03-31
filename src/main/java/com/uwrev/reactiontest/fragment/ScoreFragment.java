package com.uwrev.reactiontest.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import com.uwrev.reactiontest.R;

/**
 * Created by Joshua Lauer
 */
public class ScoreFragment extends Fragment {

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.score_layout, container, false);

    ButterKnife.inject(this, root);

    return root;
  }

}