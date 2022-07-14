/*
package com.example.databaselibrary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBmain extends SQLiteOpenHelper {
    private static final String DBNAME = "loggingDB";
    private static final String TABLE = "loggingTable";
    private static final int VER = 1;

    public DBmain(@Nullable Context context) {
        super(context, DBNAME, null, VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //String query = "create table " + TABLE + "(id integer primary key, fname text, lname text)";
        String query = "create table " + TABLE + "(id integer primary key, EventTime text, " +
                "SerialNumber text, AppId text default 'CPRNT', EmpId text, Location text, " +
                "Route text, Day text, Logger text, EventNumber text, " +
                "EventAdditionalDesc text, EventAdditionalNum text)";
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
        Cursor res = db.rawQuery("select * from loggingTable where id=" + id + "", null);
        return res;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE, null);
        return res;

    }

    public long insert(String eventTime, String serialNum, String empId, String location,
                          String route, String day, String logger, String eventNum, String eventAdditionalDesc,
                          String eventAdditionalNum) {

        //boolean flag1 = checkEventTimeFormatting(eventTime);
        //boolean flag2 = checkLocationFormatting(location);
        //boolean flag3 = checkRouteFormatting(route);
        //boolean flag4 = checkDayFormatting(day);
        //boolean flag5 = checkEventNumberFormatting(eventNum);

        //if (flag2 && flag3 && flag4 && flag5) {

        //At this point, we know the record is valid, and we can insert.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("EventTime", eventTime);
        contentValues.put("SerialNumber", serialNum);
        contentValues.put("AppId", "CPRNT");
        contentValues.put("EmpId", empId);
        contentValues.put("Location", location);
        contentValues.put("Route", route);
        contentValues.put("Day", day);
        contentValues.put("Logger", logger);
        contentValues.put("EventNumber", eventNum);
        contentValues.put("EventAdditionalDesc", eventAdditionalDesc);
        contentValues.put("EventAdditionalNum", eventAdditionalNum);

        long result = db.insert(TABLE, null, contentValues);
        return result;
    }

    private boolean checkLocationFormatting(int location) {
        int length = 0;
        long temp = 1;
        while (temp <= location && length <= 4) {
            length++;
            temp *= 10;
        }
        if (length == 4) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkRouteFormatting(int route) {
        int length = 0;
        long temp = 1;
        while (temp <= route && length <= 2) {
            length++;
            temp *= 10;
        }
        if (length == 2) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkDayFormatting(String day) {
        if (day.length() == 1 && Character.isLetter(day.charAt(0))) {
            return true;
        } else {
            return false;
        }
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

    public boolean updateRecord(int id, String eventTime, String serialNum, String empId, String location,
                                String route, String day, String logger, String eventNum, String eventAdditionalDesc,
                                String eventAdditionalNum) {

        //boolean flag2 = checkLocationFormatting(location);
        //boolean flag3 = checkRouteFormatting(route);
        //boolean flag4 = checkDayFormatting(day);
        //boolean flag5 = checkEventNumberFormatting(eventNum);

        //if (flag2 && flag3 && flag4 && flag5) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("EventTime", eventTime);
        contentValues.put("SerialNumber", serialNum);
        contentValues.put("AppId", "CPRNT");
        contentValues.put("EmpId", empId);
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
*/
package com.example.databaselibrary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBmain extends SQLiteOpenHelper {
    private static final String DBNAME = "loggingDB";
    private static final String TABLE = "loggingTable";
    private static final int VER = 1;

    public DBmain(@Nullable Context context) {
        super(context, DBNAME, null, VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //String query = "create table " + TABLE + "(id integer primary key, fname text, lname text)";
        String query = "create table " + TABLE + "(id integer primary key, EventTime text)";//", " +
                //"SerialNumber text, AppId text default 'CPRNT', EmpID text, Location text, " +
                //"Route text, Day text, Logger text, EventNumber text, " +
                //"EventAdditionalDesc text, EventAdditionalNum text)";
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
        Cursor res = db.rawQuery("select * from loggingTable where id=" + id + "", null);
        return res;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE, null);
        return res;

    }

    public boolean insert(String eventTime, String serialNum, String empId, String location,
                          String route, String day, String logger, String eventNum, String eventAdditionalDesc,
                          String eventAdditionalNum) {

        //boolean flag1 = checkEventTimeFormatting(eventTime);
        //boolean flag2 = checkLocationFormatting(location);
        //boolean flag3 = checkRouteFormatting(route);
        //boolean flag4 = checkDayFormatting(day);
        //boolean flag5 = checkEventNumberFormatting(eventNum);

        //if (flag2 && flag3 && flag4 && flag5) {

        //At this point, we know the record is valid, and we can insert.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("EventTime", eventTime);
        /*contentValues.put("SerialNumber", serialNum);
        contentValues.put("AppId", "CPRNT");
        contentValues.put("EmpID", empId);
        contentValues.put("Location", location);
        contentValues.put("Route", route);
        contentValues.put("Day", day);
        contentValues.put("Logger", logger);
        contentValues.put("EventNumber", eventNum);
        contentValues.put("EventAdditionalDesc", eventAdditionalDesc);
        contentValues.put("EventAdditionalNum", eventAdditionalNum);*/

        db.insert(TABLE, null, contentValues);
        return true;
    }

    private boolean checkLocationFormatting(int location) {
        int length = 0;
        long temp = 1;
        while (temp <= location && length <= 4) {
            length++;
            temp *= 10;
        }
        if (length == 4) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkRouteFormatting(int route) {
        int length = 0;
        long temp = 1;
        while (temp <= route && length <= 2) {
            length++;
            temp *= 10;
        }
        if (length == 2) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkDayFormatting(String day) {
        if (day.length() == 1 && Character.isLetter(day.charAt(0))) {
            return true;
        } else {
            return false;
        }
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

    public boolean updateRecord(int id, String eventTime, String serialNum, String empId, String location,
                                String route, String day, String logger, String eventNum, String eventAdditionalDesc,
                                String eventAdditionalNum) {

        //boolean flag2 = checkLocationFormatting(location);
        //boolean flag3 = checkRouteFormatting(route);
        //boolean flag4 = checkDayFormatting(day);
        //boolean flag5 = checkEventNumberFormatting(eventNum);

        //if (flag2 && flag3 && flag4 && flag5) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("EventTime", eventTime);
       /* contentValues.put("SerialNumber", serialNum);
        contentValues.put("AppId", "CPRNT");
        contentValues.put("EmpID", empId);
        contentValues.put("Location", location);
        contentValues.put("Route", route);
        contentValues.put("Day", day);
        contentValues.put("Logger", logger);
        contentValues.put("EventNumber", eventNum);
        contentValues.put("EventAdditionalDesc", eventAdditionalDesc);
        contentValues.put("EventAdditionalNum", eventAdditionalNum);*/

        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.update(TABLE, contentValues, "id=" + id, null);
        if (result != -1) {
            return true;
        } else {
            return false;
        }
    }
}
