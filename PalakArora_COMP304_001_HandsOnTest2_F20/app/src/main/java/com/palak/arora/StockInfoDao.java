package com.palak.arora;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface StockInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertDetails(StockInfo data);

    @Query("select * from StockInfo")
    LiveData<List<StockInfo>> getDetails();

    @Query("delete from StockInfo")
    void deleteAllData();

    @Query("select * from StockInfo where CompanyName = :pId")
    LiveData<StockInfo> getselectedStock(String pId);
}
