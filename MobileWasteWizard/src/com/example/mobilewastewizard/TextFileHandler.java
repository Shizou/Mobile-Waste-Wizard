/** Name: Burhan Qadri, Justin Li, William Granados
 *  Date: 30/03/16
 *  Purpose: Handles reading in information to the database
 * */

package com.example.mobilewastewizard;
import java.io.*;
import java.util.*;
import android.content.*;
import android.support.v7.app.ActionBarActivity;

public class TextFileHandler extends ActionBarActivity  
{
	
	private final int CATEGORIES_SIZE = 10;
	private List<List<String>> categories = new ArrayList<List<String>>();
	private List<String>totalList = new ArrayList<String>();
	
	/** Initializes categories then reads them from the file
	 * @param cxt the context from which this class acquires privileges 
	 * for the assets folder (in this case main activity)
	 * */
	public TextFileHandler(Context cxt)
	{
		// Initialize the categories
		for(int i = 0;i < this.CATEGORIES_SIZE;i++)
		{
			this.categories.add(new ArrayList<String>());
		}
	    // Now populate each categories with the terms available in the text
		// files. Just for reference, here are what each terminology each index
		// represents
		
		// 0) Blue Bin
		// 1) Bring to Transfer Station or Waste Depot
		// 2) E-Waste
		// 3) Green Bin
		// 4) Grey Bin - Garbage
		// 5) Household Hazardous Waste
		// 6) Over-sized Waste
		// 7) Prohibited Waste
		// 8) Scrap Metal
		// 9) Yard Waste
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
		// in the OpenCategoryFile, all of the terms are also added to a total
		// list so we are simply sorting the entries for further usage
		Collections.sort(totalList);
	}

	/**Opens a file and returns contents from it into an array list
	 * @param file file path for the file
	 * @param list list for information to be stored in
	 * */
	public void openCategoryFile(Context cxt, String file,List<String>list)
	{
		// Lets try and open the the text file that was handed to us
		// then we'll append each term to the respective list object, and
		// to a global list object
		try 
		{
			InputStream is = cxt.getAssets().open(file);
			InputStreamReader isr  = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line = br.readLine();
		    // Read to EOF	
			while(line != null)
			{
				list.add(line);
				totalList.add(line);
				line = br.readLine();
			}
		} 
	    // Something went terribly! Likely just that the file wasn't there	
		catch (IOException e) 
		{
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
