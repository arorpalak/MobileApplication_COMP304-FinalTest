package com.palak.arora;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class StockInfoViewModel extends AndroidViewModel {
    // calling repository tasks and
    // sending the results to the Activity
    private static StockInfoRepository stockInfoRepository;
    private LiveData<Integer> insertResult;
    private LiveData<List<StockInfo>> allStock;
    private StockInfoDao stockInfoDao;

    public StockInfoViewModel(@NonNull Application application) {
        super(application);
        stockInfoRepository = new StockInfoRepository(application);
        insertResult = stockInfoRepository.getInsertResult();
        allStock = stockInfoRepository.getAllData();
    }

    //calls repository to insert a patient
    public void insert(StockInfo stockInfo) {
        stockInfoRepository.insertData(stockInfo);
    }

    //gets insert results as LiveData object
    public LiveData<Integer> getInsertResult() {
        return insertResult;
    }

    //returns query results as live data object
    public LiveData<List<StockInfo>> getAllResults() {
        return allStock;
    }

    public static LiveData<StockInfo> getSpecificStock(String companyName) {
        return stockInfoRepository.getSelectedStock(companyName);
    }


}
