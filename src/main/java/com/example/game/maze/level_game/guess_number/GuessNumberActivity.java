package com.example.game.maze.level_game.guess_number;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.game.R;
import com.example.game.user.User;

/**
 * A guess number game. Rule: There will be a random number, d generated at the back when the game
 * begins. The player will first input a number between 1-50. If the input number is smaller than d,
 * a message "Too small, go bigger!" will pop up. If the input number is greater than d, a message
 * "Too big, go smaller!" will pop up. Then the player will enter a number again. There will be a
 * lower and upper bound displayed on the screen so that the range for guessing will be narrowed as
 * the player makes more guesses. Each unsuccessful guess will decrease the number of tries by 1.
 * The game ends when all 5 attempts are unsuccessful, or when the player has successfully guessed
 * the number. Stats: 1. Number of tries: the player gets a total number of 5 tries
 */
public class GuessNumberActivity extends AppCompatActivity {
  /** The backend database. */
  private GuessNumberData data = new GuessNumberData();

  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Intent intent = getIntent();
    setContentView(R.layout.activity_guess_number);

    // Display initial upper and lower bounds.
    TextView leftView = findViewById(R.id.guesstextView5);
    TextView rightView = findViewById(R.id.guesstextView6);
    leftView.setText(String.valueOf(data.getLowerB()));
    rightView.setText(String.valueOf(data.getUpperB()));

    // sent user and adventureColor to backend database
    data.setAdventureColor(intent.getIntExtra("color", 0));
    data.setUser((User) intent.getSerializableExtra("User"));
  }

  /** Set text on a TextView object. */
  void setText(TextView textView, CharSequence charSequence) {
    textView.setText(charSequence);
  }

  /**
   * A helper method responsible for removing one heart from the screen
   *
   * @param tries the number of tries left
   */
  private void removeHeart(int tries) {
    // get the id of the heart to be removed
    String heartId = "heart" + tries;
    int id = getResources().getIdentifier(heartId, "id", getPackageName());
    ImageView heart = findViewById(id);
    // remove the heart
    heart.setVisibility(View.INVISIBLE);
  }

  /*when player loses the game*/
  private void gameLost() {
    data.decreasePoints();
    showResultLoss();
  }

  /*when player wins the game*/
  private void gameWon() {
    data.increasePoints();
    showResultWin();
  }
  /**
   * Respond to the number inputted by the player.
   *
   * @param inputNum the number inputted by the player.
   */
  void respondToInput(TextView textView, int inputNum) {
    // if the player has more than 0 attempt left
    if (data.getTotalTries() > 0) {
      // if the number inputted by the player is correct
      if (inputNum == data.getRandomNum()) {
        // player wins
        gameWon();
      }
      // if the number inputted by the player is incorrect
      else {
        // remove a heart from screen
        removeHeart(data.getTotalTries());
        // decrease the number of tries by 1
        data.setTotalTries(data.getTotalTries() - 1);
        // if no attempts left
        if (data.getTotalTries() == 0) {
          // player loses
          gameLost();
        } else {
          // display message according to the language selected
          displayMessage(textView, inputNum);
          // if the inputted number is smaller than the randomly generated number
          if (inputNum < data.getRandomNum()) {
            TextView leftView = findViewById(R.id.guesstextView5);
            // if the inputted number is greater than the current lower bound
            if (inputNum > data.getLowerB()) {
              // set new lower bound
              data.setLowerB(inputNum);
              leftView.setText(String.valueOf(inputNum));
            }
            // if the inputted number is greater than the randomly generated number
          } else {
            TextView rightView = findViewById(R.id.guesstextView6);
            // if the inputted number is smaller than the current upper bound
            if (inputNum < data.getUpperB()) {
              // set new upper bound
              data.setUpperB(inputNum);
              rightView.setText(String.valueOf(inputNum));
            }
          }
        }
      }
    } else {
      // lose game
      gameLost();
    }
  }

  /** Lead the user to the losing page, then go back to the maze game after a brief delay. */
  private void showResultLoss() {
    new Handler()
        .postDelayed(
            new Runnable() {
              @Override
              public void run() {
                Intent intent = new Intent(GuessNumberActivity.this, GuessNumLoss.class);
                intent.putExtra("color", data.getAdventureColor());
                intent.putExtra("User", data.getUser());
                startActivity(intent);
              }
            },
            500);
  }

  /** Lead the user to the winning page, then go back to the maze game after a brief delay. */
  private void showResultWin() {
    new Handler()
        .postDelayed(
            new Runnable() {
              @Override
              public void run() {
                Intent intent = new Intent(GuessNumberActivity.this, GuessNumWin.class);
                intent.putExtra("color", data.getAdventureColor());
                intent.putExtra("User", data.getUser());
                startActivity(intent);
              }
            },
            500);
  }

  /** Start the game. */
  public void startGame(View view) {
    // get input from edittext
    EditText editText = findViewById(R.id.guesseditText2);
    String message = editText.getText().toString();
    if (!message.equals("")) {
      int inputNum = Integer.parseInt(message);
      // get the textview for displaying message
      TextView textView = findViewById(R.id.guesstextView3);
      respondToInput(textView, inputNum);
    }
  }

  /**
   * Display in-game messages according to current language.
   *
   * @param inputNum the number inputted by the user
   */
  void displayMessage(TextView textView, int inputNum) {
    if (data.isEnglish()) {
      if (inputNum < data.getRandomNum()) {
        setText(textView, "Too small, go bigger!");
      } else {
        setText(textView, "Too big, go smaller!");
      }
    } else {
      if (inputNum < data.getRandomNum()) {
        setText(textView, "太小了");
      } else {
        setText(textView, "太大了");
      }
    }
  }

  /** Switch between two languages. */
  public void setLanguage(View view) {
    data.setEnglish(!data.isEnglish());
    if (data.isEnglish()) {
      //      Button button = findViewById(R.id.GuessReturnCenter);
      //      setText(button, "GAME CENTER");
      Button button3 = findViewById(R.id.guessbutton5);
      setText(button3, "LET'S GOOO!");
      Button button4 = findViewById(R.id.GuessSetlanguage);
      setText(button4, "中文");

    } else {
      //      Button button = findViewById(R.id.GuessReturnCenter);
      //      setText(button, "游戏中心");
      Button button3 = findViewById(R.id.guessbutton5);
      setText(button3, "确定");
      Button button4 = findViewById(R.id.GuessSetlanguage);
      setText(button4, "ENGLISH");
    }
  }
}
