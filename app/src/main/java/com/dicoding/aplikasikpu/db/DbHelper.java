package com.dicoding.aplikasikpu.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.dicoding.aplikasikpu.model.Voter;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {
    private static String DATABASE_NAME = "db_kpu";
    private static  final int DATABASE_VERSION = 1;
    private static  final String TABLE_STD = "kpu";
    private static  final String KEY_ID = "id";
    private static  final String KEY_NIK = "nik";
    private static  final String KEY_NAME = "name";
    private static  final String KEY_ADDRESS = "address";
    private static  final String KEY_SEX = "sex";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String CREATE_TABLE_VOTER = "CREATE TABLE "
            + TABLE_STD + " ( " + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NIK + " TEXT,"
            + KEY_NAME + " TEXT, " + KEY_ADDRESS + " TEXT, "
            + KEY_SEX + " TEXT )";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_VOTER);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int ii) {
        db.execSQL("DROP TABLE IF EXISTS'" +TABLE_STD+"'");
        onCreate(db);
    }
    public void addUsers(String nik, String name, String address, String sex){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NIK, nik);
        values.put(KEY_NAME, name);
        values.put(KEY_ADDRESS, address);
        values.put(KEY_SEX, sex);

        db.insert(TABLE_STD, null,values);
    }

    public int updateUser(int id, String nik, String name, String address, String sex) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NIK, nik);
        values.put(KEY_NAME, name);
        values.put(KEY_ADDRESS, address);
        values.put(KEY_SEX, sex);

        return db.update(TABLE_STD, values, KEY_ID + " = ?", new String[]{String.valueOf(id)});
    }

    @SuppressLint("Range")
    public ArrayList<Voter> getAllUser(){
        ArrayList<Voter> userModel = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_STD;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if(c.moveToFirst()){
            do{
                Voter vote = new Voter();
                vote.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                vote.setNik(c.getString(c.getColumnIndex(KEY_NIK)));
                vote.setName(c.getString(c.getColumnIndex(KEY_NAME)));
                vote.setAddress(c.getString(c.getColumnIndex(KEY_ADDRESS)));
                vote.setSex(c.getString(c.getColumnIndex(KEY_SEX)));
                userModel.add(vote);
            } while (c.moveToNext());
        }
        return userModel;
    }
    public void deleteUser(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STD, KEY_ID + " = ?", new String[]{String.valueOf(id)});
    }
}