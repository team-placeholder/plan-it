package com.data.SqlData;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Antoan on 2/18/2017.
 */

public class StoreDbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 7;
    static final String DATABASE_NAME = "my_users.db";
    public SQLiteDatabase sqLiteDatabase;

    public StoreDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_EMAILS_TABLE =
                "CREATE TABLE IF NOT EXISTS " + UserContract.EmailEntry.TABLE_NAME + " (" +
                        UserContract.EmailEntry._ID + " INTEGER PRIMARY KEY, " +
                        UserContract.EmailEntry.COLUMN_EMAIL + " TEXT UNIQUE NOT NULL " +
                        ");";





        sqLiteDatabase.execSQL(SQL_CREATE_EMAILS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        final String SQL_CREATE_USERS_TABLE =
                "CREATE TABLE IF NOT EXISTS " + UserContract.ResUserEntry.TABLE_NAME + " (" +
                        UserContract.ResUserEntry._ID + " INTEGER PRIMARY KEY, " +
                        UserContract.ResUserEntry.COLUMN_EMAIL + " TEXT UNIQUE NOT NULL, " +
                        UserContract.ResUserEntry.COLUMN_USERNAME + " TEXT UNIQUE NOT NULL, " +
                        UserContract.ResUserEntry.COLUMN_PASSWORD + " TEXT NOT NULL " +
                        ");";
        db.execSQL(SQL_CREATE_USERS_TABLE);
    }

    public void openForWriting() {
        sqLiteDatabase = getWritableDatabase();
    }

    public void openForReading() {
        sqLiteDatabase = getReadableDatabase();
    }

    @Override
    public synchronized void close() {
        super.close();
    }
}
