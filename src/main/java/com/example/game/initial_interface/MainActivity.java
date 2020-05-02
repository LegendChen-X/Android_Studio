package com.example.game.initial_interface;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.R;
import com.example.game.user.FileStorage;
import com.example.game.user.User;

/** A class for account management, ie. sign in for existing users and sign up for new users. */
public class MainActivity extends AppCompatActivity {
  private EditText username;
  private EditText password;
  private User user;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    username = findViewById(R.id.UserName);
    password = findViewById(R.id.Password);
  }

  /** Help the user to sign up their account. */
  public void signUp(View view) {
    Intent intent = new Intent(this, UserSignUp.class);
    startActivity(intent);
  }

  /**
   * Let the user to sign in. If local file has not created yet, direct them to signing up account
   * first. If created, check whether the user account and password match in the file then let the
   * user login.
   */
  public void signIn(View view) {
    String name = username.getText().toString();
    String pass = password.getText().toString();
    String filename = name + ".ser";
    if (!FileStorage.getInstance().fileNotExist(this.getApplicationContext(), filename)) {
      user = (User) FileStorage.getInstance().readFromFile(this.getApplicationContext(), filename);
    } else {
      TextView textView = findViewById(R.id.NameValidate);
      textView.setText("Please Sign Up First!!");
      username.setText("");
      password.setText("");
      return;
    }

    if (user.checkPass(pass)) {
      Intent intent = new Intent(MainActivity.this, GameCenter.class);
      intent.putExtra("User", user);
      startActivity(intent);
    } else {
      TextView textView = findViewById(R.id.NameValidate);
      textView.setText("Please Try Again!!!");
      password.setText("");
    }
  }
}
