package com.example.game.maze.level_game.puzzle.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.R;
import com.example.game.user.User;

public class Level extends AppCompatActivity {
  private User user;
  private int adventureColor;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_levelchoosing);
    Intent intent = getIntent();
    user = (User) intent.getSerializableExtra("User");
    adventureColor = intent.getIntExtra("color", 0);
  }

  public void easyModel(View view) {
    Intent intent_0 = new Intent(this, PlayNumberPuzzleActivity.class);
    Bundle bundle = new Bundle();
    bundle.putInt("level", 0);
    intent_0.putExtras(bundle);
    intent_0.putExtra("User", user);
    intent_0.putExtra("color", adventureColor);
    startActivity(intent_0);
  }

  public void mediumModel(View view) {
    Intent intent_1 = new Intent(this, PlayNumberPuzzleActivity.class);
    Bundle bundle = new Bundle();
    bundle.putInt("level", 1);
    intent_1.putExtras(bundle);
    intent_1.putExtra("User", user);
    intent_1.putExtra("color", adventureColor);
    startActivity(intent_1);
  }

  public void hardModel(View view) {
    Intent intent_2 = new Intent(this, PlayNumberPuzzleActivity.class);
    Bundle bundle = new Bundle();
    bundle.putInt("level", 2);
    intent_2.putExtras(bundle);
    intent_2.putExtra("User", user);
    intent_2.putExtra("color", adventureColor);
    startActivity(intent_2);
  }
}
