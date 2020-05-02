package com.example.game.maze.level_game.puzzle.button;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.example.game.R;
// This class extends GridView and uses BaseAdapter to create ButtonGridView Adapter to achieve
// dynamic instantiation.
public class ButtonsGridView extends GridView {
  public ButtonsGridView(Context context, AttributeSet attrs) {
    super(context, attrs);
    ButtonGridViewAdapter gridViewAdapter = new ButtonGridViewAdapter(context);
    setAdapter(gridViewAdapter);
  }

  class ButtonGridViewAdapter extends BaseAdapter {
    private Context context;

    ButtonGridViewAdapter(Context context) {
      this.context = context;
    }

    @Override
    public int getCount() {
      return 10;
    }

    @Override
    public Object getItem(int position) {
      return null;
    }

    @Override
    public long getItemId(int position) {
      return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      View view = convertView;
      if (view == null) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        view = inflater.inflate(R.layout.button, parent, false);
        NumButtons button;
        button = (NumButtons) view;
        button.setId(position);
        if (position != 9) {
          button.setText(String.valueOf(position + 1));
          button.setNumber(position + 1);
        } else {
          button.setText("X");
          button.setNumber(0);
        }

        return button;
      }
      return view;
    }
  }
}
