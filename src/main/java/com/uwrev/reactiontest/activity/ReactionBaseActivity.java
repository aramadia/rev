package com.uwrev.reactiontest.activity;

import android.app.Activity;
import android.os.Bundle;
import com.uwrev.reactiontest.ReactionTestApplication;

/**
 * Created by Joshua Lauer
 */

public abstract class ReactionBaseActivity extends Activity {
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // Perform injection so that when this call returns all dependencies will be available for use.
    ((ReactionTestApplication) getApplication()).inject(this);
  }
}