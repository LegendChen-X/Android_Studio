package com.example.game.maze;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.R;
import com.example.game.user.User;

public class MazeCustomization extends AppCompatActivity {
  private int color;
  private User user;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_maze_customization);
    Intent intent = getIntent();
    user = (User) intent.getSerializableExtra("User");
  }

  public void choose_blue(View view) {
    color = Color.BLUE;
    Intent intent = new Intent(this, MazeActivity.class);
    intent.putExtra("User", user);
    intent.putExtra("color", color);
    startActivity(intent);
  }

  public void choose_green(View view) {
    color = Color.GREEN;
    Intent intent = new Intent(this, MazeActivity.class);
    intent.putExtra("User", user);
    intent.putExtra("color", color);
    startActivity(intent);
  }

  public void choose_gray(View view) {
    color = Color.GRAY;
    Intent intent = new Intent(this, MazeActivity.class);
    intent.putExtra("User", user);
    intent.putExtra("color", color);
    startActivity(intent);
  }
}
