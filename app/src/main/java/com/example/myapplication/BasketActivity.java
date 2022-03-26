package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.IOException;

public class BasketActivity extends Activity {
    String[] year = {"R13", "R14", "R15", "R16", "R17", "R18", "R19", "R20", "R21", "R22", "R23"};
    private DatabaseHelper mDBHelper;
    private SQLiteDatabase mDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket1);


        mDBHelper = new DatabaseHelper(this);
        try {
            mDBHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }

        try {
            mDb = mDBHelper.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }


        Button btn = findViewById(R.id.button_next);
        EditText editTextMark = findViewById(R.id.spinner);
        EditText editTextModel = findViewById(R.id.Model);
        EditText editTextGos = findViewById(R.id.GosNum);
        Spinner spinner = findViewById(R.id.Rad);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, year);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getMark = editTextMark.getText().toString();
                String getModel = editTextModel.getText().toString();
                String getGos = editTextGos.getText().toString();
                String getRad = spinner.getSelectedItem().toString();

                if (TextUtils.isEmpty(getMark) || TextUtils.isEmpty(getModel)) {

                } else {
                    Boolean insert = mDBHelper.insertDataCar(getMark, getModel, getGos);
                    if (insert == true) {
                        Intent intent = new Intent(BasketActivity.this, AddressActivity.class);
                        String etg = editTextGos.getText().toString();
                        intent.putExtra("gos", etg);
                        Bundle extras = getIntent().getExtras();
                        String userlog = extras.getString("log2");
                        String usluga = extras.getString("uslug");
                        String cst = extras.getString("cost");
                        intent.putExtra("cost2", cst);
                        intent.putExtra("uslug2", usluga);
                        intent.putExtra("log3", userlog);
                        startActivity(intent);
                    }
                }
            }
        });

    }


    public void OnClickCatalog(View view) {
        Intent intent = new Intent(BasketActivity.this, AddressActivity.class);
        startActivity(intent);
    }

    public void OnClickReadyB(View view) {
    }

    public void OnClickBackToSm(View view) {
        Intent intent = new Intent(BasketActivity.this, SmActivity.class);
        startActivity(intent);
    }
}