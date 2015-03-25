/** Name: Burhan Qadri, Justin Li, William Granados
 *  Date: 25/03/15
 *  Purpose: user interface
 * */
package com.example.mobilewastewizard;


import java.io.File;
import java.io.IOException;
import java.util.*;

import com.cloudinary.Cloudinary;

import android.speech.RecognizerIntent;
import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.*;
import android.widget.*;

public class MainActivity extends ActionBarActivity {
	private static final int REQ_CAPTURE_IMAGE = 0, REQ_VOICE = 1;
    private String imagePath = "";
    private Database database;
    
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.database = new Database(this);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void getQuery(View view){
		EditText searchQuery = (EditText)findViewById(R.id.search);
//		Context context = getApplicationContext();
		CharSequence text = searchQuery.getText();
//		int duration = Toast.LENGTH_SHORT;
//		Toast toast = Toast.makeText(context, text, duration);
	
		// handles queries
		String query = text.toString();
		
		showResult(query);
		
/*		String bin = database.initialQuery(query);
		if(bin!=null){// query matches a value in the database
			toast = Toast.makeText(context, bin, duration);
		}
		else{// attempt to give suggestions
			List<String>suggestions = database.secondaryQuery(query); 	// checks if parts of the query partially match values in the database
		//	if(suggestions==null)// query does not resemble anything in the database
			//	suggestions = database.tertiaryQuery(query);
			
			if(suggestions == null){
				bin = "no bin";
				toast = Toast.makeText(context, bin, duration);
			}
			else{
				bin = "";
				for(int i = 0;i < suggestions.size();i++){
					bin+= "Did you mean:" + suggestions.get(i)+"?\n";
				}
				toast = Toast.makeText(context, bin, duration);
			}
		}
		toast.show();	*/
	}
	
	public void showResult(String query){
		int duration = Toast.LENGTH_SHORT;
		Context context = getApplicationContext();
		Toast toast;
		
		String bin = database.initialQuery(query);
		if(bin!=null){// query matches a value in the database
			toast = Toast.makeText(context, bin, duration);
		}
		else{// attempt to give suggestions
			List<String>suggestions = database.secondaryQuery(query); 	// finds the most relvant matches in the database	
			if(suggestions == null){
				bin = "no bin";
				toast = Toast.makeText(context, bin, duration);
			}
			else{
				bin = "";
				for(int i = 0;i < suggestions.size();i++){
					bin+= "Did you mean:" + suggestions.get(i)+"?\n";
				}
				toast = Toast.makeText(context, bin, duration);
			}
		}
		toast.show();
	}
	
	public void camera(View view) throws IOException{
		String imageDirectoryStr = Environment.getExternalStorageDirectory() + File.separator + "temp";
        File imageDirectory = new File(imageDirectoryStr);

        if (!(imageDirectory.exists())) {
            imageDirectory.mkdir();
        }

        imagePath = imageDirectoryStr + File.separator + "temp.jpg";   

        File file = new File(imagePath);   
        Uri outputFileUri = Uri.fromFile(file);

        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.PNG.toString());
        startActivityForResult(intent, REQ_CAPTURE_IMAGE);
	}
	
	public void voice(View view){
		Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");
       	 try {
            startActivityForResult(i, REQ_VOICE);
        } catch (Exception e) {
       	 	Toast.makeText(this, "Error initializing speech to text engine.", Toast.LENGTH_LONG).show();
        }
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		//handles camera results
	    if (requestCode == REQ_CAPTURE_IMAGE && resultCode == RESULT_OK) {
	    	try {
	    		Map<String, String> config = new HashMap<String, String>();
	    		config.put("cloud_name", "serene17");
	    		config.put("api_key", "712618266454968");
	    		config.put("api_secret", "5MmY0HE4maNqLmdugBNFtTo6yBo");
	    		Cloudinary cloudinary = new Cloudinary(config);
	    		
	    		File image = new File(imagePath);
	    		
	    		cloudinary.uploader().upload(image, Cloudinary.emptyMap());
			} catch (IOException e) {}
	    }
	    
	    //handles voice results
	    if(requestCode == REQ_VOICE && resultCode == RESULT_OK){
	    	ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
	    	String query = result.get(0);
	    	showResult(query);
	    }
	    
	}
}
