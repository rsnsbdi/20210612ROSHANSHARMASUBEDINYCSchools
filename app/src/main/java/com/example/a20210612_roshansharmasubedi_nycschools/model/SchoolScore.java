package com.example.a20210612_roshansharmasubedi_nycschools.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SchoolScore {
    @SerializedName("dbn")
    @Expose
    public String dbn;
    @SerializedName("school_name")
    @Expose
    public String school_name;
    @SerializedName("num_of_sat_test_takers")
    @Expose
    public String num_of_sat_test_takers;
    @SerializedName("sat_critical_reading_avg_score")
    @Expose
    public String sat_critical_reading_avg_score;
    @SerializedName("sat_math_avg_score")
    @Expose
    public String sat_math_avg_score;
    @SerializedName("sat_writing_avg_score")
    @Expose
    public String sat_writing_avg_score;

    public String getDbn() {
        return dbn;
    }

    public void setDbn(String dbn) {
        this.dbn = dbn;
    }

    public String getSchool_name() {
        return school_name;
    }

    public void setSchool_name(String school_name) {
        this.school_name = school_name;
    }

    public String getNum_of_sat_test_takers() {
        return num_of_sat_test_takers;
    }

    public void setNum_of_sat_test_takers(String num_of_sat_test_takers) {
        this.num_of_sat_test_takers = num_of_sat_test_takers;
    }

    public String getSat_critical_reading_avg_score() {
        return sat_critical_reading_avg_score;
    }

    public void setSat_critical_reading_avg_score(String sat_critical_reading_avg_score) {
        this.sat_critical_reading_avg_score = sat_critical_reading_avg_score;
    }

    public String getSat_math_avg_score() {
        return sat_math_avg_score;
    }

    public void setSat_math_avg_score(String sat_math_avg_score) {
        this.sat_math_avg_score = sat_math_avg_score;
    }

    public String getSat_writing_avg_score() {
        return sat_writing_avg_score;
    }

    public void setSat_writing_avg_score(String sat_writing_avg_score) {
        this.sat_writing_avg_score = sat_writing_avg_score;
    }
}
