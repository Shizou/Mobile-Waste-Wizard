package com.example.mobilewastewizard;

import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;

public class DatabaseActivity extends ActionBarActivity {
	private Database database;
	public final static String EXTRA_ITEM = "com.example.mobilewastewizard.ITEM";

	/**
	 * Initializes the database into a list and listens for an item selection.
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_database);
		database = new Database(this);
		
		//fills list with total database
		List<String> autoCompleteList = database.getTotalList();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, autoCompleteList);
		ListView listView = (ListView) findViewById(R.id.listView);
		listView.setAdapter(adapter);

		//listens for a selection
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				System.out.println(parent.getItemAtPosition(position));
				sendItem(parent.getItemAtPosition(position));
			}                 
		});
	}
	
	/**
	 * Sends selection back to the main activity as a query.
	 * @param item The selected item.
	 */
	private void sendItem(Object item){
		Intent intent = new Intent(this, MainActivity.class);
		intent.putExtra(EXTRA_ITEM, item.toString());
		startActivity(intent);
	}
}
