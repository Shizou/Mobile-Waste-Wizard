package me.wgma00.mobilewastewizard;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import me.wgma00.mobilewastewizard.backend.WasteDatabaseContract;
import me.wgma00.mobilewastewizard.backend.WasteDatabaseHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WasteDatabaseHelper dbHelper = new WasteDatabaseHelper(getApplicationContext());
        // Get the database. If it does not exist, this is where it will also be created.
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        this.initiateDatabase(db);
    }

    private void initiateDatabase(SQLiteDatabase db){
        String pathInAssetsFolder = "WasteDatabaseEntries.csv";
        ArrayList<String[]>sqlEntries = null;
        try {
            sqlEntries = this.retrieveSqlDatabaseEntries(getApplicationContext().getAssets().open(pathInAssetsFolder));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        for(int i = 0;i < sqlEntries.size();i++) {
            // Create insert entries
            ContentValues values = new ContentValues();
            values.put(WasteDatabaseContract.Table1.COLUMN_NAME_COL1, sqlEntries.get(i)[0]);
            values.put(WasteDatabaseContract.Table1.COLUMN_NAME_COL2, sqlEntries.get(i)[1]);
            values.put(WasteDatabaseContract.Table1.COLUMN_NAME_COL3, sqlEntries.get(i)[2]);
            // Insert the new row, returning the primary key value of the new row
            //long newRowId = db.insert(WasteDatabaseContract.Table1.TABLE_NAME, null, values);
        }
    }

    /**
     * Retrieves information from the .csv file where each row signifies an entry in our SQL database
     * @{link backend.WasteDatabaseHelper}.
     *
     * <p>Note that there is no error checking on the information from the file.
     *
     * @param stream file stream to collect information from
     */
    private ArrayList<String[]> retrieveSqlDatabaseEntries(InputStream stream) throws IOException {
        ArrayList<String[]> ret = new ArrayList<String[]>();
        BufferedReader br = new BufferedReader(new InputStreamReader(stream));
        for(int lineNo = 0; br.ready() ;lineNo++) {
            String line = br.readLine();
            String[]entry = line.split(",");
            // This skips the first entry "TITLE,ALT_WORDS,DESC_ID,DESCRIPTION"
            if(lineNo != 0) {
                ret.add(entry);
            }
        }
        return ret;
    }
}
