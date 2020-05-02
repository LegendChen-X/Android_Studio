package com.example.game.maze.level_game.button_game;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.R;
import com.example.game.user.DataManager;
import com.example.game.user.User;

/**
 * A button clicking game. The player will be told to click on the button a specific number of times
 * within a specific time range. The player wins by clicking the exact number of times as required.
 */
public class Button extends AppCompatActivity {
  public static final String RESULT = "com.example.button.RESULT";
  public static final String TOTAL = "com.example.button.TOTAL";
  private int num = 0;
  private int count;
  private boolean startGame = false;
  private int adventureColor;
  private String username;
  private User user;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_button);
    Intent intent = getIntent();
    // get current user
    username = intent.getStringExtra("username");
    user = (User) intent.getSerializableExtra("User");
    // set colour for player's block
    adventureColor = intent.getIntExtra("color", 0);
    // display the number of clicks that the player needs to achieve to win
    TextView textView = findViewById(R.id.random_button_num);
    count = (int) Math.round(10 * (Math.random() + 0.1));
    String random_num_str = String.valueOf(count);
    String text = "Press button " + random_num_str + " times in 2 seconds";
    textView.setText(text);

    // A timer for this button clicking game. This times counts down 3 seconds to let the player prepares.
    new CountDownTimer(3000, 500) {

      TextView mTextField = findViewById(R.id.time_count_dowm);

      public void onTick(long millisUntilFinished) {
        mTextField.setText("Game starts in: " + millisUntilFinished / 1000);
      }

      public void onFinish() {
        mTextField.setText("Start clicking");
        startGame();
      }
    }.start();
  }

  /** Start game. */
  private void startGame() {
    startGame = true;

    // Create a timer to count down for button clicking.
    new CountDownTimer(2000, 1000) {

      TextView mTextField = findViewById(R.id.time_count_dowm);

      public void onTick(long millisUntilFinished) {
        mTextField.setText("Seconds remaining:" + millisUntilFinished / 1000);
      }

      public void onFinish() {
        mTextField.setText("Game finish");
        startGame = false;
        showResult();
      }
    }.start();
  }

  /** Increase the number of times clicked by the player by 1. */
  public void countNumber(View view) {
    if (startGame) {
      num += 1;
    }
  }

  /** Show the game result. */
  private void showResult() {
    new Handler()
        .postDelayed(
            new Runnable() {
              @Override
              public void run() {
                Intent intent = new Intent(Button.this, GameOver.class);
                String game_result;
                int score;
                // player wins when the number of clicks is exactly as required
                if (num == count) {
                  game_result = "You Win!";
                  score = 3;
                  // otherwise they loses
                } else {
                  game_result = "You Lose!!!!";
                  score = -1;
                }

                intent.putExtra(TOTAL, num);
                DataManager manager = user.getDataManager();
                // update points after game finishes
                manager.setPoints(manager.getPoints() + score);
                intent.putExtra("username", username);
                intent.putExtra("User", user);
                intent.putExtra(RESULT, game_result);
                intent.putExtra("color", adventureColor);
                startActivity(intent);
              }
            },
            1500);
  }
}
