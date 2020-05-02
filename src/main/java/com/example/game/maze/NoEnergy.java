package com.example.game.maze;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.R;
import com.example.game.user.User;

/**
 * A helper class that leads the guess number game to its losing page and then goes back to the maze
 * game
 */
public class NoEnergy extends AppCompatActivity {
    private User user;
    private int color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("User");
        color = intent.getIntExtra("Color",0);
        setContentView(R.layout.activity_no_energy);
        showResult();
    }

    private void showResult() {
        new Handler()
                .postDelayed(
                        new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(NoEnergy.this, MazeActivity.class);
                                user.getDataManager().setPoints(user.getDataManager().getPoints() - 3);
                                intent.putExtra("User", user);
                                intent.putExtra("color", color);
                                startActivity(intent);
                            }
                        },
                        2000);
    }
}
