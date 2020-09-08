package com.example.myapplication.services;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.PUT;
import retrofit2.http.Url;

public interface SubmitService {
    @PUT
    Call<String> postForm(@Url String url,
                          @Field("entry.1824927963")String email,
                          @Field("entry.1877115667")String Name,
                          @Field("entry.2006916086")String lastName,
                          @Field("entry.284483984")String projectLink);
}
