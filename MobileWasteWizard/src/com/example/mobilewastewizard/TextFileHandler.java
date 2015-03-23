/** Name: Burhan, Justin, William
 *  Date: 24/03/15
 *  Purpose: Handles the reading in from database
 * */

package com.example.mobilewastewizard;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import android.content.res.AssetManager;
import android.content.*;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class TextFileHandler extends ActionBarActivity  {
	
	private final int CATEGORIES_SIZE = 10;
	private List<List<String>> categories = new ArrayList<List<String>>();
	private Scanner sc;
	
	/**Initializes categories by reading them from file.*/
	public TextFileHandler(){
		for(int i = 0;i < this.CATEGORIES_SIZE;i++)// create the new categories
			this.categories.add(new ArrayList<String>());
		this.openCategoryFile("Blue.txt", 	categories.get(0));
		this.openCategoryFile("BTTSWD.txt", 	categories.get(1));
		this.openCategoryFile("EWaste.txt", 	categories.get(2));
		this.openCategoryFile("Green.txt", 	categories.get(3));
		this.openCategoryFile("Grey.txt", 	categories.get(4));
		this.openCategoryFile("HHW.txt", 	categories.get(5));
		this.openCategoryFile("OW.txt", 		categories.get(6));
		this.openCategoryFile("PW.txt", 		categories.get(7));
		this.openCategoryFile("SM.txt", 		categories.get(8));
		this.openCategoryFile("YW.txt", 		categories.get(9));
	}
	
	/**Opens a file and returns contents from it into an array list
	 * @param file file path for the file
	 * @param list list for information to be stored in*/
	public void openCategoryFile(String file,List<String>list){
//		File categoryFile = new File(file);
		try{
			AssetManager manager = getAssets();
			InputStream is = manager.open(file);
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader in = new BufferedReader(isr);
//			sc = new Scanner(new BufferedReader(new FileReader(file)));
			String line = " ";
			while(line != ""){
				line = in.readLine();
			    list.add(line);
			}
		}
		catch(Exception noFile) { 
		    System.out.println(file + " No file found");
		}
	}
	/**Returns the categories which contain the items*/
	public List<List<String>> getCategories(){
		return this.categories;
	}
}
