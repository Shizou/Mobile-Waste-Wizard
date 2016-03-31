/** Name: Burhan Qadri, Justin Li, William Granados
 *  Date: 25/03/15
 *  Purpose: database activity
 * */

package com.example.mobilewastewizard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AlphabetIndexer;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.SectionIndexer;

public class DatabaseActivity extends ActionBarActivity {
	private Database database;
	public final static String EXTRA_ITEM =
    "com.example.mobilewastewizard.ITEM";

	/**
	 * Initializes the database into a list and listens for an item selection.
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_database);
		database = new Database(this);

		//fills list with total database
		List<String> autoCompleteList = database.getTotalList();
		ListView listView = (ListView) findViewById(R.id.listView);
		listView.setFastScrollEnabled(true);
		IndexerAdapter<String> adapter = new IndexerAdapter<String>(
						 getApplicationContext(),
						 android.R.layout.simple_list_item_1,
						 database.getTotalList());
		listView.setAdapter(adapter);
		//listens for a selection
		listView.setOnItemClickListener(new OnItemClickListener() {
		public void onItemClick(AdapterView<?> parent, View view, int position,
        long id) {
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

class IndexerAdapter<T> extends ArrayAdapter<T> implements SectionIndexer {

    ArrayList<String> elements;
    HashMap<String, Integer> alphaIndexer;

    String[] sections;

    public IndexerAdapter(Context context, int textViewResourceId,
                    List<T> objects) {
            super(context, textViewResourceId, objects);

            elements = (ArrayList<String>) objects;
            alphaIndexer = new HashMap<String, Integer>();
            int size = elements.size();
            for (int i = size - 1; i >= 0; i--) {
                    String element = elements.get(i);
                    alphaIndexer.put(element.substring(0, 1), i);
            }
            Set<String> keys = alphaIndexer.keySet();
            Iterator<String> it = keys.iterator();
            ArrayList<String> keyList = new ArrayList<String>();
            while (it.hasNext()) {
                    String key = it.next();
                    keyList.add(key);
            }
            Collections.sort(keyList);
            sections = new String[keyList.size()];
            keyList.toArray(sections);
    }

    @Override
    public int getPositionForSection(int section) {
            String letter = sections[section];
            return alphaIndexer.get(letter);
    }

    @Override
    public int getSectionForPosition(int position) {
            return 0;
    }

    @Override
    public Object[] getSections() {
            return sections;
    }
}
