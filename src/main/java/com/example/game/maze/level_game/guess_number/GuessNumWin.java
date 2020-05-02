package com.example.game.maze.level_game.guess_number;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import com.example.game.R;
import com.example.game.maze.MazeActivity;
import com.example.game.user.User;

/**
 * A helper class that leads the guess number game to its winning page and then goes back to the
 * maze game
 */
public class GuessNumWin extends AppCompatActivity {
  private int adventureColor;
  private User user;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Intent intent = getIntent();
    user = (User) intent.getSerializableExtra("User");
    adventureColor = intent.getIntExtra("color", 0);
    setContentView(R.layout.activity_guess_num_win);
    showResult();
  }

  private void showResult() {
    new Handler()
        .postDelayed(
            new Runnable() {
              @Override
              public void run() {
                Intent intent = new Intent(GuessNumWin.this, MazeActivity.class);
                intent.putExtra("color", adventureColor);
                intent.putExtra("User", user);
                startActivity(intent);
              }
            },
            1500);
  }
}
