package com.example.a20210612_roshansharmasubedi_nycschools;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.a20210612_roshansharmasubedi_nycschools.model.NYCHighSchools;
import com.example.a20210612_roshansharmasubedi_nycschools.repo.SchoolRepository;

import java.util.ArrayList;

public class MainActivityViewModel extends ViewModel {
    private MutableLiveData<ArrayList<NYCHighSchools>> schoolList;
    private SchoolRepository mSchoolRepository;

    public MainActivityViewModel() {
        mSchoolRepository = new SchoolRepository();
    }

    public LiveData<ArrayList<NYCHighSchools>> getSchoolList(Context context) {
        if (schoolList == null) {
            // when null getting LiveData from repository
            schoolList = mSchoolRepository.getNYCSchoolLiveData();
        }
        return schoolList;
    }

}
