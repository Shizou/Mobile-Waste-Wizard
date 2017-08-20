package com.example.mobilewastewizard.backend;

import android.provider.BaseColumns;

/**
 * Created by william on 2017-08-20.
 */

public class WasteDatabaseContract {
    public static final  int    DATABASE_VERSION   = 1;
    public static final  String DATABASE_NAME      = "wastedatabase.db";
    private static final String TEXT_TYPE          = " TEXT";
    private static final String COMMA_SEP          = ",";

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private WasteDatabaseContract() {}

    public static abstract class Table1 implements BaseColumns {
        public static final String TABLE_NAME       = "Waste";
        public static final String COLUMN_NAME_COL1 = "title";
        public static final String COLUMN_NAME_COL2 = "alt_words";
        public static final String COLUMN_NAME_COL3 = "desc_id";
        public static final String COLUMN_NAME_COL4 = "description";


        public static final String CREATE_TABLE = "CREATE TABLE " +
                TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY," +
                COLUMN_NAME_COL1 + TEXT_TYPE + COMMA_SEP +
                COLUMN_NAME_COL2 + TEXT_TYPE + COMMA_SEP +
                COLUMN_NAME_COL3 + TEXT_TYPE + COMMA_SEP +
                COLUMN_NAME_COL4 + TEXT_TYPE + " )";
        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
