package com.example.a20210612_roshansharmasubedi_nycschools;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.a20210612_roshansharmasubedi_nycschools.model.NYCHighSchools;
import com.example.a20210612_roshansharmasubedi_nycschools.model.SchoolScore;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

public class SchoolDetailView extends BottomSheetDialogFragment {
    Context context;
    NYCHighSchools nycHighSchools;

    public SchoolDetailView(Context context, NYCHighSchools highSchool) {
        this.context = context;
        this.nycHighSchools = highSchool;
    }

    View view;

    TextView tvSchoolName, tvMathScore, tvReadingScore, tvWritingScore, tvSchoolOverview, tvContact,tvAddress;
    CardView cardView;
    SwipeRefreshLayout swipeRefreshLayout;

    private SchoolDetailViewModel detailViewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.school_detail_view, container,
                false);

        tvSchoolName = view.findViewById(R.id.school_name);
        tvMathScore = view.findViewById(R.id.sat_math);
        tvReadingScore = view.findViewById(R.id.sat_reading);
        tvWritingScore = view.findViewById(R.id.sat_writing);
        tvSchoolOverview = view.findViewById(R.id.school_overview);
        tvContact = view.findViewById(R.id.school_contact);
        tvAddress = view.findViewById(R.id.school_contactAddress);

        cardView = view.findViewById(R.id.schoolCardView);

        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        initViews();
        // get the views and attach the listener
        return view;

    }

    private void initViews() {
        tvSchoolName.setText(nycHighSchools.school_name);

        swipeRefreshLayout.setRefreshing(true);
        detailViewModel = new ViewModelProvider(this).get(SchoolDetailViewModel.class);

        detailViewModel.getSATScore(nycHighSchools.dbn).observe(getViewLifecycleOwner(), new Observer<ArrayList<SchoolScore>>() {
            @Override
            public void onChanged(ArrayList<SchoolScore> schoolScores) {
                if (schoolScores != null && schoolScores.size()> 0){
                    SchoolScore schoolScore = schoolScores.get(0);
                    showDetails(schoolScore);
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        });

    }

    private void showDetails(SchoolScore schoolScore) {
        cardView.setVisibility(View.VISIBLE);
         tvMathScore.setText(schoolScore.sat_math_avg_score);
         tvReadingScore.setText(schoolScore.sat_critical_reading_avg_score);
         tvWritingScore.setText(schoolScore.sat_writing_avg_score);
         tvSchoolOverview.setText(nycHighSchools.overview_paragraph);
         tvContact.setText("Call : "+nycHighSchools.phone_number);
         tvAddress.setText("Address: "+nycHighSchools.primary_address_line_1+","+nycHighSchools.city);
    }

}
