/** Name: Burhan, Justin, William
 *  Date: 24/03/15
 *  Purpose: Handles queries to the database
 * */
package com.example.mobilewastewizard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Database {
	
	private final int CATEGORIES_SIZE = 10;
	private String categoriesName[];
	private List<List<String>> categories;
	private TextFileHandler textFileSupport = new TextFileHandler();
	
	/**Initializes the database*/
	public Database() {
		this.textFileSupport = new TextFileHandler();
		this.categoriesName = new String[this.CATEGORIES_SIZE];
		this.categories = textFileSupport.getCategories();
		this.categoriesName[0] = "Blue Bin";
		this.categoriesName[1] = "Bring to Transfer Station/Waste Depot";
		this.categoriesName[2] = "E-Waste";
		this.categoriesName[3] = "Green Bin";
		this.categoriesName[4] = "Grey Bin - Garbage";
		this.categoriesName[5] = "Household Hazardous Waste";
		this.categoriesName[6] = "Oversized Waste";
		this.categoriesName[7] = "Prohibited Waste";
		this.categoriesName[8] = "Scrap Metal";
		this.categoriesName[8] = "Yard Waste";
	}
	
	/**Returns whether true if the ith character in string
	 * A is the same as the jth character in string B.
	 * @param A first string
	 * @param B second string
	 * @param i ith character in A
	 * @param j jth character in B*/
	public int diff(String A, String B,int i,int j){
		return A.charAt(i) == B.charAt(j) ? 0:1;
	}
	/**Returns the edit-distance between two strings
	 * Time complexity is O(NM) where N is the length of
	 * first string and M is the length of the second string
	 * @param A first string
	 * @param B second string  */
	public int editDistance(String A,String B){
		int N = A.length()-1, M = B.length()-1,inf = 1000000;
		int [][]dp = new int[N][M];
		for(int i = 0;i <= N;i++)
			for(int j = 0;j <= M;j++)
				dp[i][j] = inf;
		for(int i = 0;i < N;i++){
			for(int j = 0;j < M;j++){
				if(i == 0)
					dp[i][j] = j;
				else if(j == 0)
					dp[i][j] = i;
				else
					dp[i][j] = Math.min(dp[i-1][j-1]+diff(A,B,i-1,j-1), Math.min(dp[i-1][j]+1,dp[i][j-1]+1) );
			}
		}
		return dp[N][M];
	}
	/**Checks whether a pattern is present in a text field
	 * Time complexity is O(NM) where N is the length of the 
	 * text field and M is the query
	 * @param text text to search pattern from
	 * @param pattern pattern to search for*/
	public boolean search(String text,String pattern){
		// TODO use KMP algorithm for faster run times
		for(int i = 0;i < text.length();i++){
			for(int j = 0;j < pattern.length();j++){
				if(text.charAt(i+j)!=pattern.charAt(j))break;
				if(j == pattern.length()-1)return true;
			}
		}
		return false;
	}
	
	/**Does a binary search on a list  to checker whether a given string
	 * is within the list. Time complexity is O(logN) where N is the amount
	 * of elements in the list.
	 * @param A string to be searched for
	 * @param list list to be searched for*/
	public int findFromDataBase(String A,List<String>list){
		return Collections.binarySearch(list, A);
	}
	
	/**Looks through the entire database looking for an exact match
	 * @param query string to be queried*/
	public String exactQuery(String query){
		for(int i = 0;i < this.CATEGORIES_SIZE;i++){
			int index = findFromDataBase(query,categories.get(i));
			if(index >= 0)
				return categoriesName[i];
		}
		return null;
	}
	/**Looks through all of the items in the database and returns
	 * a value which contains a prefix of the queried word
	 * @param query string to be queried*/
	public List<String>suggestions(String query){
		List<String>list = new ArrayList<String>();
		String splitted[] = query.split("\\s+");
		for(int i = 0;i < this.CATEGORIES_SIZE;i++){
			for(int j = 0;j < this.categories.get(i).size();i++){
				for(int k = 0;k < splitted.length;k++){
					if(search(categories.get(i).get(j),splitted[k])){
						list.add(categories.get(i).get(j));
						break;
					}
				}
			}
		}
		return list.size() != 0 ? list:null;
	}
	/**Looks through all of the items in the database and returns
	 * the N items which are most similar to the queried string*/
	public List<String>spellingSuggestions(String query,int N){
		List<Pair<Integer,String>>list = new ArrayList<Pair<Integer,String>>();
		List<String>list2 = new ArrayList<String>();
		for(int i = 0;i < this.CATEGORIES_SIZE;i++){
			for(int j = 0;j < this.categories.get(i).size();i++){
				String s = categories.get(i).get(j);
				list.add(new Pair<Integer,String>(editDistance(s,query),s));
			}
		}
		Collections.sort(list);
		for(int i = 0;i < N;i++)
			list2.add(list.get(i).second);
		return list2.size() == 0 ? null:list2;
	}
}