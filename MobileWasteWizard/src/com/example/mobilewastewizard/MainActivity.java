package com.example.mobilewastewizard;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

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
	private static final int REQ_CAPTURE_IMAGE = 0;
    String imagePath;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
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
		
		Context context = getApplicationContext();
		CharSequence text = searchQuery.getText();
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(context, text, duration);
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
	
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (requestCode == REQ_CAPTURE_IMAGE && resultCode == RESULT_OK) {
	    	try {
				cloudinaryUpload();
			} catch (IOException e) {}
	    }
	    
	}
	
	public void cloudinaryUpload() throws IOException{
		Map config = new HashMap();
		config.put("cloud_name", "serene17");
		config.put("api_key", "712618266454968");
		config.put("api_secret", "5MmY0HE4maNqLmdugBNFtTo6yBo");
		Cloudinary cloudinary = new Cloudinary(config);
		
		FileInputStream inputStream = null;
		cloudinary.uploader().upload(inputStream, ObjectUtils.emptyMap());
	}
}
