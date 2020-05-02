package com.example.game.maze;

import android.graphics.Color;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import static com.example.game.maze.Labyrinth.COLS;
import static com.example.game.maze.Labyrinth.ROWS;

public class PlayButton implements Playable, Serializable {
    /** example class for Playable object */
    private ArrayList<Cell> items;
    private int itemNum = 3;
    /** Change to customize how many object you want to show on maze.*/
    private Random random = new Random();
    private int color;

    public PlayButton(){
        color = Color.RED;
    }

    public int getColor(){
        return color;
    }

    public void createGame(Cell[][] cells){
        int i, j, k, l;
        l = Math.min(COLS, ROWS) / 4;
        items = new ArrayList<>();
        //create an ArrayList that store Cell object which indicate the
        //location of this object in maze
        for (i=0; i < itemNum; i++) {
            j = random.nextInt(COLS - l) + l;
            k = random.nextInt(ROWS - l) + l;
            if(!cells[j][k].occupied){
                items.add(cells[j][k]);
                cells[j][k].occupied = true;
            }
        }
    }

    public ArrayList<Cell> getItems() {
        return items;
    }

  public boolean callGame(Cell adventurer) {
    for (Cell toButton : items) {
      if (adventurer == toButton) {
        GameView.setButtonGameCalled(true);
        return true;
      }
    }
    return false;
    }

}
