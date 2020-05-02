package com.example.game.maze.level_game.puzzle.button;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import com.example.game.maze.level_game.puzzle.sudoku.Engine;
// This class create the function of button and the onClick event.
public class NumButtons extends Button implements View.OnClickListener {
  private int num;

  // Constructor
  public NumButtons(Context context, AttributeSet attr) {
    super(context, attr);
    setOnClickListener(this);
  }

  @Override
  // Change the value of cell with Updatable attribution
  public void onClick(View view) {
    Engine.getInstance().updateNumber(num);
  }

  // The value of the number itself.
  public void setNumber(int num) {
    this.num = num;
  }
}
