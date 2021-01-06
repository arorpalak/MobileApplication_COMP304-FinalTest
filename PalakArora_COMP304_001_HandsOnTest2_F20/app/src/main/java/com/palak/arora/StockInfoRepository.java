package com.palak.arora;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class StockInfoRepository {
    private StockInfoDao stockInfoDao;
    private LiveData<List<StockInfo>> allStockData;
    private MutableLiveData<Integer> insertResult = new MutableLiveData<>();

    public StockInfoRepository(Application application) {

        StockInfoDatabase db = StockInfoDatabase.getDatabase(application);
        stockInfoDao = db.stockInfoDao();
        allStockData = stockInfoDao.getDetails();

    }

    public LiveData<List<StockInfo>> getAllData() {
        return allStockData;
    }

    public LiveData<StockInfo> getSelectedStock(String stockID) {
        return stockInfoDao.getselectedStock(stockID);
    }

    public void insertData(StockInfo data) {
        try {
            new StockInsert(stockInfoDao).execute(data);
            insertResult.postValue(1);
        } catch (Exception e) {
            insertResult.postValue(0);
        }
    }

    public LiveData<Integer> getInsertResult() {
        return insertResult;
    }

    private static class StockInsert extends AsyncTask<StockInfo, Void, Void> {

        private StockInfoDao stockInfoDao1;

        private StockInsert(StockInfoDao loginDao) {

            this.stockInfoDao1 = loginDao;

        }

        @Override
        protected Void doInBackground(StockInfo... data) {

            for (int i = 0; i < data.length; i++) {
                stockInfoDao1.insertDetails(data[i]);
            }
            return null;

        }

    }
}



