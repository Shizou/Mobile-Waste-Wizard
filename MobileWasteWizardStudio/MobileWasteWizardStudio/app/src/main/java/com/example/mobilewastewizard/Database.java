package com.example.mobilewastewizard;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


/**
 * This is the main database interface we will use to handle user queries.
 *  
 * @author William Granados
 * @author Burhan Quadri
 * @author Justin Li
 *
 */
public class Database {


  private String[] categoriesName;
  private HashMap<String, Integer> database;
  private List<String> totalList;
  private TextFileHandler textFileSupport;


  /**
   * Constructor.
   * 
   * <p>Initializes the database we will use to handle user queries.
   * 
   * @param cxt the context from which this class acquires privileges for the 
   *        assets folder (in this case main activity)
   */
  public Database(Context cxt) {
    // Initialize miscellaneous stuff
    this.textFileSupport = new TextFileHandler(cxt);
    this.categoriesName = new String[Constants.CATEGORIES_SIZE];
    this.database = textFileSupport.getDatabase();
    this.totalList = textFileSupport.getTotalList();
    // Assign each index the corresponding terminology
    this.categoriesName[Constants.BLUE_BIN] = "Blue Bin";
    this.categoriesName[Constants.BTTWSD] = "Transfer Station or Waste Depot";
    this.categoriesName[Constants.E_WASTE] = "E-Waste";
    this.categoriesName[Constants.GREEN_BIN] = "Green Bin";
    this.categoriesName[Constants.GREY_BIN] = "Grey Bin - Garbage";
    this.categoriesName[Constants.HHW] = "Household Hazardous Waste";
    this.categoriesName[Constants.OW] = "Oversized Waste";
    this.categoriesName[Constants.PW] = "Prohibited Waste";
    this.categoriesName[Constants.SM] = "Scrap Metal";
    this.categoriesName[Constants.YW] = "Yard Waste";
  }

  /**
   * Returns integer value for the character at <code>indexOne</code> in 
   * <code>strOne</code> is the same as the character at <code>indexTwo</code>
   * in <code>strTwo</code>.
   * 
   * @param strOne first string
   * @param strTwo second string
   * @param indexOne index to check in <code>strOne</code>
   * @param indexTwo index to check in <code>strTwo</code>
   * @return  1 if they're the same, 0 otherwise
   */
  public int diff(String strOne, String strTwo, int indexOne, int indexTwo) {
    if (strOne.charAt(indexOne) == strTwo.charAt(indexTwo)) {
      return 0;
    } else {
      return 1;
    }
  }

  /**
   * Returns the Levenshtein distance between two strings.
   * 
   * <p>Time complexity is <code>O(NM)</code> where N is the length of first
   *  string and M is the length of the second string.
   * 
   * @param strOne first string
   * @param strTwo second string
   * @return Levenshtein distance of both strings.
   */
  public int editDistance(String strOne, String strTwo) {
    int strOneLen = strOne.length();
    int strTwoLen = strTwo.length();
    int inf = 1000000;
    int[][] dp = new int[strOneLen][strTwoLen];
    // arbitrarily set the cost for each to be infinity
    for (int i = 0; i < strOneLen; i++) {
      for (int j = 0; j < strTwoLen; j++) {
        dp[i][j] = inf;
      }
    }
    // Now we calculate the cost or "edit distance" associated with the
    // sub strings of String A and String B. We then build off this by
    // doing some dynamic programming logic
    for (int i = 0; i < strOneLen; i++) {
      for (int j = 0; j < strTwoLen; j++) {
        if (i == 0) { 
          // In this case it takes j characters two form the first i characters of strOne
          dp[i][j] = j;
        } else if (j == 0) {
          // In this case it takes j characters two form the first i characters of strTwo 
          dp[i][j] = i;
        } else {
          // in this case it takes minimum of of the dp state itself, and
          // creating a new string from previous states
          dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i][j]);
          dp[i][j] = Math.min(dp[i - 1][j - 1] + diff(strOne, strTwo, i - 1, j - 1), dp[i][j]);
        }
      }
    }
    return dp[strOneLen - 1][strTwoLen - 1];
  }


  /**
   * Returns the category of the this user's <code>query</code> to the database.
   * 
   * @param query string to be queried
   * @return string category from {@link categoriesName} if it's found, null otherwise
   */
  public String initialQuery(String query) {
    Integer category = this.database.get(query);
    return (category != null) ? categoriesName[category] : null;
  }

  /**
   * Looks through all of the items in the database and returns values which are most similar to the
   * query. First checks for values that contain the parts of the query then checks their edit
   * distance
   * 
   * @param query string to be queried
   */
  public List<String> secondaryQuery(String query) {
    List<String> list = new ArrayList<String>();
    List<Pair<Integer, String>> list2 = new ArrayList<Pair<Integer, String>>();
    List<String> suggestions = new ArrayList<String>();
    // checks if the query if a substring
    for (String it : this.totalList) {
      if (it.contains(query)) {
        list.add(it);
      }
    }
    Collections.sort(list);
    // Now we sort will get a baring of how close these strings are by
    // using the edit distance between the two strings.
    for (int i = 0; i < Constants.LIMIT && i < list.size(); i++) {
      list2.add(new Pair<Integer, String>(editDistance(list.get(i), query), list.get(i)));
    }
    Collections.sort(list2);
    // Now we'll add the first this.LIMIT words that are most similar to the
    // user's query
    for (int i = 0; i < Constants.LIMIT && i < list2.size(); i++) {
      suggestions.add(list2.get(i).second);
    }
    return suggestions.size() == 0 ? null : suggestions;
  }

  /** Returns the a list containing all of the terms in the database. */
  public List<String> getTotalList() {
    return this.totalList;
  }
}
