package com.example.game.score_board;

import com.example.game.user.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Logic for scoreboard.
 */
public class Score implements Serializable {
  private ArrayList<User> topFive;

  /** Construct a new topFive ArrayList. */
  public Score() {
    topFive = new ArrayList<>();
  }

  /**
   * Getter for topFive ArrayList.
   *
   * @return Return the topFive ArrayList.
   */
  public ArrayList<User> getTopFive() {
    return topFive;
  }

  /**
   * Add a user to topFive ArrayList.
   *
   * @param user User to be added in.
   */
  public void addScoreUser(User user) {
    topFive.add(user);
  }

  /**
   * Check whether the user is currently in our topFive List.
   *
   * @param user User to be check for.
   * @return Return true if user is in topFive, false if the user is not.
   */
  public boolean checkTopFive(User user) {
    for (int i = 0; i < topFive.size(); i++) {
      if (topFive.get(i).getUsername().equals(user.getUsername())) {
        return true;
      }
    }
    return false;
  }

  /**
   * Insert the user to our TopFive List. If the user is already in the list then return nothing, if
   * the list length is less than five then add the user in then sorted, else add the user then sort
   * then pop the last one.
   *
   * @param user User to be check for.
   */
  public void insertTopFive(User user) {
    if (checkTopFive(user)) {
      for (Iterator<User> iterator = topFive.iterator(); iterator.hasNext(); ) {
        User u = iterator.next();
        if (u.getUsername().equals(user.getUsername())) {
          iterator.remove();
        }
      }

      topFive.add(user);
      sortTopFive();

    } else if (topFive.size() < 5) {
      addScoreUser(user);
      sortTopFive();
    } else {
      addScoreUser(user);
      sortTopFive();
      topFive.remove(topFive.size() - 1);
    }
  }

  /** Sort the current topFive List according to their points. */
  public void sortTopFive() {
    Collections.sort(
        topFive,
        new Comparator<User>() {
          public int compare(User self, User other) {
            Integer first = self.getDataManager().getPoints();
            Integer second = other.getDataManager().getPoints();
            return second.compareTo(first);
          }
        });
  }
}
