package com.palak.arora;
//301112908
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button displayButton, insertDataButton;
    RadioButton  radioGoogle, radioAmazon, radioSSNLF;
    StockInfo stockInfo;
    StockInfoViewModel stockInfoViewModel;
    TextView tvOpeningPrice, tvClosingPrice;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayButton = findViewById(R.id.btnDisplay);
        displayButton.setOnClickListener(this);

        radioSSNLF = findViewById(R.id.radioSSNLF);
        radioGoogle = findViewById(R.id.radioGoogle);
        radioAmazon = findViewById(R.id.radioAmazon);
        tvOpeningPrice = findViewById(R.id.textOpeningPrice);
        tvClosingPrice = findViewById(R.id.textClosingPrice);
        insertDataButton = findViewById(R.id.btnInsertData);
        insertDataButton.setOnClickListener(this);
        stockInfoViewModel = ViewModelProviders.of(this).get(StockInfoViewModel.class);
        stockInfoViewModel.getInsertResult().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer result) {
                if (result == 1) {
                    Toast.makeText(MainActivity.this, "Stock Data saved", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Error saving stock data", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void insertData() {

        stockInfo = new StockInfo("Google", 1762.25 , 1760.33);
        stockInfoViewModel.insert(stockInfo);

        stockInfo = new StockInfo("Amazon", 3088.99,3099.11);
        stockInfoViewModel.insert(stockInfo);

        stockInfo = new StockInfo( "SSNLF", 72700 ,  72555);
        stockInfoViewModel.insert(stockInfo);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btnDisplay) {
            getData();
        } else if (id == R.id.btnInsertData) {
            insertData();
            tvOpeningPrice.setText("");
            tvClosingPrice.setText("");
        }
    }

    private void getData() {
        String selectedStock = "";
        if (radioAmazon.isChecked()) {
            selectedStock = radioAmazon.getText().toString();
        } else if (radioGoogle.isChecked()) {
            selectedStock = radioGoogle.getText().toString();
        } else if (radioSSNLF.isChecked()) {
            selectedStock = radioSSNLF.getText().toString();
        } else {
            selectedStock = radioAmazon.getText().toString();
        }
        ShowOnUI(selectedStock);

    }
    private void ShowOnUI(String selectedStock) {
        stockInfoViewModel.getSpecificStock(selectedStock).observe(this, new Observer<StockInfo>() {
            @Override
            public void onChanged(StockInfo stockInfo) {
                if (stockInfo != null) {
                    tvOpeningPrice.setText("Opening Price: " + stockInfo.getOpeningPrice());
                    tvClosingPrice.setText("Closing Price: " + String.valueOf(stockInfo.getClosingPrice()));
                }
            }
        });
    }


}