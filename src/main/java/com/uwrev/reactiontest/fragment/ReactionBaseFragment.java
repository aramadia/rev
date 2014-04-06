package com.uwrev.reactiontest.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.uwrev.reactiontest.ReactionApplication;

/**
 * Created by Joshua Lauer
 */
public class ReactionBaseFragment extends Fragment {

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // Perform injection so that when this call returns all dependencies will be available for use.
    ((ReactionApplication) getActivity().getApplication()).inject(this);
  }

}
