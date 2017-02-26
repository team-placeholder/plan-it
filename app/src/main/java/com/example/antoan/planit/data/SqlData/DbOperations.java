package com.example.antoan.planit.data.SqlData;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

/**
 * Created by Antoan on 2/18/2017.
 */

public class DbOperations extends StoreDbHelper {

    public DbOperations(Context context) {
        super(context);
    }

    public void AddEmail(String email) {
        openForWriting();
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserContract.EmailEntry.COLUMN_EMAIL, email);

        sqLiteDatabase.insert(UserContract.EmailEntry.TABLE_NAME, null, contentValues);
    }

    public Cursor getEmailsContent() {
        openForReading();
        String[] projection = {
                UserContract.EmailEntry.COLUMN_EMAIL
        };
        String sortOrder =  UserContract.EmailEntry.COLUMN_EMAIL + " ASC";
        return this.sqLiteDatabase.query(UserContract.EmailEntry.TABLE_NAME, projection, null, null, null, null, sortOrder);
    }

    public void addCurUser(String email, String username, String password){
        openForWriting();
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserContract.ResUserEntry.COLUMN_EMAIL,email);
        contentValues.put(UserContract.ResUserEntry.COLUMN_USERNAME,username);
        contentValues.put(UserContract.ResUserEntry.COLUMN_PASSWORD,password);

        sqLiteDatabase.insert(UserContract.ResUserEntry.TABLE_NAME,null,contentValues);
    }

    public void clearUsers(){
        openForWriting();
        sqLiteDatabase.execSQL("DELETE  FROM "+UserContract.ResUserEntry.TABLE_NAME);
    }

    public Cursor getCurrentUser(){
        openForReading();
        String[] projection = {
                UserContract.ResUserEntry.COLUMN_EMAIL,
                UserContract.ResUserEntry.COLUMN_USERNAME,
        };
        return this.sqLiteDatabase.query(UserContract.ResUserEntry.TABLE_NAME,projection,null,null,null,null,null);

    }
}
