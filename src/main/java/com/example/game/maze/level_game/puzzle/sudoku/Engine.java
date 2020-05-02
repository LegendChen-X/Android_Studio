package com.example.game.maze.level_game.puzzle.sudoku;

import android.content.Context;

import com.example.game.maze.level_game.puzzle.grid.GameGrid;

public class Engine {
  private static Engine engine;
  private GameGrid grid = null; // 9*9 Array list
  // private User user;
  private boolean isFinished;

  private int currentPositionX = -1;
  private int currentPositionY = -1;

  private Engine() {
    isFinished = false;
  }

  public static Engine getInstance() {
    if (engine == null) {
      engine = new Engine();
    }
    return engine;
  }

  // generate the game grid
  public void createGrid(Context context, int level) {
    int[][] numberPuzzle = NumberPuzzleGenerator.getInstance().generateGrid();
    numberPuzzle = NumberPuzzleGenerator.getInstance().initialNumberPuzzle(numberPuzzle, level);
    grid = new GameGrid(context);
    grid.initialGrid(numberPuzzle);
    setGameStatus(false);
  }

  // return grid
  public GameGrid getGrid() {
    return grid;
  }

  // To locate the current location in grid
  public void setCurrentPosition(int currentPositionX, int currentPositionY) {
    this.currentPositionX = currentPositionX;
    this.currentPositionY = currentPositionY;
  }

  // To make the button assign value to 0 value cell
  public void updateNumber(int num) {
    if (currentPositionX != -1 && currentPositionY != -1) {
      grid.setNumber(currentPositionX, currentPositionY, num);
    }
    // Every time you need to check whether or not win.
    grid.check();
  }

  // set boolean value
  public void setGameStatus(boolean isFinished) {
    this.isFinished = isFinished;
  }
  // get boolean value
  public boolean getStatus() {
    return isFinished;
  }
}
