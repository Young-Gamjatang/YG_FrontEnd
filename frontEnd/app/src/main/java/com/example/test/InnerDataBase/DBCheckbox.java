package com.example.test.InnerDataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBCheckbox extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Checkbox.db";
    private static final int DATABASE_VERSION = 1;
    private static DBCheckbox instance; // 인스턴스 변수 추가

    private DBCheckbox(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized DBCheckbox getInstance(Context context) {
        if (instance == null) {
            instance = new DBCheckbox(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE Checkbox (area TEXT, term TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Checkbox");
        onCreate(sqLiteDatabase);
    }

    public void insertarea(String area) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Checkbox", null);
        if (cursor.getCount() > 0) {
            db.execSQL("UPDATE Checkbox SET area = '" + area + "'");
        } else {
            db.execSQL("INSERT INTO Checkbox (area) VALUES('" + area + "')");
        }
        cursor.close();
        db.close();
    }

    public void insertterm(String term) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Checkbox", null);
        if (cursor.getCount() > 0) {
            db.execSQL("UPDATE Checkbox SET term = '" + term + "'");
        } else {
            db.execSQL("INSERT INTO Checkbox (term) VALUES('" + term + "')");
        }
        cursor.close();
        db.close();
    }

    public void delete() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM Checkbox");
        db.close();
    }

    public String getResult() {
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        Cursor cursor = db.rawQuery("SELECT * FROM Checkbox", null);
        while (cursor.moveToNext()) {
            String area = cursor.getString(0);
            String term = cursor.getString(1);

            result += area +"/"+ term + "\n";
        }

        cursor.close();
        db.close();

        return result;
    }

    public void updateArea(String newArea) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE Checkbox SET area = '" + newArea + "'");
        db.close();
    }
}
