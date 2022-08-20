package com.example.trackcovid.ui.country;

public class CovidCountry {
    String mCovidCountry,mCases,mTodayCases,mDeath,mRecovered,mcritical;

    public CovidCountry(String mCovidCountry, String mCases) {
        this.mCovidCountry = mCovidCountry;
        this.mCases = mCases;
        this.mTodayCases = mTodayCases;
        this.mDeath = mDeath;
        this.mRecovered = mRecovered;
        this.mcritical = mcritical;
    }

    public String getmCovidCountry() {
        return mCovidCountry;
    }

    public String getmCases() {
        return mCases;
    }
}
