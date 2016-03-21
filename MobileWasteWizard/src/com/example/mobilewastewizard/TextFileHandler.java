/** Name: Burhan Qadri, Justin Li, William Granados
 *  Date: 25/03/15
 *  Purpose: Handles the reading in from database
 * */

package com.example.mobilewastewizard;
import java.io.*;
import java.util.*;
import android.content.*;
import android.support.v7.app.ActionBarActivity;

public class TextFileHandler extends ActionBarActivity  {

	private final int CATEGORIES_SIZE = 10;
	private List<List<String>> categories = new ArrayList<List<String>>();
	private List<String>totalList = new ArrayList<String>();

	/**Initializes categories by reading them from file.
	 * @param cxt the context from which this class acquires privileges for the assets folder (in this case main activity)*/
	public TextFileHandler(Context cxt){
		for(int i = 0;i < this.CATEGORIES_SIZE;i++)// creates the new categories
			this.categories.add(new ArrayList<String>());
		this.openCategoryFile(cxt,"categories/Blue.txt", categories.get(0));
		this.openCategoryFile(cxt,"categories/BTTSWD.txt", categories.get(1));
		this.openCategoryFile(cxt,"categories/EWaste.txt", categories.get(2));
		this.openCategoryFile(cxt,"categories/Green.txt", categories.get(3));
		this.openCategoryFile(cxt,"categories/Grey.txt", categories.get(4));
		this.openCategoryFile(cxt,"categories/HHW.txt", categories.get(5));
		this.openCategoryFile(cxt,"categories/OW.txt", categories.get(6));
		this.openCategoryFile(cxt,"categories/PW.txt", categories.get(7));
		this.openCategoryFile(cxt,"categories/SM.txt", categories.get(8));
		this.openCategoryFile(cxt,"categories/YW.txt", categories.get(9));
		Collections.sort(totalList);
	}

	/**Opens a file and returns contents from it into an array list
	 * @param file file path for the file
	 * @param list list for information to be stored in*/
	public void openCategoryFile(Context cxt,String file,List<String>list){
		try {
			InputStream  	  is   = cxt.getAssets().open(file);
			InputStreamReader isr  = new InputStreamReader(is);
			BufferedReader 	  br   = new BufferedReader(isr);
			String 			  line = br.readLine();
			while(line!=null){
				list.add(line);
				totalList.add(line);
				line = br.readLine();
			}
		} catch (IOException e) {
			System.out.println("File not found:" + file);
			e.printStackTrace();
		}
	}
	/**Returns the categories which contain the items*/
	public List<List<String>> getCategories(){
		return this.categories;
	}
	/**Returns the total list which contains all the items*/
	public List<String>getTotalList(){
		return this.totalList;
	}
}
