package com.example.week2daily2_studentdatabase;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    ArrayList<Student> studentArrayList;

    public RecyclerViewAdapter(ArrayList<Student> studentArrayList) {
        this.studentArrayList = studentArrayList;
    }


    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder viewHolder, int position) {
        Student student = studentArrayList.get(position);

        if (student != null) {
            String name = student.getName();
            String major = student.getMajor();
            String minor = student.getMinor();
            String gpa = student.getGpa();
            String dob = student.getDob();
            String homeCity = student.getHomeCity();
            String homeState = student.getHomeState();
            String ssn = student.getSsn();

            viewHolder.tvName.setText("Name: " + name);
            viewHolder.tvMajor.setText("Major: " + major);
            viewHolder.tvMinor.setText("Minor: " + minor);
            viewHolder.tvGpa.setText("GPA: " + gpa);
            viewHolder.tvDob.setText("DOB: " + dob);
            viewHolder.tvHomeCity.setText("City: " + homeCity);
            viewHolder.tvHomeState.setText("State: " + homeState);
            viewHolder.tvSsn.setText("SSN: " + ssn);
        }

    }

    @Override
    public int getItemCount() {

        return studentArrayList != null ? studentArrayList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //Declare Views
        TextView tvName;
        TextView tvMajor;
        TextView tvMinor;
        TextView tvGpa;
        TextView tvDob;
        TextView tvHomeCity;
        TextView tvHomeState;
        TextView tvSsn;

        public TextView getTvName() {
            return tvName;
        }

        public void setTvName(TextView tvName) {
            this.tvName = tvName;
        }

        public TextView getTvMajor() {
            return tvMajor;
        }

        public void setTvMajor(TextView tvMajor) {
            this.tvMajor = tvMajor;
        }

        public TextView getTvMinor() {
            return tvMinor;
        }

        public void setTvMinor(TextView tvMinor) {
            this.tvMinor = tvMinor;
        }

        public TextView getTvGpa() {
            return tvGpa;
        }

        public void setTvGpa(TextView tvGpa) {
            this.tvGpa = tvGpa;
        }

        public TextView getTvDob() {
            return tvDob;
        }

        public void setTvDob(TextView tvDob) {
            this.tvDob = tvDob;
        }

        public TextView getTvHomeCity() {
            return tvHomeCity;
        }

        public void setTvHomeCity(TextView tvHomeCity) {
            this.tvHomeCity = tvHomeCity;
        }

        public TextView getTvHomeState() {
            return tvHomeState;
        }

        public void setTvHomeState(TextView tvHomeState) {
            this.tvHomeState = tvHomeState;
        }

        public TextView getTvSsn() {
            return tvSsn;
        }

        public void setTvSsn(TextView tvSsn) {
            this.tvSsn = tvSsn;
        }

        //Instanciate Views
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvMajor = itemView.findViewById(R.id.tvMajor);
            tvMinor = itemView.findViewById(R.id.tvMinor);
            tvGpa = itemView.findViewById(R.id.tvGpa);
            tvDob = itemView.findViewById(R.id.tvDob);
            tvHomeCity = itemView.findViewById(R.id.tvHomeCity);
            tvHomeState = itemView.findViewById(R.id.tvHomeState);
            tvSsn = itemView.findViewById(R.id.tvSsn);
            Student oneStudent;
        }
    }

    public ArrayList<Student> getStudentArrayList() {
        return studentArrayList;
    }

    public void setStudentArrayList(ArrayList<Student> studentArrayList) {
        this.studentArrayList = studentArrayList;
    }

    public void addStudent(Student student){
        studentArrayList.add(student);
        notifyDataSetChanged();
    }
}
