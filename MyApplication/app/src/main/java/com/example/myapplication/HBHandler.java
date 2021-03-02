package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION= 2;
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

     public int  add(Student s){
        SQLiteDatabase db = this.getWritableDatabase();
         ContentValues values = new ContentValues();
         values.put("ID",s.ID);
         values.put("name", s.name);
         values.put("familyName", s.familyName);
         db.insert("student",null,values);
         db.close();
         return 1;
     }

     //search about a student
     public Student getStudent(String ID){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT* FROM student where ID=?",new String[]{ID});
        if(cursor.getCount() == 0){ return null; }
        cursor.moveToFirst();
         return new Student(cursor.getString(1),cursor.getString(2),cursor.getString(3));
     }

    //UPDATE Student
    public int update(Student s){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT* FROM student where ID=?",new String[]{s.ID});

        if(cursor.getCount() == 0){
            return -1;
        }else{
                db.execSQL("update student set name =?, familyName =? where ID=?",new String[]{s.name,s.familyName, s.ID});
                 return 1; }

    }
    public int delete(Student s)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM student where ID=?",new String[]{s.ID});
        if(cursor.getCount()== 0){
            return -1;
        }else{
            db.execSQL("DELETE from student where ID=? and name=? and familyName=?",new String[]{s.ID,s.name,s.familyName});
            return 1;
        }
    }
    public Cursor showStudents()
    {
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM student",null);
        if(cursor.getCount() == 0){
            return null;
        }
       
        return cursor;

    }



}
