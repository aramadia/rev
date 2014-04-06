package com.uwrev.reactiontest.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.uwrev.reactiontest.R;
import com.uwrev.reactiontest.model.RevScore;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joshua Lauer
 */
public class ScoreAdapter extends BaseAdapter {

  private final List<RevScore> scores = new ArrayList<RevScore>();
  private final Context context;

  public ScoreAdapter(Context context) {
    this.context = context;
  }

  @Override
  public int getCount() {
    return scores.size();
  }

  @Override
  public Object getItem(int position) {
    return scores.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    if (convertView == null) {
      convertView = LayoutInflater.from(context).inflate(R.layout.score_list_item, parent, false);
    }

    assert convertView != null;

    RevScore revScore = scores.get(position);

    TextView scoreText = (TextView) convertView.findViewById(R.id.score_text);
    scoreText.setText(revScore.toString());
    scoreText.setContentDescription(revScore.toString());

    if (position % 2 == 0) {
      convertView.setBackgroundColor(Color.parseColor("#000033"));
    } else {
      convertView.setBackgroundColor(Color.parseColor("#003300"));
    }

    return convertView;
  }

  public void setScores(List<RevScore> scoreList) {
    scores.clear();
    scores.addAll(scoreList);
    notifyDataSetChanged();
  }
}
