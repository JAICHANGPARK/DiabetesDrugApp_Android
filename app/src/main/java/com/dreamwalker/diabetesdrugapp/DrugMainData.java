package com.dreamwalker.diabetesdrugapp;

/**
 * Created by 2E313JCP on 2017-06-14.
 */

public class DrugMainData {
    private String drugName;
    private String drugDetail;
    private int thumbnail;

    public DrugMainData() {
    }

    public DrugMainData(String drugName, String drugDetail, int thumbnail) {
        this.drugName = drugName;
        this.drugDetail = drugDetail;
        this.thumbnail = thumbnail;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getDrugDetail() {
        return drugDetail;
    }

    public void setDrugDetail(String drugDetail) {
        this.drugDetail = drugDetail;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
