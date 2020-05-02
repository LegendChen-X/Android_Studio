package com.example.game.user;

import java.io.Serializable;

/** A class for user in-game stats storage and modification. */
public class DataManager implements Serializable {
  private int gold;
  private int points;
  private int energy;

  /**
   * Construct DataManager.
   *
   * @param gold Initial gold value the user has.
   * @param points Initial point value the user has.
   * @param energy Initial energy value the user has.
   */
  public DataManager(int gold, int points, int energy) {
    setGold(gold);
    setPoints(points);
    setEnergy(energy);
  }

  /**
   * Setter for gold value
   *
   * @param gold New gold value.
   */
  public void setGold(int gold) {
    this.gold = gold;
  }

  /**
   * Setter for point value
   *
   * @param points New point value.
   */
  public void setPoints(int points) {
    this.points = points;
  }

  /**
   * Setter for energy value
   *
   * @param energy New energy value.
   */
  public void setEnergy(int energy) {
    this.energy = energy;
  }

  /**
   * Getter for gold value
   *
   * @return Return current gold value the user has.
   */
  public int getGold() {
    return this.gold;
  }

  /**
   * Getter for point value
   *
   * @return Return current point value the user has.
   */
  public int getPoints() {
    return this.points;
  }

  /**
   * Getter for energy value
   *
   * @return Return current energy value the user has.
   */
  public int getEnergy() {
    return this.energy;
  }

  /**
   * Increase the user's Gold values by gold
   *
   * @param gold Additional gold values.
   */
  public void increaseGold(int gold) {
    this.gold += gold;
  }

  /**
   * Decrease the user's Gold values by gold
   *
   * @param gold gold to be decrease.
   */
  public void decreaseGold(int gold) {
    this.gold -= gold;
  }

  /**
   * Increase the user's Energy values by energy
   *
   * @param energy Additional energy values.
   */
  public void increaseEnergy(int energy) {
    this.energy += energy;
  }

  /**
   * Decrease the user's Energy values by energy
   *
   * @param energy energy to be decrease.
   */
  public void decreaseEnergy(int energy) {
    this.energy -= energy;
  }

  /**
   * Increase the user's Point values by point
   *
   * @param points Additional point values.
   */
  public void increasePoints(int points) {
    this.points += points;
  }

  /**
   * Decrease the user's Energy values by energy
   *
   * @param points point to be decrease.
   */
  public void decreasePoints(int points) {
    this.points -= points;
  }
}
