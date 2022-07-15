package com.example.databaselibrary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBmain extends SQLiteOpenHelper {
    private static final String DBNAME = "loggingDB";
    private static final String TABLE = "loggingTable2";
    private static final int VER = 1;

    public DBmain(@Nullable Context context) {
        super(context, DBNAME, null, VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table " + TABLE + "(id integer primary key, EventTime text, " +
                "SerialNumber text, AppId text default 'CPRNT', EmpID text, Location integer(4), " +
                "Route integer(2), Day integer(1), Logger text, EventNumber integer(5), " +
                "EventAdditionalDesc text, EventAdditionalNum integer)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "drop table if exists " + TABLE + "";
        db.execSQL(query);
        onCreate(db);
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from loggingTable2 where id=" + id + "", null);
        return res;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE, null);
        return res;

    }

    public boolean insert(String eventTime, String serialNum, String empId, int location,
                          int route, int day, String logger, int eventNum, String eventAdditionalDesc,
                          int eventAdditionalNum) {

        //boolean flag1 = checkEventTimeFormatting(eventTime);
        //boolean flag5 = checkEventNumberFormatting(eventNum);

        //if (flag2 && flag3 && flag4 && flag5) {

        //At this point, we know the record is valid, and we can insert.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("EventTime", eventTime);
        contentValues.put("SerialNumber", serialNum);
        contentValues.put("AppId", "CPRNT");
        contentValues.put("EmpID", empId);
        contentValues.put("Location", location);
        contentValues.put("Route", route);
        contentValues.put("Day", day);
        contentValues.put("Logger", logger);
        contentValues.put("EventNumber", eventNum);
        contentValues.put("EventAdditionalDesc", eventAdditionalDesc);
        contentValues.put("EventAdditionalNum", eventAdditionalNum);

        long temp = db.insert(TABLE, null, contentValues);
        if (temp != -1){
            return true;
        }
        else{
            return false;
        }
        //return true;
    }

    private boolean checkEventNumberFormatting(int eventNum) {
        if (eventNum >= 0 && eventNum <= 21) {
            return true;
        }
        if (eventNum >= 23 && eventNum <= 27) {
            return true;
        }
        switch (eventNum) {
            case 30:
                return true;
            case 32:
                return true;
            case 33:
                return true;
            case 88:
                return true;
            case 99999:
                return true;
            default:
                return false;
        }
    }

    public boolean removeRecord(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE, "id=" + id, null);
        if (result != -1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean updateRecord(int id, String eventTime, String serialNum, String empId, int location,
                                int route, int day, String logger, int eventNum, String eventAdditionalDesc,
                                int eventAdditionalNum) {

        //boolean flag5 = checkEventNumberFormatting(eventNum);

        //if (flag2 && flag3 && flag4 && flag5) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("EventTime", eventTime);
        contentValues.put("SerialNumber", serialNum);
        contentValues.put("AppId", "CPRNT");
        contentValues.put("EmpID", empId);
        contentValues.put("Location", location);
        contentValues.put("Route", route);
        contentValues.put("Day", day);
        contentValues.put("Logger", logger);
        contentValues.put("EventNumber", eventNum);
        contentValues.put("EventAdditionalDesc", eventAdditionalDesc);
        contentValues.put("EventAdditionalNum", eventAdditionalNum);

        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.update(TABLE, contentValues, "id=" + id, null);
        if (result != -1) {
            return true;
        } else {
            return false;
        }
    }
}
