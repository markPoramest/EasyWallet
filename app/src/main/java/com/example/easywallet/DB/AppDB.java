package com.example.easywallet.DB;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {LedgerItem.class}, exportSchema = false, version = 1)
public abstract class AppDB extends RoomDatabase {

    private static final String DB_NAME = "wallet.db";

    public abstract LedgerDAO ledgerDAO();

    private static AppDB mInstance;

    public static synchronized AppDB getInstance(Context context) {
        if (mInstance == null) {
            mInstance = Room
                    .databaseBuilder(
                            context.getApplicationContext(),
                            AppDB.class,
                            DB_NAME
                    )
                    .build();
        }
        return mInstance;
    }
}
