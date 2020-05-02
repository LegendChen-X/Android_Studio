package com.example.game.maze;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;


public class GameView extends View {
  public int color;
  private static boolean buttonGameCalled = false;
  private static boolean puzzleCalled = false;
  private static boolean guessNumberCalled = false;
  private static boolean coinCollected = false;

  private Labyrinth labyrinth;
  /** An ArrayList of Paint for each collectible object */
  private ArrayList<Paint> collectionPaint = new ArrayList<>();
  private ArrayList<Paint> playStationPaint = new ArrayList<>();
  private Paint wallPaint, adventurerPaint, exitPaint;
  private float cellSize, hMargin, vMargin;

  public static boolean buttonGameIsCalled() {
    return buttonGameCalled;
  }

  public static boolean puzzleIsCalled() {
    return puzzleCalled;
  }

  public static boolean guessNumberIsCalled() {
    return guessNumberCalled;
  }

  public static boolean coinsAreCollected() {
    return coinCollected;
  }

  public static void setButtonGameCalled(boolean isCalled) {
    buttonGameCalled = isCalled;
  }

  public static void setPuzzleCalled(boolean isCalled) {
    puzzleCalled = isCalled;
  }

  public static void setGuessNumberCalled(boolean isCalled) {
    guessNumberCalled = isCalled;
  }

  public static void setCoinCollected(boolean isCollected) {
    coinCollected = isCollected;
  }

  public Labyrinth getLabyrinth() {
    return labyrinth;
  }

  public void setLabyrinth(Labyrinth labyrinth) {
    this.labyrinth = labyrinth;
  }

  public GameView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);

    labyrinth = new Labyrinth();

    wallPaint = new Paint();
    wallPaint.setColor(Color.BLACK);
    wallPaint.setStrokeWidth(Labyrinth.WALL_THICKNESS);

    for (Collectible item : labyrinth.collection) {
      Paint itemPaint = new Paint();
      itemPaint.setColor(item.getColor()); // each Collectible object can have different color.
      collectionPaint.add(itemPaint);
    }

    for (Playable game : labyrinth.playStation) {
      Paint gamePaint = new Paint();
      gamePaint.setColor(game.getColor()); // each Collectible object can have different color.
      gamePaint.setStyle(Paint.Style.STROKE);
      gamePaint.setStrokeWidth((Labyrinth.WALL_THICKNESS));
      playStationPaint.add(gamePaint);
    }

    adventurerPaint = new Paint();
    adventurerPaint.setColor(Color.GRAY);

    exitPaint = new Paint();
    exitPaint.setColor(Color.BLACK);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    canvas.drawColor(Color.WHITE);

    int width = getWidth();
    int height = getHeight();

    // Decide the size of cells based on the screen.
    if (width / height < Labyrinth.COLS / Labyrinth.ROWS) cellSize = width / (Labyrinth.COLS + 1);
    else cellSize = height / (Labyrinth.ROWS + 1);

    hMargin = (width - Labyrinth.COLS * cellSize) / 2;
    vMargin = (height - Labyrinth.ROWS * cellSize) / 2;

    // Draw the walls.

    // Translate the origin from (0, 0) to (hMargin, vMargin).
    canvas.translate(hMargin, vMargin);

    for (int x = 0; x < Labyrinth.COLS; x++) {
      for (int y = 0; y < Labyrinth.ROWS; y++) {
        if (labyrinth.cells[x][y].topWall)
          canvas.drawLine(x * cellSize, y * cellSize,
                  (x + 1) * cellSize, y * cellSize, wallPaint);
        if (labyrinth.cells[x][y].bottomWall)
          canvas.drawLine(
              x * cellSize, (y + 1) * cellSize,
                  (x + 1) * cellSize, (y + 1) * cellSize, wallPaint);
        if (labyrinth.cells[x][y].leftWall)
          canvas.drawLine(x * cellSize, y * cellSize,
                  x * cellSize, (y + 1) * cellSize, wallPaint);
        if (labyrinth.cells[x][y].rightWall)
          canvas.drawLine(
              (x + 1) * cellSize, y * cellSize,
                  (x + 1) * cellSize, (y + 1) * cellSize, wallPaint);
      }
    }

    // Draw adventurer and exit squares.

    float margin = cellSize / 7;
    canvas.drawRect(
        labyrinth.adventurer.col * cellSize + margin,
        labyrinth.adventurer.row * cellSize + margin,
        (labyrinth.adventurer.col + 1) * cellSize - margin,
        (labyrinth.adventurer.row + 1) * cellSize - margin,
        adventurerPaint);

    canvas.drawRect(
        labyrinth.exit.col * cellSize + margin,
        labyrinth.exit.row * cellSize + margin,
        (labyrinth.exit.col + 1) * cellSize - margin,
        (labyrinth.exit.row + 1) * cellSize - margin,
        exitPaint);

    for (int i = 0; i < labyrinth.playStation.size(); i++) {
      Playable game = labyrinth.playStation.get(i);
      for(int j= 0; j< game.getItems().size(); j++) {
        canvas.drawRect(
                (game.getItems().get(j).col) * cellSize +  margin,
                (game.getItems().get(j).row) * cellSize +  margin,
                (game.getItems().get(j).col + 1) * cellSize - margin,
                (game.getItems().get(j).row + 1) * cellSize -  margin,
                playStationPaint.get(i));
      }
    }

    for (int i = 0; i < labyrinth.collection.size(); i++) {
      Collectible item = labyrinth.collection.get(i);
      for(int j= 0; j< item.getItems().size(); j++) {
        canvas.drawCircle(
                (item.getItems().get(j).col) * cellSize +  cellSize / 2,
                  (item.getItems().get(j).row) * cellSize + cellSize / 2,
                  (cellSize / 2) - margin * 2,
                  collectionPaint.get(i));
        }
  }
  }

  public void changeAdventureColor(int color) {
    adventurerPaint.setColor(color);
  }

  public void updateMaze(int direction) {
    labyrinth.moveAdventurer(direction);
    invalidate();
  }
}
