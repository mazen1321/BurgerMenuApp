package com.example.navigationbarapp;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "user_db";
    private static final int DATABASE_VERSION = 1;

    // Table name
    private static final String TABLE_USERS = "users";

    // Common column names
    private static final String KEY_ID = "id";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_FIRST_NAME = "first_name";
    private static final String KEY_LAST_NAME = "last_name";

    // Table creation query
    private static final String CREATE_TABLE_USERS =
            "CREATE TABLE " + TABLE_USERS + "("
                    + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + KEY_FIRST_NAME + " TEXT,"
                    + KEY_LAST_NAME + " TEXT,"
                    + KEY_USERNAME + " TEXT UNIQUE,"
                    + KEY_PASSWORD + " TEXT"
                    + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create tables
        db.execSQL(CREATE_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older tables if they exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);

        // Create tables again
        onCreate(db);
    }

    // Add a new user to the database
    public long addUser(String firstName, String lastName, String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_FIRST_NAME, firstName);
        values.put(KEY_LAST_NAME, lastName);
        values.put(KEY_USERNAME, username);
        values.put(KEY_PASSWORD, password);

        // Insert row
        long userId = db.insert(TABLE_USERS, null, values);

        // Close the database connection
        db.close();

        return userId;
    }

    // Check if a user with the given username and password exists
    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {KEY_ID};

        // Filter results WHERE "username" = 'username' AND "password" = 'password'
        String selection = KEY_USERNAME + " = ? AND " + KEY_PASSWORD + " = ?";
        String[] selectionArgs = {username, password};

        // How you want the results sorted in the resulting Cursor
        String sortOrder = null; // Use default sorting order

        Cursor cursor = db.query(
                TABLE_USERS,      // The table to query
                projection,       // The array of columns to return (pass null to get all)
                selection,        // The columns for the WHERE clause
                selectionArgs,    // The values for the WHERE clause
                null,             // don't group the rows
                null,             // don't filter by row groups
                sortOrder         // The sort order
        );

        // Check if a user with the given username and password exists
        boolean userExists = cursor.moveToFirst();

        // Close the database connection
        cursor.close();
        db.close();

        return userExists;
    }
}




