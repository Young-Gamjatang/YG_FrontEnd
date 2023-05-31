package com.example.test.InnerDataBase;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBCheckbox extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Checkbox.db";

    public DBCheckbox(Context context, int version) {
        super(context, DATABASE_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE Checkbox (area TEXT, term INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Checkbox");
        onCreate(sqLiteDatabase);
    }

    // Checkbox 테이블에 area 데이터 입력
    public void areainsert(String area) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO Checkbox (area) VALUES('" + area + "')");
        db.close();
    }

    // Checkbox 테이블에 term 데이터 입력
    public void terminsert(boolean term) {
        int termValue = term ? 1 : 0;

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO Checkbox (term) VALUES(" + termValue + ")");
        db.close();
    }

    // Checkbox 테이블에서 데이터 삭제
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
            boolean term = cursor.getInt(1) == 1;

            result += "area: " + area + ", term: " + term + "\n";
        }

        cursor.close();
        db.close();

        return result;
    }

    public boolean checkDataExists() {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM Checkbox";
        Cursor cursor = db.rawQuery(query, null);
        boolean hasData = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return hasData;
    }

    public void updateArea(String newArea) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE Checkbox SET area = '" + newArea + "'");
        db.close();
    }
}

