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

/** A class for new user sign up. */
public class UserSignUp extends AppCompatActivity {
  private EditText username;
  private EditText password;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_user_sign_up);
    username = findViewById(R.id.SignUpName);
    password = findViewById(R.id.SignUpPass);
  }

  /**
   * Add user into the UserManager. If local file has not created yet, then create the file and add
   * the user. If local file has been created, extract the userManager in the file and add user then
   * replace the file with new UserManager.
   */
  public void addUser(View view) {
    String name = username.getText().toString();
    String pass = password.getText().toString();
    User user = new User(name, pass);
    String filename = name + ".ser";
    if (FileStorage.getInstance().fileNotExist(this.getApplicationContext(), filename)) {
      FileStorage.getInstance().saveToFile(this.getApplicationContext(), user, filename);
      Intent intent = new Intent(UserSignUp.this, MainActivity.class);
      startActivity(intent);
    } else {
      TextView textView = findViewById(R.id.IfDuplicateName);
      textView.setText("Please Select another User Email!!");
      username.setText("");
      password.setText("");
    }
  }

  /**
   * Return to Game Center Page.
   *
   * @param view
   */
  public void goBack(View view) {
    Intent intent = new Intent(this, MainActivity.class);
    startActivity(intent);
  }
}
