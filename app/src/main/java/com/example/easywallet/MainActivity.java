package com.example.easywallet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.easywallet.Adapter.LedgerRecyclerViewAdapter;
import com.example.easywallet.DB.AppDB;
import com.example.easywallet.DB.LedgerItem;
import com.example.easywallet.DB.LedgerRepository;

import org.w3c.dom.Text;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LedgerRepository r1 = new LedgerRepository(MainActivity.this);


        Button income = findViewById(R.id.income);
        income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent l1 = new Intent(MainActivity.this,Income.class);
                l1.putExtra("type",0);
                startActivity(l1);
               /* LedgerRepository l1 = new LedgerRepository(MainActivity.this);
                l1.InsertLedger(new LedgerItem(0, "เงินจากภาษีประชาชน", 1000), new LedgerRepository.insertCallBack() {
                    @Override
                    public void onInsertCallBack() {
                        ReloadData();
                    }
                });*/


            }
        });
        Button expense = findViewById(R.id.expense);
        expense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent l1 = new Intent(MainActivity.this,Income.class);
                l1.putExtra("type",1);
                startActivity(l1);
            /*    LedgerRepository l1 = new LedgerRepository(MainActivity.this);
                l1.InsertLedger(new LedgerItem(0, "จ่ายภาษี", -5000), new LedgerRepository.insertCallBack() {
                    @Override
                    public void onInsertCallBack() {
                        ReloadData();
                    }
                });*/


            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        ReloadData();
    }

    private void ReloadData() {
       LedgerRepository l1 = new LedgerRepository(MainActivity.this);
       l1.getLedger(new LedgerRepository.callBack() {
           @Override
           public void getLedgerCallBack(List<LedgerItem> LedgerItemList) {
               int totalAmout = 0;
               for(int i=0;i<LedgerItemList.size();i++)
               {
                   totalAmout+=LedgerItemList.get(i).amout;
               }
               TextView balance = findViewById(R.id.Balance);
               balance.setText(totalAmout+"");
               RecyclerView recyclerView = findViewById(R.id.ledger_view);
               LedgerRecyclerViewAdapter adapter = new LedgerRecyclerViewAdapter(MainActivity.this,R.layout.item_ledger,LedgerItemList);
               recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
               recyclerView.setAdapter(adapter);

           }
       });
    }


}
