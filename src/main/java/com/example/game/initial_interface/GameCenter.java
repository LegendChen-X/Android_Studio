package com.example.game.initial_interface;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.R;
import com.example.game.store.Store;
import com.example.game.maze.MazeCustomization;

import com.example.game.score_board.Score;
import com.example.game.score_board.ScoreActivity;

import com.example.game.user.FileStorage;
import com.example.game.user.User;

/** Home page of the game. */
public class GameCenter extends AppCompatActivity {
  private User user;
  private Score score;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_game_center);
    Intent intent = getIntent();
    user = (User) intent.getSerializableExtra("User");
  }

  /** Start the MazeActivity. */
  public void startMaze(View view) {
    Intent intent = new Intent(this, MazeCustomization.class);
    intent.putExtra("User", user);
    startActivity(intent);
  }

  /** Start the Store Activity. */
  public void startStore(View view) {
    Intent intent = new Intent(this, Store.class);
    intent.putExtra("User", user);
    startActivity(intent);
  }

  /** Quit the Game Center and return to Log in page. */
  public void gameCenterQuit(View view) {
    Intent intent = new Intent(this, MainActivity.class);
    startActivity(intent);
  }

  /**
   * Start the Score Activity.
   * @param view
   */
  public void scoreBoard(View view){
    extractNewScoreBoard();
    Intent intent = new Intent(this, ScoreActivity.class);
    intent.putExtra("User", user);
    intent.putExtra("Score", score);
    startActivity(intent);
  }

  /**
   * If score file doesnt exist, create a new file and add user. If exists extract the score out,
   * then perform insertTopFive method then save back to file.
   */
  private void extractNewScoreBoard(){
    if (FileStorage.getInstance().fileNotExist(this, "FinalScore1.ser")){
      score = new Score();
      score.addScoreUser(user);
      FileStorage.getInstance().saveToFile(this.getApplicationContext(), score, "FinalScore1.ser");
    } else {
      score = (Score) FileStorage.getInstance().readFromFile(this, "FinalScore1.ser");
      score.insertTopFive(user);
      FileStorage.getInstance().saveToFile(this, score, "FinalScore1.ser");
    }
  }
}
