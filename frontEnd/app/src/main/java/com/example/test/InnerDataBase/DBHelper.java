package com.example.test.InnerDataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATEBASE_NAME = "Quality_Restaurant.db";

    public DBHelper(Context context, int version) {
        super(context, DATEBASE_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE Quality_Restaurant(Latitude DOUBLE, Longitude DOUBLE, CggCode TEXT, UpsoName TEXT, " +
                "SiteAdderss TEXT, GeEnYn TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Quality_Restaurant");
        onCreate(sqLiteDatabase);
    }

    // Person Table 데이터 입력
    public void insert(double latitude, double longitude, String cggCode, String upsoName,
                       String siteAddress, String geEnYn) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO Person VALUES('" + latitude + "', " + longitude + ", '" + cggCode + "', " + upsoName + "," + siteAddress + ", '" + geEnYn + "')");
        db.close();
    }

    // Person Table 조회
    public String getResult(int index, String upsoName) {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        String result = null;

        Cursor cursor = getWordMatches(upsoName, db);

        result += cursor.getDouble(0)
                + "," + cursor.getDouble(1)
                + "," + cursor.getString(2)
                + "," + cursor.getString(3)
                + "," + cursor.getString(4)
                + "," + cursor.getString(5)
                + "/n";

        return result;
    }

//        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
//        Cursor cursor = db.rawQuery("SELECT * FROM Quality_Restaurant", null);
////        while (cursor.moveToNext()) {

    public Cursor getWordMatches(String upsoName, SQLiteDatabase db) {
        String[] projection = {"UpsoName"};
        String selection = "UpsoName = ?";
        String[] selectionArgs = {"upsoName"};

        return db.query("Quality_Restaurant",projection, selection, selectionArgs, null, null, null);
    }
}
