package com.example.game.user;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/** A class for file storage. */
public class FileStorage {
  private static FileStorage obj;

  private FileStorage() {}

  public static FileStorage getInstance() {
    if (obj == null) {
      obj = new FileStorage();
    }
    return obj;
  }
  /**
   * Save the User object to the .ser file with the user name.
   *
   * @param fileContext the file context from the class
   * @throws IOException exception
   */
  public void saveToFile(Context fileContext, Object object, String filename) {
    try {
      FileOutputStream output = fileContext.openFileOutput(filename, Context.MODE_PRIVATE);
      ObjectOutputStream outputStream = new ObjectOutputStream(output);
      outputStream.writeObject(object);
      outputStream.close();
      output.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Get the User object from the .ser file that stored the User.
   *
   * @param fileContext the file context from the class
   * @param filename the file name
   * @return User the user from the file
   * @throws IOException exception
   * @throws ClassNotFoundException exception
   */
  public Object readFromFile(Context fileContext, String filename) {
    try {
      FileInputStream inputStream = fileContext.openFileInput(filename);
      ObjectInputStream output = new ObjectInputStream(inputStream);
      Object object = output.readObject();
      inputStream.close();
      output.close();
      return object;
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Check whether the user name exist among .ser file.
   *
   * @param fileContext the file context from the class
   * @param filename the file name to check
   * @return Return a path if exists, otherwise return null.
   */
  public boolean fileNotExist(Context fileContext, String filename) {
    InputStream inputStream = null;
    try {
      inputStream = fileContext.openFileInput(filename);
      inputStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return inputStream == null;
  }
}
