package com.example.mobilewastewizard.backend;

import com.example.mobilewastewizard.generic.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.transform.sax.TransformerHandler;


/**
 * This is the main database interface we will use to handle user queries.
 *  
 * @author William Granados, Burhan Quadri, Justin Li
 *
 */
public class Database {

  private static Database instance = new Database();

  private Map<String, Constants.Categories> database = new HashMap<>();
  private List<TrashItem> totalList = new ArrayList<>();

  private Database(){}

  public static Database getInstance() {
    return instance;
  }

  /**
   * Retrieves information from the file denoted by stream and maps the item from each line
   * in the file to categoryFieldId; category field should be a constant from
   * {@link Constants}.
   *
   * <p>Note that there is error checking on the information from the file.
   *
   * @param stream file stream to collect information from
   * @param categoryFileId an integer from
   */
  public void retrieveInformationFromCategoryFile(InputStream stream, Constants.Categories categoryFileId) throws IOException{
      BufferedReader br = new BufferedReader(new InputStreamReader(stream));
      while (br.ready()) {
        String line = br.readLine();
        this.database.put(line, categoryFileId);
        this.totalList.add(new TrashItem(line, categoryFileId));
      }
  }

  /**
   * Sorts the list, this should only be done once.
   */
  public void sortTotalList() {
    Collections.sort(this.totalList);
  }

  /** Returns the a list containing all of the terms in the database. */
  public List<TrashItem> getTotalList() {
    return this.totalList;
  }

  /**
   * An item representing a piece of trash.
   */
  public static class TrashItem implements Comparable<TrashItem>, Serializable{
    public final String id;
    public final Constants.Categories category;

    public TrashItem(String id, Constants.Categories category) {
      this.id = id;
      this.category= category;
    }
    @Override
    public int compareTo(TrashItem other){
      final int BEFORE = -1;
      final int EQUAL = 0;
      final int AFTER = 1;
      if(this == other){
        return EQUAL;
      } else if(this.id.compareTo(other.id) < 0){
        return BEFORE;
      }else{
        return AFTER;
      }
    }

    @Override
    public String toString() {
      return this.id;
    }
  }
}
