package com.example.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ConfirmActivity extends AppCompatActivity {
    DatabaseHelper DB;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        ConfirmActivity confirmActivity = ConfirmActivity.this;
        DB = new DatabaseHelper(this);
        db = DB.getWritableDatabase();
        TextView textViewName = findViewById(R.id.Name);
        Button btn_confirm = findViewById(R.id.button_ready);
        confirmActivity.Select();

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup radioGroup = findViewById(R.id.radioGroup);
                Bundle extras = getIntent().getExtras();
                String usergos = extras.getString("gos3");
                String userstreet = extras.getString("street2");
                String userlog = extras.getString("log5");
                String selusluga = extras.getString("uslug4");
                String concost = extras.getString("cost4");
                int vidoplID = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButtonSel = findViewById(vidoplID);
                String rad = radioButtonSel.getText().toString();
                Cursor cursor = db.rawQuery("Select id_Пользователя from Пользователь where Логин = '" + userlog + "'", null);
                Cursor cursor2 = db.rawQuery("Select id_Автомобиля from Автомобиль where Гос_Номер = '" + usergos + "'", null);
                Cursor cursor3 = db.rawQuery("Select id_Услуги from Справочник_Услуг where Название_Услуги = '" + selusluga + "'", null);
                Cursor cursor4 = db.rawQuery("Select id_Адреса from Адрес where Улица = '" + userstreet + "'", null);
                ContentValues values = new ContentValues();
                if (cursor.moveToFirst() && cursor2.moveToFirst() && cursor3.moveToFirst() && cursor4.moveToFirst()) {
                    values.put("id_Пользователя", cursor.getInt(0));
                    values.put("id_Автомобиля", cursor2.getInt(0));
                    values.put("id_Услуги", cursor3.getInt(0));
                    values.put("id_Адреса", cursor4.getInt(0));
                    values.put("Вид_Оплаты", rad);
                    values.put("Итоговая_Стоимость", concost);
                    db.insert("Заказ", null, values);
                }
            }
        });

    }


    public void Select() {
        TextView textViewName = findViewById(R.id.Name);
        TextView textViewNum = findViewById(R.id.Tel);
        TextView textViewProd = findViewById(R.id.Prod);
        TextView textViewDate = findViewById(R.id.Date);
        TextView textViewCost = findViewById(R.id.Cost);
        Bundle extras = getIntent().getExtras();
        String userlog2 = extras.getString("log5");
        String userstreet = extras.getString("street2");
        String selusluga = extras.getString("uslug4");
        String cst = extras.getString("cost4");
        Cursor cursor = db.rawQuery("Select Имя from Пользователь where Логин = '" + userlog2 + "'", null);
        Cursor cursor2 = db.rawQuery("Select Номер_Телефона from Пользователь where Логин = '" + userlog2 + "'", null);
        Cursor cursor3 = db.rawQuery("Select Улица from Адрес where Улица = '" + userstreet + "'", null);
        Cursor cursor4 = db.rawQuery("Select Название_Услуги from Справочник_Услуг where Название_Услуги = '" + selusluga + "'", null);
        if (cursor != null && cursor.moveToFirst() && cursor2 != null && cursor2.moveToFirst() && cursor3 != null && cursor3.moveToFirst() && cursor4 != null && cursor4.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndex("Имя"));
            String num = cursor2.getString(cursor2.getColumnIndex("Номер_Телефона"));
            String ul = cursor3.getString(cursor3.getColumnIndex("Улица"));
            String usl = cursor4.getString(cursor4.getColumnIndex("Название_Услуги"));
            textViewName.setText(name);
            textViewNum.setText(num);
            textViewDate.setText(ul);
            textViewProd.setText(usl);
            textViewCost.setText(cst);
            cursor.close();
            cursor2.close();
            cursor3.close();
            cursor4.close();
        }
    }


    public void OnClickBackToDate(View view) {
        Intent intent = new Intent(ConfirmActivity.this, Date.class);
        startActivity(intent);
    }
}