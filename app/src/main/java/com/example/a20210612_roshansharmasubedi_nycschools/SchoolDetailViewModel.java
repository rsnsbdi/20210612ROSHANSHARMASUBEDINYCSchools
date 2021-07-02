package com.example.a20210612_roshansharmasubedi_nycschools;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.a20210612_roshansharmasubedi_nycschools.model.SchoolScore;
import com.example.a20210612_roshansharmasubedi_nycschools.repo.SchoolRepository;

import java.util.ArrayList;

public class SchoolDetailViewModel extends ViewModel {
    private MutableLiveData<ArrayList<SchoolScore>> schoolScore;
    private SchoolRepository mSchoolRepository;

    public SchoolDetailViewModel() {
        mSchoolRepository = new SchoolRepository();
    }

    public LiveData<ArrayList<SchoolScore>> getSATScore(String id) {
        if (schoolScore == null) {
            schoolScore = mSchoolRepository.getSATScoreLiveData(id);
        }
        return schoolScore;
    }
}
