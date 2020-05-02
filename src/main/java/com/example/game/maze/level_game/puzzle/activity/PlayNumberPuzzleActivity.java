package com.example.game.maze.level_game.puzzle.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.R;
import com.example.game.maze.MazeActivity;
import com.example.game.maze.level_game.puzzle.sudoku.Engine;
import com.example.game.user.User;

public class PlayNumberPuzzleActivity extends AppCompatActivity {
  private User user;
  private int adventureColor;
  int level;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_play_sudoku);
    final Bundle bundle = getIntent().getExtras();
    level = bundle.getInt("level");
    Engine.getInstance().createGrid(this, level);
    Intent intent = getIntent();
    user = (User) intent.getSerializableExtra("User");
    adventureColor = intent.getIntExtra("color", 0);
    int time = 18000;
    if (level == 1) {
      time *= 15;
    } else if (level == 2) {
      time *= 30;
    }
    new CountDownTimer(time, 1000) {

      TextView mTextField = findViewById(R.id.timeRemaining);

      public void onTick(long millisUntilFinished) {
        mTextField.setText("Time remaining:" + millisUntilFinished / 1000);
        // here you can have your logic to set text to edit text
      }

      public void onFinish() {
        if (!Engine.getInstance().getStatus()) {
          Engine.getInstance().getGrid().showLoseMessage();
          startMaze();
        }
      }
    }.start();
  }

  public void startMaze() {
    new Handler()
        .postDelayed(
            new Runnable() {
              @Override
              public void run() {
                Intent intent = new Intent(PlayNumberPuzzleActivity.this, MazeActivity.class);
                intent.putExtra("color", adventureColor);
                intent.putExtra("User", user);
                startActivity(intent);
              }
            },
            500);
  }

  public void submit(View view) {
    if (Engine.getInstance().getStatus()) {
      Engine.getInstance().getGrid().showWinMessage();
      user.getDataManager().setPoints(user.getDataManager().getPoints() + (level + 1) * 3);
      new Handler()
          .postDelayed(
              new Runnable() {
                @Override
                public void run() {
                  Intent intent = new Intent(PlayNumberPuzzleActivity.this, MazeActivity.class);
                  intent.putExtra("color", adventureColor);
                  intent.putExtra("User", user);
                  startActivity(intent);
                }
              },
              1000);
    } else {
      Engine.getInstance().getGrid().showNotWinMessage();
    }
  }
}
