package com.example.game.maze;

import android.content.Intent;

import com.example.game.user.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

class Labyrinth implements Serializable {
  static final int COLS = 7;
  static final int ROWS = 11;
  static final int MINOTAUR_NUM = 2;
  static final int GOLD_NUM = 3;
  static final float WALL_THICKNESS = 10;
  static final int UP = 1;
  static final int DOWN = 2;
  static final int LEFT = 3;
  static final int RIGHT = 4;
 //User user;
  Cell[][] cells;
  Cell adventurer, exit;
  User user;
  //ArrayList<Cell> golds;
  /** declare what Playable object you want to put in the maze here */
  PlayButton callButton;
  PlayPuzzle callPuzzle;
  PlayGuess callGuess;
  /** declare what Collectible object you want to put in the maze here */
  GoldCoin coin;
  /** the ArrayList of all kinds of Collectible object in the maze */
  ArrayList<Collectible> collection;
  /** the ArrayList of all kinds of Playable object in the maze */
  ArrayList<Playable> playStation;
  private Random random = new Random();

  Labyrinth() {
    createMaze();
  }

  public void setUser(User user) {
    this.user = user;
  }

  private void createMaze() {
    cells = new Cell[COLS][ROWS];
    Stack<Cell> visitedCells = new Stack<>();
    Cell current, next;

    // Create a 2d array of cells.
    for (int i = 0; i < COLS; i++) {
      for (int j = 0; j < ROWS; j++) {
        cells[i][j] = new Cell(i, j);
      }
    }

    current = cells[0][0];
    current.visited = true;
    // Procedure:
    //
    // If we find a neighbour of the current cell, remove the wall between the
    // current cell and the neighbour, and then do the procedure again on the neighbour.
    // Otherwise, get the last visited cell and then do the procedure on it.
    do {
      next = getNeighbour(current);
      if (next != null) {
        removeWall(current, next);
        visitedCells.push(current);
        current = next;
        current.visited = true;
      } else {
        current = visitedCells.pop();
      }
    } while (!visitedCells.isEmpty());

    // Top left corner.
    adventurer = cells[0][0];
    // Bottom right corner.
    exit = cells[COLS - 1][ROWS - 1];
    exit.occupied = true;

    // Random positions
    //createMinotaurs();
    createPlayStation();
    createCollection();

    //createGold();
  }

  private Cell getNeighbour(Cell cell) {
    ArrayList<Cell> neighbours = new ArrayList<>();

    // Check whether the left cell is visited, if there is one.
    if (cell.col > 0 && !cells[cell.col - 1][cell.row].visited)
      neighbours.add(cells[cell.col - 1][cell.row]);

    // Check whether the right cell is visited, if there is one.
    if (cell.col < COLS - 1 && !cells[cell.col + 1][cell.row].visited)
      neighbours.add(cells[cell.col + 1][cell.row]);

    // Check whether the cell above is visited, if there is one.
    if (cell.row > 0 && !cells[cell.col][cell.row - 1].visited)
      neighbours.add(cells[cell.col][cell.row - 1]);

    // Check whether the cell below is visited, if there is one.
    if (cell.row < ROWS - 1 && !cells[cell.col][cell.row + 1].visited)
      neighbours.add(cells[cell.col][cell.row + 1]);

    if (neighbours.size() == 0) return null;
    int i = random.nextInt(neighbours.size());
    return neighbours.get(i);
  }

  private void removeWall(Cell cell1, Cell cell2) {
    // If cell2 is at the left side of cell1.
    if (cell1.col - 1 == cell2.col) {
      cell1.leftWall = false;
      cell2.rightWall = false;
    }
    // If cell2 is at the right side of cell1.
    else if (cell1.col + 1 == cell2.col) {
      cell1.rightWall = false;
      cell2.leftWall = false;
    }
    // If cell2 is on top of cell1.
    else if (cell1.row - 1 == cell2.row) {
      cell1.topWall = false;
      cell2.bottomWall = false;
    }
    // If cell1 is on top of cell2.
    else {
      cell1.bottomWall = false;
      cell2.topWall = false;
    }
  }

  private void createPlayStation(){
    // Initiated what Playable object you want to add to the maze.
    callGuess = new PlayGuess();
    callPuzzle = new PlayPuzzle();
    callButton = new PlayButton();
    playStation = new ArrayList<>();
    // Adding those Playable object to the ArrayList playStation.
    playStation.add(callButton);
    playStation.add(callPuzzle);
    playStation.add(callGuess);
    for(Playable game: playStation){
      game.createGame(cells);
    }
  }

  private void createCollection(){
    coin = new GoldCoin();
    collection = new ArrayList<>();
    collection.add(coin);
    for(Collectible item : collection){
      item.createItem(cells);
    }
  }
  void moveAdventurer(int direction) {
    switch (direction) {
      case Labyrinth.LEFT:
        if (adventurer.isValidDirection(LEFT))
          adventurer = cells[adventurer.col - 1][adventurer.row];
        break;
      case Labyrinth.RIGHT:
        if (adventurer.isValidDirection(RIGHT))
          adventurer = cells[adventurer.col + 1][adventurer.row];
        break;
      case Labyrinth.UP:
        if (adventurer.isValidDirection(UP)) adventurer = cells[adventurer.col][adventurer.row - 1];
        break;
      case Labyrinth.DOWN:
        if (adventurer.isValidDirection(DOWN))
          adventurer = cells[adventurer.col][adventurer.row + 1];
        break;
    }
    if (adventurer == exit) {
        user.getDataManager().increasePoints(10);
        createMaze();
    }

    informPlayable(adventurer);
    informCollectible(adventurer);
  }

  private void informPlayable(Cell adventurer){
    for(Playable game: playStation){
      game.callGame(adventurer);
      }
    }

  private void informCollectible(Cell adventurer){
    for(Collectible item: collection){
      // for each Collectible object in collection, check whether adventurer can collect it.
      if (item.collectBy(adventurer)) {
        item.updateUser(user);
      }
    }
  }
}
