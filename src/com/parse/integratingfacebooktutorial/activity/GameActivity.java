package com.parse.integratingfacebooktutorial.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.parse.integratingfacebooktutorial.R;

import java.util.Random;

public class GameActivity extends Activity {

  private static Random random = new Random();
  private static ReactionTimer reactionTimer = new ReactionTimer();

  private Button gameButton;
  private LinearLayout gameLayout;
  private TextView gameTime;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.game);

    gameTime = (TextView) findViewById(R.id.game_time);

    gameLayout = ((LinearLayout) findViewById(R.id.game_background));
    gameLayout.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        reactionTimer.endTimer();
        gameTime.setText(reactionTimer.getElapsedTimeMs() + "ms");
      }
    });

    gameButton = ((Button) findViewById(R.id.start_game));
    gameButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        gameLayout.setBackgroundColor(Color.BLACK);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
          @Override
          public void run() {
            gameLayout.setBackgroundColor(Color.BLUE);
            reactionTimer.startTimer();
          }
        }, nextInt(1000, 10000));
      }
    });
  }

  @Override
  protected void onResume() {
    super.onResume();
  }

  private int nextInt(int low, int high) {
    return random.nextInt(high - low) + low;
  }

  private static class ReactionTimer {
    private long startTime = 0;
    private long endTime = 0;

    public void startTimer() {
      startTime = System.nanoTime();
    }

    public void endTimer() {
      endTime = System.nanoTime();
    }

    public long getElapsedTimeMs() {
      if (startTime == 0 || endTime == 0) {
        return 0;
      }

      return (endTime - startTime) / 1000000L;
    }
  }

}
