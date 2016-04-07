package com.mailab.azharul.contact;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Azharul on 13-Mar-16.
 */
public class ContactDatabaseConfig extends SQLiteOpenHelper{

    static final int DB_VERSION = 1;
    static final String DB_NAME = "contact_DB";
    static final String TABLE_CONTACT_INFO = "contact_info";

    static final String COL_ID = "id";
    static final String COL_NAME = "name";
    static final String COL_PHONE = "phone";

    String CREATE_TABLE = " CREATE TABLE " + TABLE_CONTACT_INFO +
            " ( " + COL_ID + " INTEGER PRIMARY KEY," + COL_NAME + " TEXT," + COL_PHONE + " TEXT )";

    public ContactDatabaseConfig(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
