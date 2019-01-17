package com.example.week2daily2_studentdatabase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Main2StudentData extends AppCompatActivity {
    EditText etName;
    EditText etMajor;
    EditText etMinor;
    EditText etGpa;
    EditText etDob;
    EditText etCity;
    EditText etState;
    EditText etSsn;
    SQLDBHelper sqldbHelper = new SQLDBHelper(this);

    public static final String TAG = "NULL_CHECK ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_student_data);

        etName = findViewById(R.id.etName);
        etMajor = findViewById(R.id.etMajor);
        etMinor = findViewById(R.id.etMinor);
        etGpa = findViewById(R.id.etGpa);
        etDob = findViewById(R.id.etDob);
        etCity = findViewById(R.id.etCity);
        etState = findViewById(R.id.etState);
        etSsn = findViewById(R.id.etSsn);
    }


    private void clear() {

        etName.setText("");
        etMajor.setText("");
        etMinor.setText("");
        etGpa.setText("");
        etDob.setText("");
        etCity.setText("");
        etState.setText("");
        etSsn.setText("");
    }

    private void displayStudent(Student student) {
        etName.setText(student.getName());
        etMajor.setText(student.getMajor());
        etMinor.setText(student.getMinor());
        etGpa.setText(student.getGpa());
        etDob.setText(student.getDob());
        etCity.setText(student.getHomeCity());
        etState.setText(student.getHomeState());
        etSsn.setText(student.getSsn());
    }
    private void getUpdateStudent(){

        String name = etName.getText().toString();
        String major = etMajor.getText().toString();
        String minor = etMinor.getText().toString();
        String gpa = etGpa.getText().toString();
        String dob = etDob.getText().toString();
        String city = etCity.getText().toString();
        String state = etState.getText().toString();
        String ssn = etSsn.getText().toString();

        Student student = new Student(name, major, minor, gpa, dob, city, state, ssn);
        sqldbHelper.updateStudent(student);
    }
    private void getInsertStudent(){

        String name = etName.getText().toString();
        String major = etMajor.getText().toString();
        String minor = etMinor.getText().toString();
        String gpa = etGpa.getText().toString();
        String dob = etDob.getText().toString();
        String city = etCity.getText().toString();
        String state = etState.getText().toString();
        String ssn = etSsn.getText().toString();

        Student student = new Student(name, major, minor, gpa, dob, city, state, ssn);
        sqldbHelper.insertStudent(student);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSelect:
                try {
                    String ssn = etSsn.getText().toString();
                    Log.d(TAG, " "+ssn);
                    Toast.makeText(this,ssn,Toast.LENGTH_SHORT).show();
                    Student student = sqldbHelper.selectStudent(ssn);
                    displayStudent(student);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Student does not exist." + e, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnUpdate:
                try {
                    getUpdateStudent();
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "This student does not exist." + e, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnInsert:
                try {
                    getInsertStudent();
                    Toast.makeText(getApplicationContext(), "Student Inserted.", Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "This student already exist." + e, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnDelete:
                try {
                    Student student = sqldbHelper.selectStudent(etSsn.getText().toString());
                    sqldbHelper.deleteStudent(student);
                    clear();
                    Toast.makeText(getApplicationContext(), "Student has been deleted.", Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "This student does not exist." + e, Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
