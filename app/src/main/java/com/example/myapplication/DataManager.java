package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

public final class DataManager {
    private static List<Student> mStudents;
    private static DataManager mDataManager = null;
    private void DataManager(){
    }
    public static List<Student> getData(){
        if(mDataManager == null){
            mDataManager = new DataManager();
        }
        mStudents = new ArrayList<>();
        insertDummyData();
        return mStudents;
    }

    private static void insertDummyData() {
        for (int i = 0; i < 30 ; i++){
            mStudents.add(
                    new Student("Mostafa Ragab", 300, "Egypt"));
        }
    }
}
