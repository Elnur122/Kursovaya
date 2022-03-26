package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;


public class MainActivity extends Activity {

    DatabaseHelper DB;
    SQLiteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DB = new DatabaseHelper(this);
        db = DB.getWritableDatabase();
        Bundle arguments = getIntent().getExtras();


        Button btn_s = findViewById(R.id.button_sig);
        Button btnpro = findViewById(R.id.button_pro);
        TextView tx1 = findViewById(R.id.textViewLogin);
        EditText editTextLogin = findViewById(R.id.Login);
        EditText editTextPassword = findViewById(R.id.Password);
        TextView cor = findViewById(R.id.textViewCor);
        TextView count = findViewById(R.id.textViewCount);
        TextView tvCap = findViewById(R.id.textViewTvCaptcha);
        EditText editTextCaptcha = findViewById(R.id.Captcha);
        String[] SC = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        final int[] a = {0};


        btn_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ss = Integer.toString(a[0]);
                count.setText(ss);
                String getLogin = editTextLogin.getText().toString();
                String getPass = editTextPassword.getText().toString();
                if (TextUtils.isEmpty(getLogin) || TextUtils.isEmpty(getPass)) {
                    cor.setVisibility(View.VISIBLE);
                    count.setVisibility(View.VISIBLE);
                    a[0]++;
                    String sa = Integer.toString(a[0]);
                    count.setText(sa);
                    if (a[0] >= 3) {
                        btnpro.setVisibility(View.VISIBLE);
                        tvCap.setVisibility(View.VISIBLE);
                        editTextCaptcha.setVisibility(View.VISIBLE);
                        tvCap.setText("HRQLD");
                        editTextLogin.setFocusable(false);
                        editTextLogin.setClickable(false);
                    }
                } else {
                    Boolean cup = DB.checkloginpass(getLogin, getPass);
                    if (cup == true) {
                        Intent intent = new Intent(MainActivity.this, SmActivity.class);
                        String ext = editTextLogin.getText().toString();
                        intent.putExtra("log", ext);
                        startActivity(intent);
                    } else {
                        cor.setVisibility(View.VISIBLE);
                        count.setVisibility(View.VISIBLE);
                        a[0]++;
                        String sa = Integer.toString(a[0]);
                        count.setText(sa);
                        if (a[0] == 3) {
                            tvCap.setVisibility(View.VISIBLE);
                            btnpro.setVisibility(View.VISIBLE);
                            editTextCaptcha.setVisibility(View.VISIBLE);
                            tvCap.setText("HRQLD");
                            editTextLogin.setFocusable(false);
                            editTextLogin.setClickable(false);
                        }
                    }
                }
            }
        });
    }


    public void OnClickReg(View view) {
        Intent intent = new Intent(MainActivity.this, RegActivity.class);
        startActivity(intent);
    }

    public void OnClickPro(View view) {
        Button btn_s = findViewById(R.id.button_sig);
        Button btnpro = findViewById(R.id.button_pro);
        TextView tx1 = findViewById(R.id.textViewLogin);
        EditText editTextLogin = findViewById(R.id.Login);
        EditText editTextPassword = findViewById(R.id.Password);
        TextView cor = findViewById(R.id.textViewCor);
        TextView count = findViewById(R.id.textViewCount);
        TextView tvCap = findViewById(R.id.textViewTvCaptcha);
        EditText editTextCaptcha = findViewById(R.id.Captcha);
        if (tvCap.getText().toString() == editTextCaptcha.getText().toString()) {
            cor.setVisibility(View.INVISIBLE);
            count.setVisibility(View.INVISIBLE);
            tvCap.setVisibility(View.INVISIBLE);
            editTextCaptcha.setVisibility(View.INVISIBLE);
            editTextLogin.setFocusable(true);
            editTextLogin.setClickable(true);
            count.setText("0");
        } else {
            btnpro.setVisibility(View.INVISIBLE);
            cor.setVisibility(View.INVISIBLE);
            count.setVisibility(View.INVISIBLE);
            tvCap.setVisibility(View.INVISIBLE);
            editTextCaptcha.setVisibility(View.INVISIBLE);
            editTextLogin.setFocusable(true);
            editTextLogin.setClickable(true);
            count.setText("0");
        }
    }
}