package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class SmActivity extends Activity {
    View.OnClickListener radiobtnl = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            TextView textViewCostTv = findViewById(R.id.CostTv);
            TextView textViewCostNumberTv = findViewById(R.id.CostNumberTv);
            TextView textViewRubTv = findViewById(R.id.RubTv);
            RadioButton rb = (RadioButton) v;
            int cost1 = 700;
            int cost2 = 1250;
            int cost3 = 890;
            int cost4 = 1000;
            int n = 0;
            switch (v.getId()) {
                case R.id.rb1:
                    textViewCostNumberTv.setVisibility(View.VISIBLE);
                    textViewCostTv.setVisibility(View.VISIBLE);
                    textViewRubTv.setVisibility(View.VISIBLE);
                    String s1 = Integer.toString(cost1);
                    textViewCostNumberTv.setText(s1);
                    break;
                case R.id.rb2:
                    textViewCostNumberTv.setVisibility(View.VISIBLE);
                    textViewCostTv.setVisibility(View.VISIBLE);
                    textViewRubTv.setVisibility(View.VISIBLE);
                    String s2 = Integer.toString(cost2);
                    textViewCostNumberTv.setText(s2);
                    break;
                case R.id.rb3:
                    textViewCostNumberTv.setVisibility(View.VISIBLE);
                    textViewCostTv.setVisibility(View.VISIBLE);
                    textViewRubTv.setVisibility(View.VISIBLE);
                    String s3 = Integer.toString(cost3);
                    textViewCostNumberTv.setText(s3);
                    break;
                case R.id.rb4:
                    textViewCostNumberTv.setVisibility(View.VISIBLE);
                    textViewCostTv.setVisibility(View.VISIBLE);
                    textViewRubTv.setVisibility(View.VISIBLE);
                    String s4 = Integer.toString(cost4);
                    textViewCostNumberTv.setText(s4);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sm);
        RadioButton radioButton1 = findViewById(R.id.rb1);
        radioButton1.setOnClickListener(radiobtnl);
        RadioButton radioButton2 = findViewById(R.id.rb2);
        radioButton2.setOnClickListener(radiobtnl);
        RadioButton radioButton3 = findViewById(R.id.rb3);
        radioButton3.setOnClickListener(radiobtnl);
        RadioButton radioButton4 = findViewById(R.id.rb4);
        radioButton4.setOnClickListener(radiobtnl);

    }

    public void OnClickProfile(View view) {
        Intent intent = new Intent(SmActivity.this, ProfileActivity.class);
        Bundle extras = getIntent().getExtras();
        String userlog = extras.getString("log");
        intent.putExtra("log2", userlog);
        startActivity(intent);
    }

    public void OnClickCatalog(View view) {
        Intent intent = new Intent(SmActivity.this, BasketActivity.class);
        RadioGroup rg = findViewById(R.id.rg);
        TextView textViewCostNumberTv = findViewById(R.id.CostNumberTv);
        Bundle extras = getIntent().getExtras();
        int uslID = rg.getCheckedRadioButtonId();
        RadioButton radioButtonSel = findViewById(uslID);
        String usluga = radioButtonSel.getText().toString();
        String cst = textViewCostNumberTv.getText().toString();
        String userlog = extras.getString("log");
        intent.putExtra("log2", userlog);
        intent.putExtra("cost", cst);
        intent.putExtra("uslug", usluga);
        startActivity(intent);
    }

    public void OnClickBackToMain(View view) {
        Intent intent = new Intent(SmActivity.this, MainActivity.class);
        startActivity(intent);
    }

}
