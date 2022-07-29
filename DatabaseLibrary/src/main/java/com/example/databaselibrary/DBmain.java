package com.example.databaselibrary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBmain extends SQLiteOpenHelper {
    private static final String DBNAME = "loggingDB";
    private static final String TABLE1 = "loggingTable2";
    private static final String TABLE2 = "userDetails";
    private static final String TABLE3 = "appSessionData";
    private static final int VER = 1;

    public DBmain(@Nullable Context context) {
        super(context, DBNAME, null, VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query1 = "create table " + TABLE1 + "(id integer primary key, EventTime text, " +
                "SerialNumber text, AppId text default 'CPRNT', EmpID text, Location integer(4), " +
                "Route integer(2), Day integer(1), Logger text, EventNumber integer(5), " +
                "EventAdditionalDesc text, EventAdditionalNum integer)";
        db.execSQL(query1);
        String query2 = "create table " + TABLE2 + "(id integer primary key, Name text, Email text," +
                " Password text, DateOfBirth text, Gender text)";
        db.execSQL(query2);
        String query3 = "create table " + TABLE3 + "(id integer primary key, EventTime text, EventDesc text)";
        db.execSQL(query3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "drop table if exists " + TABLE1 + "";
        db.execSQL(query);
        onCreate(db);
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE1 + " where id=" + id + "", null);
        return res;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE1, null);
        return res;

    }

    public boolean insert(String eventTime, String serialNum, String empId, int location,
                          int route, int day, String logger, int eventNum, String eventAdditionalDesc,
                          int eventAdditionalNum) {

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

        long temp = db.insert(TABLE1, null, contentValues);
        if (temp != -1){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean removeRecord(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE1, "id=" + id, null);
        if (result != -1) {
            return true;
        } else {
            return false;
        }
    }

    public void removeAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE1, null, null);
    }

    public boolean updateRecord(int id, String eventTime, String serialNum, String empId, int location,
                                int route, int day, String logger, int eventNum, String eventAdditionalDesc,
                                int eventAdditionalNum) {

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
        long result = db.update(TABLE1, contentValues, "id=" + id, null);
        if (result != -1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean insertUserDetails(String name, String email, String password, String dateOfBirth,
        String gender){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name", name);
        contentValues.put("Email", email);
        contentValues.put("Password", password);
        contentValues.put("DateOfBirth", dateOfBirth);
        contentValues.put("Gender", gender);

        long temp = db.insert(TABLE2, null, contentValues);
        if (temp != -1){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean insertAppDetails(String eventTime, String eventDescription){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("EventTime", eventTime);
        contentValues.put("EventDesc", eventDescription);

        long temp = db.insert(TABLE3, null, contentValues);
        if (temp != -1){
            return true;
        }
        else{
            return false;
        }
    }

        public Cursor verifyLoginInfo(String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        //Cursor res = db.rawQuery("select * from " + TABLE2 + " where Email=" + email + "", null);
            Cursor res = db.rawQuery("select * from " + TABLE2 + " where Email=" + email + "", null);
        return res;
        /*if (res.getCount() == 0){
            return -1; //the email/password combination entered by the user is not valid
        }
        else{
            res.moveToFirst();
            return res.getInt(0);
        }*/
    }

    public String getName(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE2 + " where id=" + id + "", null);
        res.moveToFirst();
        return res.getString(1);
    }
}
