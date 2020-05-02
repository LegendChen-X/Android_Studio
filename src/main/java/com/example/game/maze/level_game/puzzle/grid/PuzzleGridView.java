package com.example.game.maze.level_game.puzzle.grid;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.example.game.maze.level_game.puzzle.sudoku.Engine;
// This class we extend GridView and use adapter to achieve dynamic instantiation.

public class PuzzleGridView extends GridView {

  public PuzzleGridView(final Context context, AttributeSet attrs) {
    super(context, attrs);
    PuzzleGridViewAdapter GridViewAdapter = new PuzzleGridViewAdapter();

    setAdapter(GridViewAdapter);

    setOnItemClickListener(
        new OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            int i = position % 9;
            int j = position / 9;
            Engine.getInstance().setCurrentPosition(i, j);

            Toast.makeText(context, "Selected item - x: " + i + " y: " + j, Toast.LENGTH_SHORT)
                .show();
          }
        });
  }

  @Override
  protected void onMeasure(int widthSpec, int heightSpec) {
    super.onMeasure(widthSpec, widthSpec);
  }

  // Use adapter to connect date and view.
  class PuzzleGridViewAdapter extends BaseAdapter {
    PuzzleGridViewAdapter() {}

    @Override
    public int getCount() {
      return 81;
    }

    @Override
    public Object getItem(int position) {
      return null;
    }

    @Override
    public long getItemId(int position) {
      return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      return Engine.getInstance().getGrid().getItem(position);
    }
  }
}
