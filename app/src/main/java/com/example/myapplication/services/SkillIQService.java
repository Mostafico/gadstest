package com.example.myapplication.services;

import com.example.myapplication.Student;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SkillIQService {
    @GET("skilliq")
    Call<List<Student>> getStudents();
}
