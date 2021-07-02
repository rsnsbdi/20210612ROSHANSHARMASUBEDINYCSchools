package com.example.a20210612_roshansharmasubedi_nycschools.repo;

import android.util.Log;

import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.MutableLiveData;

import com.example.a20210612_roshansharmasubedi_nycschools.api.NetworkCall;
import com.example.a20210612_roshansharmasubedi_nycschools.api.NetworkService;
import com.example.a20210612_roshansharmasubedi_nycschools.model.NYCHighSchools;
import com.example.a20210612_roshansharmasubedi_nycschools.model.SchoolScore;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SchoolRepository {

    NetworkService apiService = new NetworkCall().create(NetworkService.class);

    public MutableLiveData<ArrayList<NYCHighSchools>> getNYCSchoolLiveData() {

        MutableLiveData<ArrayList<NYCHighSchools>> mutableLiveData = new MutableLiveData<>();

        //Using retrofit to get data from API and assigning the response to MutableLiveData
        Call<ArrayList<NYCHighSchools>> call = apiService.getNYCSchools();
        call.enqueue(new Callback<ArrayList<NYCHighSchools>>() {
            @Override
            public void onResponse(Call<ArrayList<NYCHighSchools>> call, Response<ArrayList<NYCHighSchools>> response) {
                if (response.isSuccessful() && response != null) {
                    mutableLiveData.postValue(response.body());
                }
            }
            @Override
            public void onFailure(Call<ArrayList<NYCHighSchools>> call, Throwable t) {
                Log.e("onFailure: ", t.getMessage());
            }
        });
        return mutableLiveData;
    }

    public MutableLiveData<ArrayList<SchoolScore>> getSATScoreLiveData(String id) {

        MutableLiveData<ArrayList<SchoolScore>> mutableLiveData = new MutableLiveData<>();

        Call<ArrayList<SchoolScore>> call = apiService.getSATScores(id);
        call.enqueue(new Callback<ArrayList<SchoolScore>>() {
            @Override
            public void onResponse(Call<ArrayList<SchoolScore>> call, Response<ArrayList<SchoolScore>> response) {
                if (response.isSuccessful() && response != null) {
                    mutableLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<SchoolScore>> call, Throwable t) {
                Log.e("onFailure: ", t.getMessage());
            }
        });
        return mutableLiveData;
    }
}
