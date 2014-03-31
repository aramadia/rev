package com.uwrev.reactiontest.activity;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import butterknife.InjectView;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.uwrev.reactiontest.R;

import java.util.List;

/**
 * Created by aramadia on 2014-03-30.
 */
public class ScoreActivity extends ListActivity {


  @InjectView(R.id.list)
  ListView listScore;
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
   // setContentView(R.layout.score);

    // Pull in data from Parse

   // List<ParseObject> scoreList;

    ParseQuery<ParseObject> query = ParseQuery.getQuery("RevScore");
    query.findInBackground(new FindCallback<ParseObject>() {
      public void done(List<ParseObject> scoreList, ParseException e) {
        if (e == null) {
          Log.d("score", "Retrieved " + scoreList.size() + " scores");
          Object[] sArray = {"This", "is", 3.5, true, 2, "for", "bla"};
          ArrayAdapter adp = new ArrayAdapter(ScoreActivity.this, android.R.layout.simple_list_item_1, scoreList);
          setListAdapter(adp);
        } else {
          Log.d("score", "Error: " + e.getMessage());
        }
      }
    });


  }
}