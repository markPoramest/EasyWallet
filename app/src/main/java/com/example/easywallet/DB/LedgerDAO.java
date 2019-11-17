package com.example.easywallet.DB;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LedgerDAO {
    @Query("SELECT  * FROM Ledger")
    List<LedgerItem> getAll();

    @Insert
    void insert(LedgerItem ledgerItem);

    @Query("Select sum(amout) from ledger")
    int getSum();
}
