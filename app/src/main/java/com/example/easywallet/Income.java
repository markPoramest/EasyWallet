package com.example.easywallet;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.easywallet.DB.LedgerItem;
import com.example.easywallet.DB.LedgerRepository;

public class Income extends AppCompatActivity {
    private int val;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);
        Intent l1 = getIntent();
        val = l1.getIntExtra("type", 0);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            if (val == 0) {
                ab.setTitle("รายรับ");
                ImageView img = findViewById(R.id.imageView);
                img.setImageResource(R.drawable.ic_income);
            } else {
                ab.setTitle("รายจ่าย");
                ImageView img = findViewById(R.id.imageView);
                img.setImageResource(R.drawable.ic_expense);
            }
        }
        Button save = findViewById(R.id.ok);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LedgerRepository l1 = new LedgerRepository(Income.this);
                EditText t1 = findViewById(R.id.des);
                EditText t2 = findViewById(R.id.money);
                if (val == 0) {
                    l1.InsertLedger(new LedgerItem(0, t1.getText().toString(), Integer.parseInt(t2.getText().toString())), new LedgerRepository.insertCallBack() {
                        @Override
                        public void onInsertCallBack() {
                            finish();
                        }
                    });
                } else {
                    l1.InsertLedger(new LedgerItem(0, t1.getText().toString(), Integer.parseInt(t2.getText().toString())*-1), new LedgerRepository.insertCallBack() {
                        @Override
                        public void onInsertCallBack() {
                            finish();
                        }
                    });
                }
            }


        });
    }
}
