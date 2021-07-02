package com.example.a20210612_roshansharmasubedi_nycschools.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a20210612_roshansharmasubedi_nycschools.R;
import com.example.a20210612_roshansharmasubedi_nycschools.databinding.ItemSchoolViewBinding;
import com.example.a20210612_roshansharmasubedi_nycschools.model.NYCHighSchools;

import java.util.ArrayList;

public class NYCSchoolAdapter extends RecyclerView.Adapter<NYCSchoolAdapter.NYCSchoolViewHolder> {
    private onCardViewClickedListener onCardViewClickedListener;
    ArrayList<NYCHighSchools> schoolsArrayList = new ArrayList<>();

    public NYCSchoolAdapter(Context context, ArrayList<NYCHighSchools> schoolList){
        this.schoolsArrayList = schoolList;
    }

    ItemSchoolViewBinding itemSchoolViewBinding;

    public void setOnCardViewClickedListener(NYCSchoolAdapter.onCardViewClickedListener onCardViewClickedListener) {
        this.onCardViewClickedListener = onCardViewClickedListener;
    }

    @NonNull
    @Override
    public NYCSchoolViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        itemSchoolViewBinding = DataBindingUtil.inflate(inflater, R.layout.item_school_view,parent,false);
        return new NYCSchoolViewHolder(itemSchoolViewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull NYCSchoolViewHolder holder, int position) {
        final NYCHighSchools schools = schoolsArrayList.get(position);
         itemSchoolViewBinding.setViewmodel(schools);

         itemSchoolViewBinding.schoolCardView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 onCardViewClickedListener.onCardViewClicked(schools);
                 Log.e("onClick: ", schools.getDbn());
             }
         });

    }

    @Override
    public int getItemCount() {
        return schoolsArrayList ==null ? 0: schoolsArrayList.size();
    }

    public class NYCSchoolViewHolder extends RecyclerView.ViewHolder {
        public NYCSchoolViewHolder(ItemSchoolViewBinding viewBinding) {
            super(itemSchoolViewBinding.getRoot());
            itemSchoolViewBinding = viewBinding;
        }
    }

    public interface onCardViewClickedListener{
        void onCardViewClicked(NYCHighSchools highSchool);
    }
}
