package com.example.game.maze.level_game.puzzle.sudoku;

import java.util.ArrayList;
import java.util.Random;
//This class has a reference about how to generate the puzzle from the author, tuananhcnt55vmu.
  class NumberPuzzleGenerator {

  private static NumberPuzzleGenerator instance;
  private ArrayList<ArrayList<Integer>> available = new ArrayList<>();
  private Random randomNumber = new Random();

  private NumberPuzzleGenerator() {}

  static NumberPuzzleGenerator getInstance() {
    if (instance == null) {
      instance = new NumberPuzzleGenerator();
    }
    return instance;
  }

  int[][] generateGrid() {
    int[][] numberPuzzle = new int[9][9];
    int currentPosition = 0;

    clearGrid(numberPuzzle);

    while (currentPosition < 81) {
      if (available.get(currentPosition).size() != 0) {
        int i = randomNumber.nextInt(available.get(currentPosition).size());
        int number = available.get(currentPosition).get(i);

        if (check(numberPuzzle, currentPosition, number)) {
          int x = currentPosition % 9;
          int y = currentPosition / 9;

          numberPuzzle[x][y] = number;

          available.get(currentPosition).remove(i);
          currentPosition++;
        } else {
          available.get(currentPosition).remove(i);
        }
      } else {
        for (int i = 1; i <= 9; i++) {
          available.get(currentPosition).add(i);
        }
        currentPosition--;
      }
    }
    return numberPuzzle;
  }

  private void clearGrid(int[][] numberPuzzle) {
    available.clear();
    for (int x = 0; x < 9; x++) {
      for (int y = 0; y < 9; y++) {
        numberPuzzle[x][y] = -1;
      }
    }
    for (int i = 0; i < 81; i++) {
      available.add(new ArrayList<Integer>());
      for (int j = 1; j <= 9; j++) {
        available.get(i).add(j);
      }
    }
  }

  private boolean check(int[][] numberPuzzle, int currentPosition, final int number) {
    int x = currentPosition % 9;
    int y = currentPosition / 9;

    return checkH(numberPuzzle, x, y, number)
        && checkV(numberPuzzle, x, y, number)
        && checkN(numberPuzzle, x, y, number);
  }

  //check the row
  private boolean checkH(int[][] numberPuzzle, int x, int y, int num) {
    for (int i = x - 1; i >= 0; i--) {
      if (num == numberPuzzle[i][y]) {
        return false;
      }
    }
    return true;
  }

  //check the column
  private boolean checkV(int[][] numberPuzzle, int x, int y, int num) {
    for (int i = y - 1; i >= 0; i--) {
      if (num == numberPuzzle[x][i]) {
        return false;
      }
    }
    return true;
  }

  //check the 3*3 box
  private boolean checkN(int[][] numberPuzzle, int x, int y, int num) {
    int xRegion = x / 3;
    int yRegion = y / 3;
    for (int i = xRegion * 3; i < xRegion * 3 + 3; i++) {
      for (int j = yRegion * 3; j < yRegion * 3 + 3; j++) {
        if ((i != x || j != y) && num == numberPuzzle[i][j]) {
          return false;
        }
      }
    }

    return true;
  }

  //put some value to 0
  int[][] initialNumberPuzzle(int[][] numberPuzzle, int level) {
    int i = 0;
    if (level == 0) {
      while (i < 2) {
        int x = randomNumber.nextInt(9);
        int y = randomNumber.nextInt(9);

        if (numberPuzzle[x][y] != 0) {
          numberPuzzle[x][y] = 0;
          i++;
        }
      }
    } else if (level == 1) {
      while (i < 10) {
        int x = randomNumber.nextInt(9);
        int y = randomNumber.nextInt(9);

        if (numberPuzzle[x][y] != 0) {
          numberPuzzle[x][y] = 0;
          i++;
        }
      }
    } else {
      while (i < 20) {
        int x = randomNumber.nextInt(9);
        int y = randomNumber.nextInt(9);

        if (numberPuzzle[x][y] != 0) {
          numberPuzzle[x][y] = 0;
          i++;
        }
      }
    }

    return numberPuzzle;
  }
}
