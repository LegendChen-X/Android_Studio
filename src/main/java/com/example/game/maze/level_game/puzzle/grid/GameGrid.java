package com.example.game.maze.level_game.puzzle.grid;

import android.content.Context;
import android.widget.Toast;

import com.example.game.maze.level_game.puzzle.cell.Cell;
import com.example.game.maze.level_game.puzzle.sudoku.Engine;
import com.example.game.maze.level_game.puzzle.sudoku.NumberPuzzleChecker;

// This class we create the game board and array of cells and set method to check whether or not the player finishes
// the game.
public class GameGrid {
  private Cell[][] Puzzle = new Cell[9][9];

  private Context context;

  public GameGrid(Context context) {
    this.context = context;
    for (int x = 0; x < 9; x++) {
      for (int y = 0; y < 9; y++) {
        Puzzle[x][y] = new Cell(context);
      }
    }
  }

  // initial the value for grid
  public void initialGrid(int[][] grid) {
    for (int x = 0; x < 9; x++) {
      for (int y = 0; y < 9; y++) {
        Puzzle[x][y].setValue(grid[x][y]);
        if (grid[x][y] != 0) Puzzle[x][y].setNotUpdatable();
      }
    }
  }

  Cell getItem(int pos) {
    int x = pos % 9;
    int y = pos / 9;

    return Puzzle[x][y];
  }

  private Cell getElement(int x, int y) {
    return Puzzle[x][y];
  }

  //set the nuw number for cell
  public void setNumber(int x, int y, int num) {
    Puzzle[x][y].updateValue(num);
  }

  //check if the puzzle has been solved
  public void check() {
    int[][] grid = new int[9][9];

    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        grid[i][j] = getElement(i, j).getValue();
      }
    }
    if (NumberPuzzleChecker.getInstance().checkNumberPuzzle(grid)) {
      Engine.getInstance().setGameStatus(true);
    }
  }
  // Message when player win
  public void showWinMessage() {
    Toast.makeText(context, "You win and you get points", Toast.LENGTH_LONG).show();
  }
  // Message when player get wrong answer
  public void showNotWinMessage() {
    Toast.makeText(context, "Plz, try again", Toast.LENGTH_LONG).show();
  }

  // Message when time is down
  public void showLoseMessage() {
    Toast.makeText(context, "You lose", Toast.LENGTH_LONG).show();
  }
}
