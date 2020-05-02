package com.example.game.maze.level_game.puzzle.cell;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Cell extends BaseCell {
  private Paint myPaint;

  // Constructor
  public Cell(Context context) {
    super(context);
    myPaint = new Paint();
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    drawNumbers(canvas);
    drawLines(canvas);
  }

  // Draw the lines for cell
  private void drawLines(Canvas canvas) {
    myPaint.setColor(Color.RED);
    myPaint.setStrokeWidth(3);
    myPaint.setStyle(Paint.Style.STROKE);

    canvas.drawRect(0, 0, getWidth(), getHeight(), myPaint);
  }

  // Draw the number (value) for cell
  private void drawNumbers(Canvas canvas) {
    myPaint.setColor(Color.BLACK);
    myPaint.setTextSize(60);
    myPaint.setStyle(Paint.Style.FILL);

    Rect bounds = new Rect();
    myPaint.getTextBounds(
        String.valueOf(getValue()), 0, String.valueOf(getValue()).length(), bounds);

    // If the value of cell is not equal to 0, I will draw it.
    if (getValue() != 0) {
      canvas.drawText(
          String.valueOf(getValue()),
          (getWidth() - bounds.width()) / 2,
          (getHeight() + bounds.height()) / 2,
          myPaint);
    }
  }
}
