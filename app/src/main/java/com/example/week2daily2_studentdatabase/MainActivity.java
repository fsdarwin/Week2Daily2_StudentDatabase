package com.example.week2daily2_studentdatabase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerViewAdapter rvAdaptor;
    public static final String TAG = "FRANK: ";


    SQLDBHelper sqldbHelper = new SQLDBHelper(this);//INSTANCIATE database

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //INSERT students and log them
        Student studentA = new Student("Pat","Art History","Pan Handling","2.1","31 Feb 2000","Nashville","TN","1");
        sqldbHelper.insertStudent(studentA);
        Log.d(TAG, "INSERT: " + studentA);
        Student studentB = new Student("Dave","Pottery","Basket Weaving","2.9","08 Aug 2001","San Francisco","CA","2");
        sqldbHelper.insertStudent(studentB);
        Log.d(TAG, "INSERT: " + studentB);
        Student studentC = new Student("Carrie","Law","Ass-kissing","4.1","04 Jul 1999","New York","NY","3");
        sqldbHelper.insertStudent(studentC);
        Log.d(TAG, "INSERT: " + studentC);
        Student studentD = new Student("Ty","Hanging Out","Girls","2.0","15 Feb 2002","Orlando","FL","4");
        sqldbHelper.insertStudent(studentD);
        Log.d(TAG, "INSERT: " + studentD);
        Student studentE = new Student("Marcus","Math","Computers","3.9","15 Sep 2002","Portland","OR","5");
        sqldbHelper.insertStudent(studentE);
        Log.d(TAG, "INSERT: " + studentE);
        Student studentF = new Student("Sue","English","Waitressing","4.0","17 Apr 2002","Seattle","WA","6");
        sqldbHelper.insertStudent(studentF);
        Log.d(TAG, "INSERT: " + studentF);

        recyclerView = findViewById(R.id.rvMainRecyclerView);
        RecyclerViewAdapter rvAdapter = new RecyclerViewAdapter(listOfStudents());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(rvAdapter);
    }

    private ArrayList<Student> listOfStudents(){
        ArrayList<Student> studentArrayList = new ArrayList<>();

        studentArrayList = sqldbHelper.selectAllStudents();

        return studentArrayList;
    }

    public void onClick(View view) {
        //Student student = new Student("a","c","d","b","e","f","g","h");
        //rvAdaptor.addStudent(student);
        Intent intent = new Intent(this, Main2StudentData.class);
        startActivity(intent);
    }
}
