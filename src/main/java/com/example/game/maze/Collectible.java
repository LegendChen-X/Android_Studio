package com.example.game.maze;
import com.example.game.user.User;

import java.util.ArrayList;

/** interface for creating a collectible object in maze. Example class see GoldCoin.
Note: Don't forget to make the collectible object serializable and declare your new Collectible
 object in the Labyrinth class and the createCollection method*/

public interface Collectible {
    boolean collectBy(Cell adventurer);
    void createItem(Cell[][] cells);
    ArrayList<Cell> getItems();
    int getColor();
    void updateUser(User user);
}
