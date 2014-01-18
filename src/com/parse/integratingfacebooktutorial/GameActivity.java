package com.parse.integratingfacebooktutorial;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class GameActivity extends Activity {

  private Button gameButton;
  private LinearLayout gameLayout;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.game);

    gameLayout = ((LinearLayout) findViewById(R.id.game_background));
    gameLayout.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        //
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
            Toast.makeText(GameActivity.this, "Runnable!", Toast.LENGTH_LONG).show();
          }
        }, 1500);
      }
    });
  }

  @Override
  protected void onResume() {
    super.onResume();
  }
}
