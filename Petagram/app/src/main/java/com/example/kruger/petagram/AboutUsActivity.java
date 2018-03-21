package com.example.kruger.petagram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class AboutUsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView tv_names;
    private TextView tv_lastNames;
    private TextView tv_age;
    private TextView tv_email;
    private TextView tv_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        toolbar = findViewById(R.id.action_bar);
        tv_names = findViewById(R.id.tv_names);
        tv_lastNames = findViewById(R.id.tv_last_names);
        tv_age = findViewById(R.id.tv_age);
        tv_email = findViewById(R.id.tv_email);
        tv_phone = findViewById(R.id.tv_phone);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tv_names.setText("Kevin Ricardo");
        tv_lastNames.setText("Silva Chávez");
        tv_age.setText("26");
        tv_email.setText("kevinsilvach@gmail.com");
        tv_phone.setText("0982595054");
    }
}
