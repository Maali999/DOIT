package com.s23010658.doit;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "doit.db";
    private static final int DATABASE_VERSION = 4; // Incremented for notifications

    // ================= Users Table =================
    public static final String TABLE_USERS = "users";
    public static final String COL_ID = "id";
    public static final String COL_NAME = "name";
    public static final String COL_PASSWORD = "password";
    public static final String COL_EMAIL = "email";
    public static final String COL_LOCATION = "location";
    public static final String COL_UNIVERSITY = "university";
    public static final String COL_FACULTY = "faculty";
    public static final String COL_DEGREE = "degree";
    public static final String COL_SKILLS = "skills";

    // ================= Assignments Table =================
    public static final String TABLE_ASSIGNMENTS = "assignments";
    public static final String COL_ASSIGN_ID = "id";
    public static final String COL_SUBJECT = "subject";
    public static final String COL_DESCRIPTION = "description";
    public static final String COL_AMOUNT = "amount";
    public static final String COL_DEADLINE = "deadline";
    public static final String COL_PDFURI = "pdfUri";

    // ================= Notifications Table =================
    public static final String TABLE_NOTIFICATIONS = "notifications";
    public static final String COL_NOTIFICATION_ID = "id";
    public static final String COL_MESSAGE = "message";
    public static final String COL_TIMESTAMP = "timestamp";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Users table
        String CREATE_USERS = "CREATE TABLE IF NOT EXISTS " + TABLE_USERS + "("
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_NAME + " TEXT, "
                + COL_PASSWORD + " TEXT, "
                + COL_EMAIL + " TEXT, "
                + COL_LOCATION + " TEXT, "
                + COL_UNIVERSITY + " TEXT, "
                + COL_FACULTY + " TEXT, "
                + COL_DEGREE + " TEXT, "
                + COL_SKILLS + " TEXT)";
        db.execSQL(CREATE_USERS);

        // Assignments table
        String CREATE_ASSIGNMENTS = "CREATE TABLE IF NOT EXISTS " + TABLE_ASSIGNMENTS + "("
                + COL_ASSIGN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_SUBJECT + " TEXT, "
                + COL_DESCRIPTION + " TEXT, "
                + COL_AMOUNT + " TEXT, "
                + COL_DEADLINE + " TEXT, "
                + COL_PDFURI + " TEXT)";
        db.execSQL(CREATE_ASSIGNMENTS);

        // Notifications table
        String CREATE_NOTIFICATIONS = "CREATE TABLE IF NOT EXISTS " + TABLE_NOTIFICATIONS + "("
                + COL_NOTIFICATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_MESSAGE + " TEXT, "
                + COL_TIMESTAMP + " TEXT)";
        db.execSQL(CREATE_NOTIFICATIONS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("DB_UPGRADE", "Upgrading database from version " + oldVersion + " to " + newVersion);

        if(oldVersion < 2){
            db.execSQL("ALTER TABLE " + TABLE_USERS + " ADD COLUMN " + COL_UNIVERSITY + " TEXT");
            db.execSQL("ALTER TABLE " + TABLE_USERS + " ADD COLUMN " + COL_FACULTY + " TEXT");
            db.execSQL("ALTER TABLE " + TABLE_USERS + " ADD COLUMN " + COL_DEGREE + " TEXT");
            db.execSQL("ALTER TABLE " + TABLE_USERS + " ADD COLUMN " + COL_SKILLS + " TEXT");
        }
        if(oldVersion < 3){
            String CREATE_ASSIGNMENTS = "CREATE TABLE IF NOT EXISTS " + TABLE_ASSIGNMENTS + "("
                    + COL_ASSIGN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COL_SUBJECT + " TEXT, "
                    + COL_DESCRIPTION + " TEXT, "
                    + COL_AMOUNT + " TEXT, "
                    + COL_DEADLINE + " TEXT, "
                    + COL_PDFURI + " TEXT)";
            db.execSQL(CREATE_ASSIGNMENTS);
        }
        if(oldVersion < 4){
            String CREATE_NOTIFICATIONS = "CREATE TABLE IF NOT EXISTS " + TABLE_NOTIFICATIONS + "("
                    + COL_NOTIFICATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COL_MESSAGE + " TEXT, "
                    + COL_TIMESTAMP + " TEXT)";
            db.execSQL(CREATE_NOTIFICATIONS);
        }
    }

    // ================= Users Methods =================
    public boolean insertUser(String name, String email, String location, String password) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COL_NAME, name);
            values.put(COL_EMAIL, email);
            values.put(COL_LOCATION, location);
            values.put(COL_PASSWORD, password);
            long result = db.insert(TABLE_USERS, null, values);
            db.close();
            return result != -1;
        } catch (Exception e) {
            Log.e("DB_EXCEPTION", "Error inserting user: " + e.getMessage());
            return false;
        }
    }

    public boolean checkUser(String name, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_USERS + " WHERE " + COL_NAME + "=? AND " + COL_PASSWORD + "=?",
                new String[]{name, password});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return exists;
    }

    public boolean updateUserDetails(String name, String password, String university, String faculty, String degree, String skills) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_UNIVERSITY, university);
        values.put(COL_FACULTY, faculty);
        values.put(COL_DEGREE, degree);
        values.put(COL_SKILLS, skills);
        int rows = db.update(TABLE_USERS, values, COL_NAME + "=? AND " + COL_PASSWORD + "=?", new String[]{name, password});
        db.close();
        return rows > 0;
    }

    // ===== Profile Screen Methods =====
    // Fetch user details by username & password
    public Cursor getUserDetails(String name, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(
                "SELECT * FROM " + TABLE_USERS + " WHERE " + COL_NAME + "=? AND " + COL_PASSWORD + "=?",
                new String[]{name, password});
    }

    // Update username
    public boolean updateUserName(String oldName, String password, String newName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NAME, newName);
        int rows = db.update(TABLE_USERS, values, COL_NAME + "=? AND " + COL_PASSWORD + "=?", new String[]{oldName, password});
        db.close();
        return rows > 0;
    }

    // ================= Assignments Methods =================
    public boolean insertAssignment(String subject, String description, String amount, String deadline, String pdfUri) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COL_SUBJECT, subject);
            values.put(COL_DESCRIPTION, description);
            values.put(COL_AMOUNT, amount);
            values.put(COL_DEADLINE, deadline);
            values.put(COL_PDFURI, pdfUri);
            long result = db.insert(TABLE_ASSIGNMENTS, null, values);
            db.close();
            return result != -1;
        } catch (Exception e) {
            Log.e("DB_EXCEPTION", "Error inserting assignment: " + e.getMessage());
            return false;
        }
    }

    public Cursor getAllAssignments() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_ASSIGNMENTS + " ORDER BY " + COL_ASSIGN_ID + " ASC", null);
    }

    // ================= Notifications Methods =================
    public boolean insertNotification(String message) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COL_MESSAGE, message);
            values.put(COL_TIMESTAMP, String.valueOf(System.currentTimeMillis()));
            long result = db.insert(TABLE_NOTIFICATIONS, null, values);
            db.close();
            return result != -1;
        } catch (Exception e) {
            Log.e("DB_EXCEPTION", "Error inserting notification: " + e.getMessage());
            return false;
        }
    }

    public Cursor getAllNotifications() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NOTIFICATIONS + " ORDER BY " + COL_NOTIFICATION_ID + " DESC", null);
    }
}
