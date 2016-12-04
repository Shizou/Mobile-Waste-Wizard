package com.example.mobilewastewizard;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Handles parsing information given to us from the city of toronto and uploads
 * it into a <code>Database</code> object.
 * 
 * @author William Granados
 * @author Burhan Quadri
 * @author Justin Li 
 * 
 * @see Database
 */
public class TextFileHandler extends ActionBarActivity {

  private List<List<String>> categories;
  private List<String> totalList; 
  private Map<String, Integer> database; 


  /**
   * Constructor.
   * 
   * <p>Initializes categories then reads them from the file.
   * 
   * @param cxt the context from which this class acquires privileges for the 
   *        assets folder (in this case main activity)
   */
  public TextFileHandler(Context cxt) {
    this.categories = new ArrayList<List<String>>();
    this.totalList = new ArrayList<String>();
    this.database = new HashMap<String, Integer>();
    for (int i = 0; i < Constants.CATEGORIES_SIZE; i++) {
      this.categories.add(new ArrayList<String>());
    }
    // Now populate each categories with the terms available in the text
    // files. Just for reference, here are what each terminology each index
    // represents

    String[] language = {"English", "French", "Spanish"};
    for (int i = 0; i < language.length; i++) {
      this.openCategoryFile(cxt, "categories/" + language[i] + "/Blue.txt", Constants.BLUE_BIN);
      this.openCategoryFile(cxt, "categories/" + language[i] + "/BTTSWD.txt", Constants.BTTWSD);
      this.openCategoryFile(cxt, "categories/" + language[i] + "/EWaste.txt", Constants.E_WASTE);
      this.openCategoryFile(cxt, "categories/" + language[i] + "/Green.txt", Constants.GREEN_BIN);
      this.openCategoryFile(cxt, "categories/" + language[i] + "/Grey.txt", Constants.GREY_BIN);
      this.openCategoryFile(cxt, "categories/" + language[i] + "/HHW.txt", Constants.HHW);
      this.openCategoryFile(cxt, "categories/" + language[i] + "/OW.txt", Constants.OW);
      this.openCategoryFile(cxt, "categories/" + language[i] + "/PW.txt", Constants.PW);
      this.openCategoryFile(cxt, "categories/" + language[i] + "/SM.txt", Constants.SM);
      this.openCategoryFile(cxt, "categories/" + language[i] + "/YW.txt", Constants.YW);
    }
    // in the OpenCategoryFile, all of the terms are also added to a total
    // list so we are simply sorting the entries for further usage
    Collections.sort(totalList);
  }

  /**
   * Opens a file and returns contents from it into an array list.
   * 
   * @param cxt context to connect back end and front end
   * @param file absolute path to file path in our project
   * @param id  the integer id to the bin we want to append to {@link categories} 
   */
  public void openCategoryFile(Context cxt, String file, Integer id) {
    // Lets try and open the the text file that was handed to us
    // then we'll append each term to the respective list object, and
    // to a global list object
    try {
      InputStream is = cxt.getAssets().open(file);
      InputStreamReader isr = new InputStreamReader(is);
      BufferedReader br = new BufferedReader(isr);
      String line = br.readLine();
      // Read to EOF
      while (line != null) {
        this.database.put(line, id);
        totalList.add(line);
        line = br.readLine();
      }
    } catch (IOException exception) {
      // Something went terribly! Likely just that the file wasn't there
      System.out.println("File not found:" + file);
      exception.printStackTrace();
    }
  }

  /** Returns the categories which contain the items.*/
  public HashMap<String, Integer> getDatabase() {
    return (HashMap<String, Integer>) this.database;
  }

  /** Returns the total list which contains all the items.*/
  public List<String> getTotalList() {
    return this.totalList;
  }
}
