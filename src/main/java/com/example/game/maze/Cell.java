package com.example.game.maze;

import java.io.Serializable;

class Cell implements Serializable {
  boolean topWall = true;
  boolean bottomWall = true;
  boolean leftWall = true;
  boolean rightWall = true;
  boolean visited = false;
  boolean occupied = false;
  int row;
  int col;

  Cell(int col, int row) {
    this.col = col;
    this.row = row;
  }

  boolean isValidDirection(int direction) {
    if (direction == Labyrinth.LEFT) return col > 0 && !leftWall;
    if (direction == Labyrinth.RIGHT) return col < Labyrinth.COLS - 1 && !rightWall;
    if (direction == Labyrinth.UP) return row > 0 && !topWall;
    if (direction == Labyrinth.DOWN) return row < Labyrinth.ROWS - 1 && !bottomWall;
    System.err.println("Cell.isValidDirection: invalid input");
    return false;
  }
}
