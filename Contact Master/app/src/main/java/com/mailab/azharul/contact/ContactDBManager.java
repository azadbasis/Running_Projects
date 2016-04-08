package com.mailab.azharul.contact;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Azharul on 13-Mar-16.
 */
public class ContactDBManager {

    private ContactDatabaseConfig databaseConfig ;
    private ContactModel contactModel;
    private SQLiteDatabase sqLiteDatabase;

    // Configure DB
    public ContactDBManager(Context context) {
        databaseConfig = new ContactDatabaseConfig(context);
    }

    // Open DB
    private void openDB(){
        sqLiteDatabase = databaseConfig.getWritableDatabase();
    }

    // Close DB
    private void closeDB(){
        databaseConfig.close();
    }

    public boolean addContact(ContactModel contact){

        this.openDB();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ContactDatabaseConfig.COL_NAME, contact.getName());
        contentValues.put(ContactDatabaseConfig.COL_PHONE, contact.getPhoneNO());

        long insert = sqLiteDatabase.insert(ContactDatabaseConfig.TABLE_CONTACT_INFO,null,contentValues);

        this.closeDB();

        if(insert>0){
            return true;
        }else return false;
    }

    public ContactModel getContact(int id){

        this.openDB();

        Cursor cursor = sqLiteDatabase.query(ContactDatabaseConfig.TABLE_CONTACT_INFO,
                new String[]{ContactDatabaseConfig.COL_ID,ContactDatabaseConfig.COL_NAME,ContactDatabaseConfig.COL_PHONE},
                ContactDatabaseConfig.COL_ID + " = " + id,null,null,null,null);

        cursor.moveToFirst();

        int mID = cursor.getInt(cursor.getColumnIndex(ContactDatabaseConfig.COL_ID));
        String name = cursor.getString(cursor.getColumnIndex(ContactDatabaseConfig.COL_NAME));
        String phone = cursor.getString(cursor.getColumnIndex(ContactDatabaseConfig.COL_PHONE));

        contactModel = new ContactModel(name,phone,mID);
        this.closeDB();
        return contactModel;
    }

    public ArrayList<ContactModel> getAllContact(){

        this.openDB();
        ArrayList<ContactModel> contact = new ArrayList<>();

        Cursor cursor = sqLiteDatabase.query(ContactDatabaseConfig.TABLE_CONTACT_INFO, null, null, null, null, null, null);

        cursor.moveToFirst();

        if(cursor != null && cursor.getCount()>0){

            for (int i=0;i<cursor.getCount();i++) {
                int mID = cursor.getInt(cursor.getColumnIndex(ContactDatabaseConfig.COL_ID));
                String name = cursor.getString(cursor.getColumnIndex(ContactDatabaseConfig.COL_NAME));
                String phone = cursor.getString(cursor.getColumnIndex(ContactDatabaseConfig.COL_PHONE));

                contactModel = new ContactModel(name, phone, mID);
                contact.add(contactModel);
                cursor.moveToNext();
            }
        }
        this.closeDB();
        return contact;
    }

    public boolean deleteContact(int id){
        this.openDB();
        int delete = sqLiteDatabase.delete(ContactDatabaseConfig.TABLE_CONTACT_INFO,ContactDatabaseConfig.COL_ID+" = "+id,null);
        this.closeDB();
        if(delete>0){
            return true;
        }else return false;
    }

    public boolean updateContact(int id,ContactModel contact){

        this.openDB();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ContactDatabaseConfig.COL_NAME,contact.getName());
        contentValues.put(ContactDatabaseConfig.COL_PHONE, contact.getPhoneNO());

        int update = sqLiteDatabase.update(ContactDatabaseConfig.TABLE_CONTACT_INFO,contentValues,ContactDatabaseConfig.COL_ID+" = "+id,null);

        this.closeDB();
        if (update>0){
            return true;
        }else return false;
    }

}
