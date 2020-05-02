package com.example.game.score_board;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.game.initial_interface.GameCenter;
import com.example.game.R;
import com.example.game.user.User;

import java.util.ArrayList;

/** Displays scoreboard. */
public class ScoreActivity extends AppCompatActivity {
  private Score score;
  private User user;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_score);

    user = (User) getIntent().getSerializableExtra("User");

    score = (Score) getIntent().getSerializableExtra("Score");

    TextView[] nameArray = new TextView[5];
    nameArray[0] = findViewById(R.id.UserName0);
    nameArray[1] = findViewById(R.id.UserName1);
    nameArray[2] = findViewById(R.id.UserName2);
    nameArray[3] = findViewById(R.id.UserName3);
    nameArray[4] = findViewById(R.id.UserName4);

    TextView[] scoreArray = new TextView[5];
    scoreArray[0] = findViewById(R.id.UserPoint0);
    scoreArray[1] = findViewById(R.id.UserPoint1);
    scoreArray[2] = findViewById(R.id.UserPoint2);
    scoreArray[3] = findViewById(R.id.UserPoint3);
    scoreArray[4] = findViewById(R.id.UserPoint4);

    // set top five users
    ArrayList<User> userScore = score.getTopFive();
    for (int i = 0; i < userScore.size(); i++) {
      String name = userScore.get(i).getUsername();
      int point = userScore.get(i).getDataManager().getPoints();
      nameArray[i].setText(name);
      scoreArray[i].setText(String.valueOf(point));
    }
  }

  /** Return to Game Center. */
  public void startGameCenter(View view) {
    Intent intent = new Intent(this, GameCenter.class);
    intent.putExtra("User", user);
    startActivity(intent);
  }
}
