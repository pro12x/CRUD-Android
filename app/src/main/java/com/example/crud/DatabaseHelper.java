package com.example.crud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper
{
    public static final String DB_NAME = "Person.db";
    public static final String TABLE_NAME = "Person";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "PHONE";
    public static final String COL_4 = "AGE";

    //Constructeur de la classe DatebaseHelper
    public DatabaseHelper(/*@Nullable*/ Context context/*, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version*/) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT NOT NULL, PHONE TEXT NOT NULL, AGE INTEGER NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int older, int newer)
    {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean add(String name, String phone, String age)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, phone);
        contentValues.put(COL_4, age);

        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

        if (result == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public Cursor getAll()
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " +  TABLE_NAME, null);
        return cursor;
    }

    public boolean update(String id, String name, String phone, String age)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, phone);
        contentValues.put(COL_4, age);

        sqLiteDatabase.update(TABLE_NAME, contentValues, COL_1 + " = ?", new String[] {id});

        return true;
    }

    public Integer delete(String id)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        return sqLiteDatabase.delete(TABLE_NAME, COL_1 + " = ?", new String[] {id});
    }
}
