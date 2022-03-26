package com.example.myapplication;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

public class ProfileActivity extends Activity {
    DatabaseHelper DB;
    SQLiteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profile);

        DB = new DatabaseHelper(this);
        db = DB.getWritableDatabase();

        EditText editTextName = findViewById(R.id.Name);
        EditText editTextTel = findViewById(R.id.Tel);
        EditText editTextMail = findViewById(R.id.Email);
        EditText editTextLogin = findViewById(R.id.Login);
        Button btn_up = findViewById(R.id.button_ready);

        btn_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getName = editTextName.getText().toString();
                String getTel = editTextTel.getText().toString();
                String getMail = editTextMail.getText().toString();
                String getLogin = editTextLogin.getText().toString();

                if (TextUtils.isEmpty(getMail) || TextUtils.isEmpty(getName) || TextUtils.isEmpty(getTel)) {
                    int i = 1;
                } else {
                    Bundle extras = getIntent().getExtras();
                    String userlog2 = extras.getString("log2");
                    ContentValues cv = new ContentValues();
                    cv.put("Имя", getName);
                    cv.put("Номер_Телефона", getTel);
                    cv.put("Почта", getMail);
                    cv.put("Логин", getLogin);
                    if (!TextUtils.isEmpty(getMail) || !TextUtils.isEmpty(getName) || !TextUtils.isEmpty(getTel)) {
                        db.update("Пользователь", cv, "Логин = '" + userlog2 + "'", null);
                        Intent intent = new Intent(ProfileActivity.this, SmActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });
    }

    public void OnClickBackToSm(View view) {
        Intent intent = new Intent(ProfileActivity.this, SmActivity.class);
        startActivity(intent);
    }
}
