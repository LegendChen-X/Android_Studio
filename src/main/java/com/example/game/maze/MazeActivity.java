package com.example.game.maze;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.initial_interface.GameCenter;
import com.example.game.maze.level_game.guess_number.GuessNumberActivity;
import com.example.game.R;
import com.example.game.maze.level_game.button_game.Button;
import com.example.game.maze.level_game.puzzle.activity.Level;
import com.example.game.user.FileStorage;
import com.example.game.user.User;

public class MazeActivity extends AppCompatActivity {
  private static int color;
  private GameView mazeView;
  private User user;
  private String filename;
  private String username;
  private TextView textViewPoint;
  private TextView textViewCoin;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_maze);

    Intent intent = getIntent();
    user = (User) intent.getSerializableExtra("User");
    username = user.getUsername();
    filename = username + "State.ser";
    color = intent.getIntExtra("color", 0);
    mazeView = findViewById(R.id.mazeView);
    mazeView.changeAdventureColor(color);

    for (int i = 5; i > user.getDataManager().getEnergy(); i--) {
      String energyId = "mazeenergy" + i;
      int id = getResources().getIdentifier(energyId, "id", getPackageName());
      ImageView energy = findViewById(id);
      // remove the energy
      energy.setVisibility(View.INVISIBLE);
    }

    if (!FileStorage.getInstance().fileNotExist(this, filename)) {
      mazeView.setLabyrinth((Labyrinth)FileStorage.getInstance().readFromFile(this,
              filename));
      mazeView.getLabyrinth().setUser(user);
    } else {
      mazeView.getLabyrinth().setUser(user);
      FileStorage.getInstance().saveToFile(this, mazeView.getLabyrinth(), filename);
    }

    textViewPoint = findViewById(R.id.mazepoint);
    textViewPoint.setText(String.valueOf(user.getDataManager().getPoints()));
    textViewCoin = findViewById(R.id.mazecoinnumber);
    textViewCoin.setText("X " + String.valueOf(user.getDataManager().getGold()));

  }

  public void checkState(View view) {
    if (GameView.buttonGameIsCalled()) {
      this.callButtonClickGame(view);
      GameView.setButtonGameCalled(false);
    }
    if (GameView.puzzleIsCalled()) {
      this.callPuzzleGame(view);
      GameView.setPuzzleCalled(false);
    }
    if (GameView.guessNumberIsCalled()) {
      this.callGuessGame(view);
      GameView.setGuessNumberCalled(false);
    }
    textViewPoint.setText(String.valueOf(user.getDataManager().getPoints()));
    textViewCoin.setText("X " + String.valueOf(user.getDataManager().getGold()));
    if (GameView.coinsAreCollected()) {
      saveMazeButton(view);
      GameView.setCoinCollected(false);
    }
  }

  public void sendUpButton(View view) {
    mazeView.updateMaze(Labyrinth.UP);
    checkState(view);
  }

  public void sendDownButton(View view) {
    mazeView.updateMaze(Labyrinth.DOWN);
    checkState(view);
  }

  public void sendLeftButton(View view) {
    mazeView.updateMaze(Labyrinth.LEFT);
    checkState(view);
  }

  public void sendRightButton(View view) {
    mazeView.updateMaze(Labyrinth.RIGHT);
    checkState(view);
  }

  public void callButtonClickGame(View view) {
    saveMazeButton(view);
    if (user.getDataManager().getEnergy() == 0) {
      this.callNoEnergy(view);
    } else {
      user.getDataManager().decreaseEnergy(1);
      Intent intent = new Intent(this, Button.class);
      intent.putExtra("User", user);
      intent.putExtra("color", color);
      startActivity(intent);
    }
  }

  public void callPuzzleGame(View view){
    saveMazeButton(view);
    if (user.getDataManager().getEnergy() == 0) {
      this.callNoEnergy(view);
    } else {
      user.getDataManager().decreaseEnergy(1);
      Intent intent = new Intent(this, Level.class);
      intent.putExtra("User", user);
      intent.putExtra("color", color);
      startActivity(intent);
    }
  }

  public void callGuessGame(View view){
    saveMazeButton(view);
    if (user.getDataManager().getEnergy() == 0) {
      this.callNoEnergy(view);
    } else {
      user.getDataManager().decreaseEnergy(1);
      Intent intent = new Intent(this, GuessNumberActivity.class);
      intent.putExtra("User", user);
      intent.putExtra("color", color);
      startActivity(intent);
    }
  }

  public void quitMazeButton(View view) {
    Intent intent = new Intent(this, GameCenter.class);
    intent.putExtra("User", user);
    startActivity(intent);
  }

  public void saveMazeButton(View view) {
    FileStorage.getInstance().saveToFile(this, user,
            user.getUsername() + ".ser");
    FileStorage.getInstance().saveToFile(this, mazeView.getLabyrinth(), filename);
  }

  public void callNoEnergy(View view){
    Intent intent = new Intent(this, NoEnergy.class);
    intent.putExtra("User", user);
    intent.putExtra("Color", color);
    startActivity(intent);
  }
}