package com.example.easywallet.DB;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.easywallet.R;

@Entity(tableName = "Ledger")
public class LedgerItem {
    @PrimaryKey(autoGenerate = true)
    public  int id;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "amout")
    public int amout;

    @ColumnInfo(name = "img")
    public int img;

    public LedgerItem(int id, String description, int amout) {
        this.id = id;
        this.description = description;
        this.amout = amout;
        if(amout>=0){
            this.img = R.drawable.ic_income;
        }
        else {
            this.img = R.drawable.ic_expense;
        }
    }
}
