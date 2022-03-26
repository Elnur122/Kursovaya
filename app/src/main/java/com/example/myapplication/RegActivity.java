package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.io.IOException;

public class RegActivity extends Activity {
    private DatabaseHelper mDBHelper;
    private SQLiteDatabase mDb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
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
        Button btn_r = findViewById(R.id.button_reg);
        TextView tx1 = findViewById(R.id.textViewLogin);
        EditText editTextLogin = findViewById(R.id.Login);
        EditText editTextPassword = findViewById(R.id.Password);
        EditText editTextName = findViewById(R.id.Name);
        EditText editTextMail = findViewById(R.id.Mail);
        EditText editTextNumber = findViewById(R.id.Number);

        btn_r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getLogin = editTextLogin.getText().toString();
                String getPass = editTextPassword.getText().toString();
                String getName = editTextName.getText().toString();
                String getNumber = editTextNumber.getText().toString();
                String getMail = editTextMail.getText().toString();

                if (TextUtils.isEmpty(getLogin) || TextUtils.isEmpty(getPass)) {
                    tx1.setText("123");
                } else {
                    if (editTextPassword != editTextLogin) {
                        Boolean chekuser = mDBHelper.checkuser(getLogin);
                        if (chekuser == false) {
                            Boolean insert = mDBHelper.insertData(getLogin, getPass, getName, getMail, getNumber);
                            if (insert == true) {
                                Intent intent = new Intent(RegActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        }
                    }
                }
            }
        });
    }

    public void OnClickBackToMain(View view) {
        Intent intent = new Intent(RegActivity.this, MainActivity.class);
        startActivity(intent);
    }
}