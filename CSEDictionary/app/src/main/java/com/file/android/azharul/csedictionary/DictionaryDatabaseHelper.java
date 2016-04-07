package com.file.android.azharul.csedictionary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Azharul on 3/31/2016.
 */
public class DictionaryDatabaseHelper extends SQLiteOpenHelper {


    static final String DATABASE_NAME = "dictionary";
    static final String ITEM_ID_COLUMN = "id";
    static final String WORD_COLUMN = "word";
    static final String DEFINITION_COLUMN = "definition";

    final static String CREATE_DATABSE_QUERY="CREATE TABLE "+DATABASE_NAME+" ( "+
            ITEM_ID_COLUMN+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            WORD_COLUMN+" TEXT , "+
            DEFINITION_COLUMN+" TEXT)";


    static final String ON_UPGRADE_QUERY = "DROP TABLE" + DATABASE_NAME;
    Context context;


    public DictionaryDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_DATABSE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(ON_UPGRADE_QUERY);
        onCreate(db);
    }

    //We use this method insert single data
    public long insertData(WordDefinition wordDefinition) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(WORD_COLUMN, wordDefinition.word);
        contentValues.put(DEFINITION_COLUMN, wordDefinition.definition);
        return sqLiteDatabase.insert(DATABASE_NAME, null, contentValues);
    }

    public int updateData(WordDefinition wordDefinition) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(WORD_COLUMN, wordDefinition.word);
        contentValues.put(DEFINITION_COLUMN, wordDefinition.definition);
        return sqLiteDatabase.update(DATABASE_NAME, contentValues, ITEM_ID_COLUMN + "=?", new String[]{wordDefinition.word});
    }

    public void deleteData(WordDefinition wordDefinition) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String queryString = "DELETE FROM " + DATABASE_NAME + " WHERE " + WORD_COLUMN + "='" + wordDefinition.word + "'";


        sqLiteDatabase.execSQL(queryString);
    }

    public ArrayList<WordDefinition> getAllWords() {
        ArrayList<WordDefinition> arrayList = new ArrayList<WordDefinition>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String selectAllQueryString="SELECT * FROM "+DATABASE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(selectAllQueryString, null);

        if (cursor.moveToFirst()) {
            do {

                WordDefinition wordDefinition = new WordDefinition(cursor.getString(cursor.getColumnIndex(WORD_COLUMN)), cursor.getString(cursor.getColumnIndex(DEFINITION_COLUMN)));
                arrayList.add(wordDefinition);
            } while (cursor.moveToNext());

        }
        return arrayList;
    }

    //FOR SINGLE WORD SELECT WE USE IF...MULTIPLE WORDS DO-WHILE COULD USE
    public WordDefinition getWordDefinition(String word) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        WordDefinition wordDefinition = null;
        String selectQueryString="SELECT * FROM "+DATABASE_NAME+ " WHERE "+WORD_COLUMN+" = '"+word+ "'";
        Cursor cursor = sqLiteDatabase.rawQuery(selectQueryString, null);
        if (cursor.moveToFirst()) {
            wordDefinition = new WordDefinition(cursor.getString(cursor.getColumnIndex(WORD_COLUMN)), cursor.getString(cursor.getColumnIndex(DEFINITION_COLUMN)));

        }
        return wordDefinition;
    }


    public WordDefinition getWordDefinition(long id) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        WordDefinition wordDefinition = null;
        String selectQueryString = "SELECT * FROM " + ITEM_ID_COLUMN + " WHERE " + "= '" + id + "'";
        Cursor cursor = sqLiteDatabase.rawQuery(selectQueryString, null);
        if (cursor.moveToFirst()) {
            wordDefinition = new WordDefinition(cursor.getString(cursor.getColumnIndex(WORD_COLUMN)), cursor.getString(cursor.getColumnIndex(DEFINITION_COLUMN)));

        }
        return wordDefinition;
    }

    //This Method use for insert data from textFile/we use this method to insert whole data

    public void initializeDatabaseFromTheFirstTime(ArrayList<WordDefinition> wordDefinitions) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL("BEGIN");
        ContentValues contentValues = new ContentValues();
        for (WordDefinition wordDefinition : wordDefinitions
                ) {
            contentValues.put(WORD_COLUMN, wordDefinition.word);
            contentValues.put(DEFINITION_COLUMN, wordDefinition.definition);
            sqLiteDatabase.insert(DATABASE_NAME, null, contentValues);

        }

        sqLiteDatabase.execSQL("COMMIT");
    }
}

