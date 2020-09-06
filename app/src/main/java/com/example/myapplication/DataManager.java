package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

public final class DataManager {
    public static List<Student> mStudentsLearning;
    public static List<Student> mStudentsSkillIQ;
    private static DataManager mDataManager = null;
    private void DataManager(){
    }
    public static void getInstance(){
        if(mDataManager == null){
            mDataManager = new DataManager();
        }
        mStudentsLearning = new ArrayList<>();
        mStudentsSkillIQ = new ArrayList<>();
//        insertDummyData();
    }

    private static void insertDummyData() {
        for (int i = 0; i < 30 ; i++){
            mStudentsLearning.add(
                    new Student("Mostafa Ragab", 300, "Egypt"));
        }
    }
}
