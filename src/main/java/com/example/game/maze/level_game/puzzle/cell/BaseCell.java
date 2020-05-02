package com.example.game.maze.level_game.puzzle.cell;

import android.content.Context;
import android.view.View;

public class BaseCell extends View {

  private int value;

  private boolean updatable = true;

  // Constructor
  public BaseCell(Context context) {
    super(context);
  }

  @Override
  // We want to have a square. Therefore, onMeasure will take the same parameter.
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

    super.onMeasure(widthMeasureSpec, widthMeasureSpec);
  }

  // To get the value o cell
  public int getValue() {
    return value;
  }

  // To set cell with 0 value to new value.
  public void updateValue(int value) {
    if (updatable) this.value = value;

    invalidate();
  }

  // To set initial value of each cell
  public void setValue(int value) {
    this.value = value;
    invalidate();
  }

  // To set the cell with value not equal to 0 to become not updatable
  public void setNotUpdatable() {
    updatable = false;
  }
}
