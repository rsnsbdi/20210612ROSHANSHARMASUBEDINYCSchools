package com.example.a20210612_roshansharmasubedi_nycschools;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.a20210612_roshansharmasubedi_nycschools.adapter.NYCSchoolAdapter;
import com.example.a20210612_roshansharmasubedi_nycschools.api.NetworkUtil;
import com.example.a20210612_roshansharmasubedi_nycschools.databinding.ActivityMainBinding;
import com.example.a20210612_roshansharmasubedi_nycschools.model.NYCHighSchools;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    ActivityMainBinding binding;

    private MainActivityViewModel mainActivityViewModel;
    private NYCSchoolAdapter nycSchoolAdapter;

    NYCSchoolAdapter.onCardViewClickedListener onCardViewClickedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // using dataBinding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.swipeRefresh.setOnRefreshListener(this);
        binding.swipeRefresh.setRefreshing(true);

        //initialisation of viewModel
        mainActivityViewModel =new ViewModelProvider(this).get(MainActivityViewModel.class);

        getSchoolData();
    }

    private void setAdapterData(ArrayList<NYCHighSchools> nycHighSchools) {
        nycSchoolAdapter = new NYCSchoolAdapter(this,nycHighSchools);
        binding.rvSchoolList.setAdapter(nycSchoolAdapter);
        binding.rvSchoolList.setLayoutManager(new LinearLayoutManager(this));
        nycSchoolAdapter.notifyDataSetChanged();

        nycSchoolAdapter.setOnCardViewClickedListener(new NYCSchoolAdapter.onCardViewClickedListener() {
            @Override
            public void onCardViewClicked(NYCHighSchools highSchool) {
                Log.e( "onCardViewClicked: ", highSchool.getSchool_name() );
                showDetailFragment(highSchool);
            }
        });
    }

    private void showDetailFragment(NYCHighSchools highSchool) {
        SchoolDetailView detailView =new SchoolDetailView(this, highSchool);
        detailView.show(getSupportFragmentManager(),
                "add_photo_dialog_fragment");
    }

    @Override
    public void onRefresh() {
        getSchoolData();
    }

    private void getSchoolData() {

        Log.e( "getSchoolData: ", String.valueOf(NetworkUtil.hasNetwork(this)));

        //checking internet connection
        if (NetworkUtil.hasNetwork(this)){
            //observing LiveData fromViewModel
            mainActivityViewModel.getSchoolList(this)
                    .observe(this, new Observer<ArrayList<NYCHighSchools>>() {
                        @Override
                        public void onChanged(ArrayList<NYCHighSchools> nycHighSchools) {
                            if (nycHighSchools != null){
                                setAdapterData(nycHighSchools);
                                binding.swipeRefresh.setRefreshing(false);
                            }
                        }
                    });
            binding.tvNoInternet.setVisibility(View.GONE);
        }else{
            Snackbar snackbar = Snackbar
                    .make(binding.rootLayout, getText(R.string.no_internet_connection), Snackbar.LENGTH_LONG);
            snackbar.show();
            binding.swipeRefresh.setRefreshing(false);
        }
    }

}