package com.example.game.user;

import java.io.Serializable;

/** A user in this game. */
public class User implements Serializable {
  private static final long serialVersionUID = 1L;
  private String username;
  private String password;
  private DataManager dataManager;

  /**
   * Construct a user with their username and password.
   *
   * @param username the user input name.
   * @param password the user input password.
   */
  public User(String username, String password) {
    this.username = username;
    this.password = password;
    this.dataManager = new DataManager(0, 0, 5);
  }

  /**
   * Check whether the user input password match with the file's password.
   *
   * @param password: input to be checked
   */
  public boolean checkPass(String password) {
    return this.password.equals(password);
  }

  /**
   * A getter for username.
   *
   * @return username
   */
  public String getUsername() {
    return this.username;
  }

  /**
   * A getter for this user's data manager.
   *
   * @return data manager for the user.
   */
  public DataManager getDataManager() {
    return this.dataManager;
  }
}
