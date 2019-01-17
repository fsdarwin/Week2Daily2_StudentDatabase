package com.example.week2daily2_studentdatabase;
//DEPENDENCY IMPORTS||||||||||||||||||||||||||-----------
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
//||||||||||||||||||||||||||||||||||||||||||||------------

//DATABASE FIELD NAME IMPORTS||||||||||||||||----------------------------------------------
import java.util.ArrayList;

import static com.example.week2daily2_studentdatabase.dbStudentConstants.DATABASE_NAME;
import static com.example.week2daily2_studentdatabase.dbStudentConstants.DATABASE_VERSION;
import static com.example.week2daily2_studentdatabase.dbStudentConstants.TABLE_NAME;
import static com.example.week2daily2_studentdatabase.dbStudentConstants.FIELD_CITY;
import static com.example.week2daily2_studentdatabase.dbStudentConstants.FIELD_NAME;
import static com.example.week2daily2_studentdatabase.dbStudentConstants.FIELD_MAJOR;
import static com.example.week2daily2_studentdatabase.dbStudentConstants.FIELD_MINOR;
import static com.example.week2daily2_studentdatabase.dbStudentConstants.FIELD_DOB;
import static com.example.week2daily2_studentdatabase.dbStudentConstants.FIELD_GPA;
import static com.example.week2daily2_studentdatabase.dbStudentConstants.FIELD_SSN;
import static com.example.week2daily2_studentdatabase.dbStudentConstants.FIELD_STATE;
//||||||||||||||||||||||||||||||||||||||||--------------------------------------------------

public class SQLDBHelper extends SQLiteOpenHelper {
    //Constuctor
    public SQLDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    int count;
    public void setCount(int count){
        this.count = count;
    }
    public int getCount(){
        return count;
    }

    @Override//CREATE table with necessary fields
    public void onCreate(SQLiteDatabase db) {
        String createStmt = "CREATE TABLE " + TABLE_NAME + " ("
                + FIELD_NAME + " TEXT, "
                + FIELD_MAJOR + " TEXT, "
                + FIELD_MINOR + " TEXT, "
                + FIELD_GPA + " TEXT, "
                + FIELD_DOB + " TEXT, "
                + FIELD_CITY + " TEXT, "
                + FIELD_STATE + " TEXT, "
                + FIELD_SSN + " TEXT PRIMARY KEY)";
        db.execSQL(createStmt);//Creates the database


    }

    @Override//Update version number
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public Student selectStudent(String studentSsn) {
        Student returnStudent = null;//Set return value to null
        if (studentSsn != null && !studentSsn.isEmpty()) {//IF the value passed in is not null
            SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();//OPEN database for read only
            //SELECT
            String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + FIELD_SSN + " = \"" + studentSsn + "\"";
            Cursor cursor = sqLiteDatabase.rawQuery(query, null);
            setCount(cursor.getCount());
            if (cursor.moveToFirst()) {
                String name = cursor.getString(cursor.getColumnIndex(FIELD_NAME));
                String major = cursor.getString(cursor.getColumnIndex(FIELD_MAJOR));
                String minor = cursor.getString(cursor.getColumnIndex(FIELD_MINOR));
                String gpa = cursor.getString(cursor.getColumnIndex(FIELD_GPA));
                String dob = cursor.getString(cursor.getColumnIndex(FIELD_DOB));
                String city = cursor.getString(cursor.getColumnIndex(FIELD_CITY));
                String state = cursor.getString(cursor.getColumnIndex(FIELD_STATE));
                String ssn = cursor.getString(cursor.getColumnIndex(FIELD_SSN));
                returnStudent = new Student(name, major, minor, gpa, dob, city, state, ssn);
            }
            cursor.close();
        }
        return returnStudent;
    }
    public ArrayList<Student> selectAllStudents() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            ArrayList<Student> arrayList = new ArrayList<>();
            do {
                String name = cursor.getString(cursor.getColumnIndex(FIELD_NAME));
                String major = cursor.getString(cursor.getColumnIndex(FIELD_MAJOR));
                String minor = cursor.getString(cursor.getColumnIndex(FIELD_MINOR));
                String gpa = cursor.getString(cursor.getColumnIndex(FIELD_GPA));
                String dob = cursor.getString(cursor.getColumnIndex(FIELD_DOB));
                String city = cursor.getString(cursor.getColumnIndex(FIELD_CITY));
                String state = cursor.getString(cursor.getColumnIndex(FIELD_STATE));
                String ssn = cursor.getString(cursor.getColumnIndex(FIELD_SSN));
                arrayList.add(new Student(name, major, minor, gpa, dob, city, state, ssn));
            } while (cursor.moveToNext());
            return arrayList;
        } else {
            return null;
        }
    }
    public void insertStudent(Student student) {
        SQLiteDatabase database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();

        if (student != null) {
            String name = student.getName();
            String major = student.getMajor();
            String minor = student.getMinor();
            String gpa = student.getGpa();
            String dob = student.getDob();
            String city = student.getHomeCity();
            String state = student.getHomeState();
            String ssn = student.getSsn();

            contentValues.put(FIELD_NAME, name);
            contentValues.put(FIELD_MAJOR, major);
            contentValues.put(FIELD_MINOR, minor);
            contentValues.put(FIELD_GPA, gpa);
            contentValues.put(FIELD_DOB, dob);
            contentValues.put(FIELD_CITY, city);
            contentValues.put(FIELD_STATE, state);
            contentValues.put(FIELD_SSN, ssn);

            database.insert(TABLE_NAME, null, contentValues);
        }
    }
    public int updateStudent(Student student) {
        if (student != null) {
            String whereClause = FIELD_SSN + " = \"" + student.getSsn() + "\"";
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(FIELD_NAME, student.getName());
            contentValues.put(FIELD_MAJOR, student.getMajor());
            contentValues.put(FIELD_MINOR, student.getMinor());
            contentValues.put(FIELD_GPA, student.getGpa());
            contentValues.put(FIELD_DOB, student.getDob());
            contentValues.put(FIELD_CITY, student.getHomeCity());
            contentValues.put(FIELD_STATE, student.getHomeState());
            contentValues.put(FIELD_SSN, student.getSsn());
            return sqLiteDatabase.update(TABLE_NAME, contentValues, whereClause, null);
        } else {
            return 0;
        }
    }
    public int deleteStudent(Student student) {
        String whereClause = FIELD_SSN + " = \"" + student.getSsn() + "\"";
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME, whereClause, null);
    }
}
