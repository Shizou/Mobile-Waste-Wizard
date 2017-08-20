package me.wgma00.mobilewastewizard.backend;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class WasteDatabaseHelper extends SQLiteOpenHelper {
    public WasteDatabaseHelper(Context context) {
        super(context, WasteDatabaseContract.DATABASE_NAME, null, WasteDatabaseContract.DATABASE_VERSION);
    }

    // Method is called during creation of the database
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(WasteDatabaseContract.Table1.CREATE_TABLE);
    }

    // Method is called during an upgrade of the database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(WasteDatabaseContract.Table1.DELETE_TABLE);
        onCreate(db);
    }
}