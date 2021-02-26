package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
     public Student getStudent(String ID){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT* FROM student where ID=?",new String[]{ID});
        if(cursor.getCount() == 0){ return null; }
        cursor.moveToFirst();
         return new Student(cursor.getString(0),cursor.getString(1),cursor.getString(2));
     }

    //UPDATE Student
    public int updateStudent(String ID, String name, String familyName){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT* FROM student where ID=?",new String[]{ID});

        if(cursor.getCount() == 0){ return -1;
        }else{ db.execSQL("update student set name =?, familyName =? where ID=?",new String[]{name,familyName, ID});
                 return 1; }

    }

    //DELETE Student
     public int deleteStudent(String ID, String name, String familyName)
     {
         SQLiteDatabase db = this.getWritableDatabase();
         Cursor cursor = db.rawQuery("SELECT* FROM student where ID=?", new String[]{ID});
         if(cursor.getCount() == 0) {
             return -1;
         }else{
             db.execSQL("DELETE from student where ID=? and name=? and familyName=?", new String[]{ID,name, familyName});
             return 1;
         }

     }






}
