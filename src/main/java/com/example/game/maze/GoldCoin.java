package com.example.game.maze;

import android.graphics.Color;

import com.example.game.user.FileStorage;
import com.example.game.user.User;

import java.io.Serializable;
import java.util.ArrayList;

import static com.example.game.maze.Labyrinth.COLS;
import static com.example.game.maze.Labyrinth.ROWS;
import java.util.Random;

public class GoldCoin implements Collectible, Serializable {
    /** example class for Collectible object */
    private ArrayList<Cell> items;
    private int itemNum = 3;
    /** Change to customize how many object you want to show on maze.*/
    private Random random = new Random();
    private int color;

    /** set the color of this object so that we can customize color for different collectible object.*/

    public GoldCoin(){
        color = Color.YELLOW;
    }

    public int getColor(){ return color;}

    public void createItem(Cell[][] cells){
        int i, j, k, l;
        l = Math.min(COLS, ROWS) / 4;
        items = new ArrayList<>();
        //create an ArrayList that store Cell object which indicate the
        //location of this object in maze
        for (i = 0; i < itemNum; i++) {
            j = random.nextInt(COLS - l) + l;
            k = random.nextInt(ROWS - l) + l;
            items.add(cells[j][k]);
        }
    }
    /** take the Cell adventurer as parameter */
    public boolean collectBy(Cell adventurer) {
        int collect = items.size();
        // track which item in the items is collected
        for(int i = 0; i < items.size(); i++){
            if(items.get(i).col == adventurer.col && items.get(i).row == adventurer.row){
                // collect when adventurer and item are overlapped
                collect = i;
                break;
            }
        }
        if(collect != items.size()){
            items.remove(collect);
            return true;
        }
        return false;
    }

    public void updateUser(User user) {
        user.getDataManager().increaseGold((int)(Math.random() * 5) + 1);
        GameView.setCoinCollected(true);
    }

    public ArrayList<Cell> getItems() {
        return items;
    }
}


