package com.example.game.maze.level_game.button_game;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.maze.MazeActivity;
import com.example.game.R;
import com.example.game.user.User;

/**
 * A class that receives button clicking game result, displays it, then passes results to
 * MazeActivity.
 */
public class GameOver extends AppCompatActivity {
  private int adventureColor;
  private User user;

  public GameOver() {}

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_game_over);
    Intent intent = getIntent();
    user = (User) intent.getSerializableExtra("User");
    adventureColor = intent.getIntExtra("color", 0);
    String game_result = intent.getStringExtra(Button.RESULT);
    Integer num = intent.getIntExtra(Button.TOTAL, 0);
    TextView textView = findViewById(R.id.game_result);
    textView.setText(game_result);
    TextView numClick = findViewById(R.id.textView5);
    numClick.setText("You Click: " + num.toString() + " times");
    showResult();
  }

  private void showResult() {
    new Handler()
        .postDelayed(
            new Runnable() {
              @Override
              public void run() {
                Intent intent = new Intent(GameOver.this, MazeActivity.class);
                intent.putExtra("color", adventureColor);
                intent.putExtra("User", user);
                startActivity(intent);
              }
            },
            1500);
  }
}
