package com.example.game.maze;

import java.util.ArrayList;

/** interface for creating a Playable object in maze. Example class see GoldCoin.
 Note: Don't forget to make the Playable object serializable and declare your new Playable
 object in the Labyrinth class and the createPlayStation method*/

public interface Playable {
    void createGame(Cell[][] cells);
    boolean callGame(Cell adventurer);
    int getColor();
    ArrayList<Cell> getItems();
}
