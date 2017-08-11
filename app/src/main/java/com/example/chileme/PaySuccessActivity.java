package com.example.chileme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class PaySuccessActivity extends AppCompatActivity {
    private ImageView paysuc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_success);
        paysuc =(ImageView) findViewById(R.id.paysuc);
        paysuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in1 = new Intent(PaySuccessActivity.this, MainFragment.class);
                startActivity(in1);

            }
        });
    }
}

