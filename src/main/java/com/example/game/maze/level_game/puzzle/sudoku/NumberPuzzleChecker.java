package com.example.game.maze.level_game.puzzle.sudoku;

public class NumberPuzzleChecker {
  private static NumberPuzzleChecker instance;

  private int[] collection = new int[10];

  private NumberPuzzleChecker() {}

  public static NumberPuzzleChecker getInstance() {
    if (instance == null) {
      instance = new NumberPuzzleChecker();
    }
    return instance;
  }

  public boolean checkNumberPuzzle(int[][] NumberPuzzle) {
    return (checkRows(NumberPuzzle) && checkCols(NumberPuzzle) && checkBoxes(NumberPuzzle));
  }

  private boolean checkBoxes(int[][] board) {
    for (int i = 0; i < 9; i += 3) {
      for (int j = 0; j < 9; j += 3) {
        clearRecord();
        for (int r = 0; r < 3; r++) {
          for (int c = 0; c < 3; c++) {
            if (collection[board[i + r][j + c]] == 1) return false;
            collection[board[i + r][j + c]] = 1;
          }
        }
      }
    }
    return true;
  }

  private boolean checkCols(int[][] board) {
    for (int c = 0; c < 9; c++) {
      clearRecord();
      for (int r = 0; r < 9; r++) {
        if (collection[board[r][c]] == 1) return false;
        collection[board[r][c]] = 1;
      }
    }
    return true;
  }

  private boolean checkRows(int[][] board) {
    for (int r = 0; r < 9; r++) {
      clearRecord();
      for (int c = 0; c < 9; c++) {
        if (collection[board[r][c]] == 1) return false;
        collection[board[r][c]] = 1;
      }
    }
    return true;
  }

  private void clearRecord() {
    for (int i = 1; i < 10; i++) collection[i] = 0;
  }
}
