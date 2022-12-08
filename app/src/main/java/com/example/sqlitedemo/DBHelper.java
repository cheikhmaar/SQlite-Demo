package com.example.sqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "login.db";

    public DBHelper(Context context) {
        super(context, "login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create  table users(usermane TEXT primary key, pwd TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists users");

    }

    public Boolean insertData(String username, String pwd){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("username", username);
        values.put("pwd", pwd);

        long result = db.insert("users", null, values);
        if (result==-1) return false;
        else return true;

    }

    public Boolean checkusername(String username){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username=?", new String[] {username});

        if (cursor.getCount()>0)
            return false;
        else
            return true;

    }

    public Boolean checkusernamepwd(String username, String pwd){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username=? and pwd=?", new String[] {username, pwd});

        if (cursor.getCount()>0)
            return false;
        else
            return true;

    }

}
