package com.example.databaselibrary;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


/**
 * @author Jake Heyser for Cintas Corporation
 * @version 1
 * @since 8/9/2022
 *
 * Library that class that implements the basic CRUD methods for a database containing tables
 * used in the "LoginSQLiteActivity" project and the "RecyclerView Database Project"
 */
public class DBmain extends SQLiteOpenHelper {
    private static final String DBNAME = "loggingDb"; // the name of the database containing the below tables
    private static final String TABLE1 = "loggingTable"; // the name of the table for the RecyclerViewDatabaseProject
    private static final String TABLE2 = "userDetails"; // the name of the table for the user's activity in the "LoginSQLiteActivity" project
    private static final String TABLE3 = "appSessionData"; // the name of the table for the app events in the "LoginSQLiteActivity" project
    private static final int VER = 1; // the version number of the database object

    /**
     * Public constructor for an object that has our library as its type
     *
     * @param context the context for the library
     */
    public DBmain(@Nullable Context context) {
        super(context, DBNAME, null, VER);
    }


    /**
     * Method that creates the three tables comprised in the database
     *
     * @param db the SQLite database object that will be called to create the three tables
     *           we need in our database
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating TABLE1
        String query1 = "create table " + TABLE1 + "(id integer primary key, EventTime text, " +
                "SerialNumber text, AppId text default 'CPRNT', EmpID text, Location integer(4), " +
                "Route integer(2), Day integer(1), Logger text, EventNumber integer(5), " +
                "EventAdditionalDesc text, EventAdditionalNum integer)";
        db.execSQL(query1);

        // creating TABLE2
        String query2 = "create table " + TABLE2 + "(id integer primary key, Name text, " +
                "Email text, Password text, DateOfBirth text, Gender text)";
        db.execSQL(query2);

        // creating TABLE3
        String query3 = "create table " + TABLE3 + "(id integer primary key, EventTime text, " +
                "EventDesc text)";
        db.execSQL(query3);
    }


    /**
     * Method that upgrades the version of an existing SQLite database object
     *
     * @param db the name of the SQLite database to be upgraded
     * @param oldVersion the old version of the SQLite database
     * @param newVersion the new version of the SQLite database
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "drop table if exists " + TABLE1 + "";
        db.execSQL(query);
        onCreate(db);
    }


    /**
     * Method that returns a Cursor pointing to all of the data for a particular id in TABLE1
     *
     * @param id the id that we want to retrieve the data at
     * @return a Cursor object pointing to the data in the first row from the database that
     * contains the id parameter as its id
     */
    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE1 + " where id=" + id + "",
                null);
        return res;
    }


    /**
     * Method that returns all of the data from TABLE1
     *
     * @return a Cursor object pointing to all of the data in TABLE1
     */
    public Cursor getAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE1, null);
        return res;
    }


    /**
     * Method that inserts the data from all of the fields used in the Rental Copilot app
     *
     * @param eventTime the time of the event
     * @param serialNum the serial number of the user's device
     * @param empId the id of the employee/partner
     * @param location the location number that the event occurred at
     * @param route the route number
     * @param day the day of the week that the event happened on
     * @param logger any information pertaining to logging
     * @param eventNum the event number that represents the event that happened
     * @param eventAdditionalDesc any additional information about the event in text form
     * @param eventAdditionalNum any additional information about the event in numeric form
     * @return a boolean representing whether the insert was successful or not; true means the
     * insert was successful and false means it was not
     */
    public boolean insert(String eventTime, String serialNum, String empId, int location,
                          int route, int day, String logger, int eventNum,
                          String eventAdditionalDesc, int eventAdditionalNum) {

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
        if (temp != -1) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * Method that removes the record from TABLE1 that has the id corresponding to the id
     * paramter that is passed in to the method
     *
     * @param id the id that we want to remove from TABLE1
     * @return a boolean representing whether the removal was successful or not; true means the
     * removal was successful and false means it was not
     */
    public boolean removeRecord(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE1, "id=" + id, null);
        if (result != -1) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * Method that removes all of the records from TABLE1
     */
    public void removeAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE1, null, null);
    }


    /**
     * Method that updates the record from TABLE1 that has the id corresponding to the id that is
     * passed into the method
     *
     * @param eventTime the time of the event
     * @param serialNum the serial number of the user's device
     * @param empId the id of the employee/partner
     * @param location the location number that the event occurred at
     * @param route the route number
     * @param day the day of the week that the event happened on
     * @param logger any information pertaining to logging
     * @param eventNum the event number that represents the event that happened
     * @param eventAdditionalDesc any additional information about the event in text form
     * @param eventAdditionalNum any additional information about the event in numeric form
     * @return a boolean representing whether the update was successful or not; true means the
     * update was successful and false means it was not
     */
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


    /**
     * Method that inserts the user's information from the fields used in the "LoginSQLiteActivity"
     * project
     *
     * @param name the name that we want to insert into TABLE2
     * @param email the email that we want to insert into TABLE2
     * @param password the password that we want to insert into TABLE2
     * @param dateOfBirth the date of birth that we want to insert into TABLE2
     * @param gender the gender that we want to insert into TABLE2
     * @return a boolean representing whether the insert was successful or not; true means the
     * insert was successful and false means it was not
     */
    public boolean insertUserDetails(String name, String email, String password, String dateOfBirth,
                                     String gender) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name", name);
        contentValues.put("Email", email);
        contentValues.put("Password", password);
        contentValues.put("DateOfBirth", dateOfBirth);
        contentValues.put("Gender", gender);

        long temp = db.insert(TABLE2, null, contentValues);
        if (temp != -1) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * Method that inserts a record into TABLE3 containing the time and description of an event where
     * the user opened or closed the "LoginSQLiteActivity" project
     *
     * @param eventTime the time that the user opened or closed the app
     * @param eventDescription a description of whether the user opened or closed the app
     * @return a boolean representing whether the insert was successful or not; true means the
     * insert was successful and false means it was not
     */
    public boolean insertAppDetails(String eventTime, String eventDescription) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("EventTime", eventTime);
        contentValues.put("EventDesc", eventDescription);

        long temp = db.insert(TABLE3, null, contentValues);
        if (temp != -1) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * Method that verifies the user's login credentials
     * @param email the email that the user entered
     * @param password the password that the user entered
     * @return null if the user did not enter a valid email/password combination, "" if the user
     * entered a valid combination but there was no name associated with that entry in the database,
     * or the name that the user entered if their credentials were valid and there was a name in
     * that entry in the database
     */
    public String verifyLoginInfo(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = getAllUserData();
        if (cursor.getCount() > 0){
            while (cursor.moveToNext()){
                String currentEmail = cursor.getString(2);
                String currentPassword = cursor.getString(3);
                if (currentEmail.equals(email) && currentPassword.equals(password)){
                    String name = cursor.getString(1);
                    if (name == null){
                        return "";
                    }
                    return name;
                }
            }
            return null;
        }
        return null;
    }


    /**
     * Method that returns all of the data in TABLE2 (which pertains to the "LoginSQLiteActivity"
     * project)
     *
     * @return a Cursor pointing to all of the data in TABLE2
     */
    public Cursor getAllUserData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE2, null);
        return res;
    }
}
