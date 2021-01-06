package com.palak.arora;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {StockInfo.class}, version = 1, exportSchema = false)
public abstract class StockInfoDatabase extends RoomDatabase {

    public abstract StockInfoDao stockInfoDao();

    private static com.palak.arora.StockInfoDatabase INSTANCE;

    public static com.palak.arora.StockInfoDatabase getDatabase(final Context context) {

        if (INSTANCE == null) {

            synchronized (com.palak.arora.StockInfoDatabase.class) {

                if (INSTANCE == null) {

                    INSTANCE = Room.databaseBuilder(
                            context, com.palak.arora.StockInfoDatabase.class, "STOCK_INFO_DATABASE")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;

    }

}
