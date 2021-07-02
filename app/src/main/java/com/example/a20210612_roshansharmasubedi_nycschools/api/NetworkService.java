package com.example.a20210612_roshansharmasubedi_nycschools.api;

import com.example.a20210612_roshansharmasubedi_nycschools.model.NYCHighSchools;
import com.example.a20210612_roshansharmasubedi_nycschools.model.SchoolScore;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NetworkService {

    @GET("s3k6-pzi2.json")
    Call<ArrayList<NYCHighSchools>> getNYCSchools();

    @GET("f9bf-2cp4.json")
    Call<ArrayList<SchoolScore>> getSATScores(@Query("dbn") String dbn);
}
