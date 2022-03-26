package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class AddressActivity extends AppCompatActivity {
    SQLiteDatabase mDataBase;
    DatabaseHelper DB;

    String[] address = {"г. Москва, Академика Королева, 12А, АЗС «Роснефть»", "г. Москва, Волоколамское шоссе, 79, АЗС «Роснефть»",
            "г. Москва, Дмитровское шоссе, 91А", "г. Москва, Куликовская, 20, стр. 1", "г. Москва, Ленинский проспект, 137А, АЗС «BP»"};
    String[] arrstreet;
    String[] arrhouse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        Spinner spinner = findViewById(R.id.spinner);
        SQLiteDatabase db = new DatabaseHelper(this).getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT Улица FROM Адрес", null);
        arrstreet = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int i = 0; i < arrstreet.length; i++) {
            arrstreet[i] = cursor.getString(cursor.getColumnIndex("Улица"));
            cursor.moveToNext();

        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrstreet);
        spinner.setAdapter(adapter);
    }


    public void OnClickNext(View view) {
        Spinner spinner = findViewById(R.id.spinner);
        String getStreet = spinner.getSelectedItem().toString();
        Intent intent = new Intent(AddressActivity.this, Date.class);
        Bundle extras = getIntent().getExtras();
        String usergos = extras.getString("gos");
        String userlog = extras.getString("log3");
        String usluga = extras.getString("uslug2");
        String cst = extras.getString("cost2");
        intent.putExtra("cost3", cst);
        intent.putExtra("uslug3", usluga);
        intent.putExtra("street", getStreet);
        intent.putExtra("gos2", usergos);
        intent.putExtra("log4", userlog);
        startActivity(intent);
    }

    public void OnClickBackToBasket(View view) {
        Intent intent = new Intent(AddressActivity.this, BasketActivity.class);
        startActivity(intent);
    }
}