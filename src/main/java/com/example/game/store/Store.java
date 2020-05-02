package com.example.game.store;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.R;
import com.example.game.initial_interface.GameCenter;
import com.example.game.user.DataManager;
import com.example.game.user.FileStorage;
import com.example.game.user.User;
import com.google.android.material.snackbar.Snackbar;

/**
 * A store. The player will be able to exchange gold they collected in-game for energies to continue
 * playing in the maze.
 */
public class Store extends AppCompatActivity {
  private User user;
  private DataManager dataManager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.store);
    Intent intent = getIntent();
    user = (User) intent.getSerializableExtra("User");
    dataManager = user.getDataManager();
    /*display initial stats value*/
    displayPoint();
    displayGoldNumber();

    // display the correct number of energies
    for (int i = 5; i > dataManager.getEnergy(); i--) {
      removeEnergy(i);
    }
  }

  /**
   * A helper method responsible for removing one energy from the screen
   *
   * @param energy the current number of energies.
   */
  private void removeEnergy(int energy) {
    // get the id of the heart to be removed
    String heartId = "energy" + energy;
    int id = getResources().getIdentifier(heartId, "id", getPackageName());
    ImageView heart = findViewById(id);
    // set it to transparent
    heart.setVisibility(View.INVISIBLE);
  }

  /** Display current gold number. */
  private void displayGoldNumber() {
    TextView textView = findViewById(R.id.storecoin);
    textView.setText("X " + String.valueOf(dataManager.getGold()));
  }

  /** Display current points. */
  private void displayPoint() {
    TextView textView = findViewById(R.id.storepoint);
    textView.setText(String.valueOf(dataManager.getPoints()));
  }

  /** Display current energy. */
  private void displayEnergy() {
    String energyId = "energy" + dataManager.getEnergy();
    int id = getResources().getIdentifier(energyId, "id", getPackageName());
    ImageView energy = findViewById(id);
    energy.setVisibility(View.VISIBLE);
  }

  /** Exchange gold into energy */
  public void goldToEnergy(View view) {
    int goldNumber = dataManager.getGold();
    TextView popUp = findViewById(R.id.PopUp);
    // if the user has more than 2 coins
    if (goldNumber >= 2) {
      // and the user does not have full energy
      if (dataManager.getEnergy() < 5) {
        // decrease number of coins by 2
        dataManager.decreaseGold(2);
        // increase energy by 1
        dataManager.increaseEnergy(1);
        // display energy and gold
        displayEnergy();
        displayGoldNumber();
        // save user stats
        String filename = user.getUsername() + ".ser";
        FileStorage.getInstance().saveToFile(this.getApplicationContext(), user, filename);
        // show popup message to indicate successful exchange
        Snackbar success = Snackbar.make(popUp, "Exchange Successful!", Snackbar.LENGTH_SHORT);
        success.show();
      }
      // the user has full energy already
      else {
        // show popup message to indicate full energy and unsuccessful exchange
        Snackbar fullEnergy = Snackbar.make(popUp, "You have full energy.", Snackbar.LENGTH_SHORT);
        fullEnergy.show();
      }
    } else {
      // show popup message to indicate insufficient gold and unsuccessful exchange
      Snackbar noMoney = Snackbar.make(popUp, "No enough coins!", Snackbar.LENGTH_SHORT);
      noMoney.show();
    }
  }

  /** Return to the Game centre */
  public void returnGameCentre(View view) {
    Intent intent = new Intent(this, GameCenter.class);
    intent.putExtra("User", user);
    startActivity(intent);
  }
}
