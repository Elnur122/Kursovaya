package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class Date extends AppCompatActivity {
    String[] day = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17",
            "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};

    String[] month = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        Spinner spinnerDay = findViewById(R.id.spinnerDay);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, R.layout.spinner_items1, day);
        adapter1.setDropDownViewResource(R.layout.spinner_items1);
        spinnerDay.setAdapter(adapter1);

        Spinner spinnerMonth = findViewById(R.id.spinnerMonth);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_items1, month);
        adapter.setDropDownViewResource(R.layout.spinner_items1);
        spinnerMonth.setAdapter(adapter);
    }

    public void OnClickNext(View view) {
        Intent intent = new Intent(Date.this, ConfirmActivity.class);
        Bundle extras = getIntent().getExtras();
        String usergos = extras.getString("gos2");
        String userstreet = extras.getString("street");
        String userlog = extras.getString("log4");
        String usluga = extras.getString("uslug3");
        String cst = extras.getString("cost3");
        intent.putExtra("cost4", cst);
        intent.putExtra("uslug4", usluga);
        intent.putExtra("log5", userlog);
        intent.putExtra("street2", userstreet);
        intent.putExtra("gos3", usergos);
        startActivity(intent);
    }

    public void OnClickBackToAddress(View view) {
        Intent intent = new Intent(Date.this, AddressActivity.class);
        startActivity(intent);
    }
}