package com.s23010658.doit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "DOIT.db";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 🔐 Table for Login (MainActivity)
        db.execSQL("CREATE TABLE login(username TEXT PRIMARY KEY, password TEXT)");
        db.execSQL("INSERT INTO login(username, password) VALUES(username, password)");

        // 📝 Table for User Registration (RegisterActivity)
        db.execSQL("CREATE TABLE users(name TEXT PRIMARY KEY, email TEXT, location TEXT, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS login");
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }

    // 🔐 Check Login Credentials from login table
    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM login WHERE username = ? AND password = ?",
                new String[]{username, password}
        );
        boolean found = cursor.getCount() > 0;
        cursor.close();
        return found;
    }

    // 📝 Insert Registered User into users table
    public boolean insertUser(String name, String email, String location, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Check if user already exists
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE name = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            cursor.close();
            return false;
        }

        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("email", email);
        values.put("location", location);
        values.put("password", password);

        long result = db.insert("users", null, values);
        return result != -1;
    }

    public boolean insertLogin(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Avoid duplicate usernames
        Cursor cursor = db.rawQuery("SELECT * FROM login WHERE username = ?", new String[]{username});
        if (cursor.getCount() > 0) {
            cursor.close();
            return false; // already exists
        }

        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("password", password);

        long result = db.insert("login", null, values);
        return result != -1;
    }


}
