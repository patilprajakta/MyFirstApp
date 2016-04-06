package com.example.synerzip.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

//userdbhelper will be called when we create its obj

public class UserDBHelper extends SQLiteOpenHelper
{

    public static final String DATABASE_NAME = "StudentDB";
    public static final String STUDENT_TABLE = "student";
    public static final String STUDENT_ROLLNO= "rollno";
    public static final String STUDENT_NAME = "name";
    public static final String STUDENT_MARKS = "marks";
    private static final int DATABASE_VERSION = 1;
String msg;
    String CREATE_STUDENT_TABLE = "CREATE TABLE " + STUDENT_TABLE + "("+ STUDENT_ROLLNO + " STRING PRIMARY KEY ," + STUDENT_NAME + " TEXT," + STUDENT_MARKS + " TEXT" + ")";//need space b4 TEXT

    public UserDBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.e("DATABASE_OPERATIONS", "Database created/opened...");
    }
  //  We create our database tables here, it’s called when database is created.
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_STUDENT_TABLE);
                Log.e("DATABASE_OPERATIONS", "Table Created...");
    }
    // called when database version is upgraded to a new version or on changing table structure, adding constraints, etc.
    //this upgrade is called when u do changes in ur app like database version then this method will check whether this or that; it'll check
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS students");
        onCreate(db);
    }
    public void addInfo (String rollno, String name, String marks)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("rollno", rollno);
        contentValues.put("name",name);
        contentValues.put("marks", marks);
        db.insert("student", null, contentValues);
        Log.d(msg,"ADDED");
        //db.close();

    }

    public Cursor viewInfo()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.query(STUDENT_TABLE, new String[] {STUDENT_ROLLNO,STUDENT_NAME, STUDENT_MARKS},null, null,null, null, null);
        if (res != null) {
            res.moveToFirst();
        }
        return res;
    }


    public Integer deleteInfo (String rollno)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("student", "rollno = ? ", new String[] {rollno});
    }

    public void updateInfo(String rollno, String name, String marks)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("rollno", rollno);
        contentValues.put("name", name);
        contentValues.put("marks", marks);
        db.update("student", contentValues, "rollno = ? ", new String[] { rollno } );
       //return true;
    }


}