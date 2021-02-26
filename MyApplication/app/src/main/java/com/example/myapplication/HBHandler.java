package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION= 1;
    private static final String DATABASE_NAME="dataBase";

    public HBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE student(_id INTEGER, ID TEXT PRIMARY KEY, name TEXT, familyName TEXT)";
        db.execSQL(CREATE_TABLE);
    }
    //update
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS student");// Drop older table if existed
        onCreate(db);// Creating tables again
    }
     public void addStudent(Student s){
        SQLiteDatabase db = this.getWritableDatabase();
         ContentValues values = new ContentValues();
         values.put("ID",s.ID);
         values.put("name", s.name);
         values.put("familyName", s.familyName);
         db.insert("student",null,values);
         db.close();
     }






}
