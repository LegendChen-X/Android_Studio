package com.example.game.maze.level_game.guess_number;

import com.example.game.user.DataManager;
import com.example.game.user.User;

import java.io.Serializable;

/** A class for logic and database storing. */
public class GuessNumberData implements Serializable {
  /** current user */
  private User user;

  /** data manager for this user */
  private DataManager dataManager;

  /** colour of block in maze */
  private int adventureColor;

  /** The random number between 1 and 50 to be guessed by the player */
  private int randomNum = (int) Math.ceil(Math.random() * 50);

  /** If the current language is English or not */
  private boolean english = true;

  /** Number of attempts */
  private int totalTries = 5;

  /** Lower bound for number guessing */
  private int lowerB = 1;

  /** Upper bound for number guessing */
  private int upperB = 50;

  /** Increases the user's points */
  void increasePoints() {
    // increase the points by the two times the number of tries left
    dataManager.increasePoints(totalTries * 2);
  }

  /** Decreases the user's points */
  void decreasePoints() {
    // decrease the points by the two times the number of tries left
    dataManager.decreasePoints(totalTries * 2);
  }

  /** Getter for d. */
  int getRandomNum() {
    return randomNum;
  }

  /** Getter for english. */
  boolean isEnglish() {
    return english;
  }

  /**
   * Setter for english.
   *
   * @param b whether the current language is English
   */
  void setEnglish(boolean b) {
    english = b;
  }

  /** Getter for totalTries. */
  int getTotalTries() {
    return totalTries;
  }

  /**
   * Setter for totalTries.
   *
   * @param tries the updated number of tries
   */
  void setTotalTries(int tries) {
    totalTries = tries;
  }

  /**
   * Getter for lower bound.
   *
   * @return lowerB
   */
  int getLowerB() {
    return lowerB;
  }

  /**
   * Setter for lower bound.
   *
   * @param lower new lower bound.
   */
  void setLowerB(int lower) {
    lowerB = lower;
  }

  /**
   * Getter for upper bound.
   *
   * @return upperB
   */
  int getUpperB() {
    return upperB;
  }

  /**
   * Setter for upper bound.
   *
   * @param upper new upper bound.
   */
  void setUpperB(int upper) {
    upperB = upper;
  }

  /** */
  public void setUser(User user) {
    this.user = user;
    dataManager = user.getDataManager();
  }

  /**
   * Get current user.
   *
   * @return user
   */
  public User getUser() {
    return user;
  }

  /**
   * Set adventureColor
   *
   * @param color the color
   */
  void setAdventureColor(int color) {
    adventureColor = color;
  }

  /**
   * Get current maze block colour.
   *
   * @return adventureColor
   */
  int getAdventureColor() {
    return adventureColor;
  }
}
