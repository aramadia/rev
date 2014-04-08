package com.uwrev.reactiontest.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.parse.*;
import com.uwrev.reactiontest.R;
import com.uwrev.reactiontest.ReactionApplication;
import com.uwrev.reactiontest.UserManager;
import com.uwrev.reactiontest.activity.MainActivity;
import com.uwrev.reactiontest.activity.UserDetailsActivity;
import com.uwrev.reactiontest.adapter.ScoreAdapter;
import com.uwrev.reactiontest.model.RevScore;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Joshua Lauer
 */
public class ScoreFragment extends ReactionBaseFragment {

  @InjectView(R.id.score_list) ListView scoreList;
  @InjectView(R.id.progress_spinner) ProgressBar progressSpinner;
  @Inject UserManager userManager;

  private ScoreAdapter adapter;

  private Dialog progressDialog;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    View root = inflater.inflate(R.layout.score_layout, container, false);

    ButterKnife.inject(this, root);

    adapter = new ScoreAdapter(getActivity());
    scoreList.setAdapter(adapter);

    getScoresAsync();

    return root;
  }

  @OnClick(R.id.button_add_score)
  public void addScore() {
    // Lets submit a dummy score here too.
    RevScore highScore = new RevScore();

    highScore.setScore((int)(Math.random() * 1000));
    highScore.setDate(new Date());
    highScore.setGameMode("default");

    userManager.reportScore(highScore);
  }

  @OnClick(R.id.button_change_name)
  public void changeName() {
    AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());

    alert.setTitle("Change your username");
    alert.setMessage("Enter your new name:");

// Set an EditText view to get user input
    final EditText input = new EditText(getActivity());
    input.setText(userManager.getUser().getVisibleName());

    alert.setView(input);

    alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
      public void onClick(DialogInterface dialog, int whichButton) {
        String value = input.getText().toString();
        // Do something with value!
        userManager.changeName(value);
      }
    });

    alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
      public void onClick(DialogInterface dialog, int whichButton) {
        // Canceled.
      }
    });

    alert.show();

  }

  @OnClick(R.id.button_facebook)
  public void connectFacebook() {
    // check if already connected

    // Check if there is a currently logged in user
    // and they are linked to a Facebook account.
    ParseUser currentUser = ParseUser.getCurrentUser();
    if ((currentUser != null) && ParseFacebookUtils.isLinked(currentUser)) {
      // Go to the user info activity
    }
    else {
      onLoginButtonClicked();
    }
  }

  private void getScoresAsync() {
    scoreList.setVisibility(View.GONE);
    progressSpinner.setVisibility(View.VISIBLE);

    ParseQuery<RevScore> query = ParseQuery.getQuery("RevScore");
    query.include(RevScore.USER);
    query.findInBackground(new FindCallback<RevScore>() {
      public void done(List<RevScore> revScores, ParseException e) {
        if (e == null) {
          Log.d("score", "Retrieved " + revScores.size() + " scores");
          progressSpinner.setVisibility(View.GONE);
          scoreList.setVisibility(View.VISIBLE);
          adapter.setScores(revScores);
        } else {
          Log.e("score", "Error: " + e.getMessage());
        }
      }
    });
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    ParseFacebookUtils.finishAuthentication(requestCode, resultCode, data);
  }

  private void onLoginButtonClicked() {
    ScoreFragment.this.progressDialog = ProgressDialog.show(
       getActivity(), "", "Logging in...", true);
    List<String> permissions = Arrays.asList("basic_info", "user_about_me",
        "user_relationships", "user_birthday", "user_location");
    ParseFacebookUtils.logIn(permissions, getActivity(), new LogInCallback() {
      @Override
      public void done(ParseUser user, ParseException err) {
        ScoreFragment.this.progressDialog.dismiss();
        if (user == null) {
          Log.d(ReactionApplication.TAG,
              "Uh oh. The user cancelled the Facebook login.");
        } else if (user.isNew()) {
          Log.d(ReactionApplication.TAG,
              "User signed up and logged in through Facebook!");

        } else {
          Log.d(ReactionApplication.TAG,
              "User logged in through Facebook!");
        }
      }
    });
  }

}