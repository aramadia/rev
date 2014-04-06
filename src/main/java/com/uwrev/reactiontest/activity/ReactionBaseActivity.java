package com.uwrev.reactiontest.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import com.uwrev.reactiontest.ReactionApplication;

/**
 * Created by Joshua Lauer
 */

public abstract class ReactionBaseActivity extends ActionBarActivity {
      @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Perform injection so that when this call returns all dependencies will be available for use.
        ((ReactionApplication) getApplication()).inject(this);
  }
}