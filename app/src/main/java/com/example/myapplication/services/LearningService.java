package com.example.myapplication.services;

import com.example.myapplication.data.Student;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LearningService {
    @GET("hours")
    Call<List<Student>> getStudents();
}
