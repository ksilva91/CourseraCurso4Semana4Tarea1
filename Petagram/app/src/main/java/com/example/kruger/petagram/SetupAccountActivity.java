package com.example.kruger.petagram;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.kruger.petagram.presentador.ISetupAccountPresenter;
import com.example.kruger.petagram.presentador.SetupAccountPresenterImpl;

public class SetupAccountActivity extends AppCompatActivity {

    private Button btn_save_account;
    private TextInputLayout til_user;
    private Toolbar toolbar;
    private ISetupAccountPresenter iSetupAccountPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_account);
        btn_save_account = findViewById(R.id.btn_save_account);
        til_user = findViewById(R.id.til_user);
        toolbar = findViewById(R.id.action_bar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        iSetupAccountPresenter = new SetupAccountPresenterImpl(SetupAccountActivity.this);

        btn_save_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = til_user.getEditText().getText().toString().trim();
                if(username.equals("")){
                    Toast.makeText(SetupAccountActivity.this, "El username no puede quedar vacío", Toast.LENGTH_SHORT).show();
                }else {
                    iSetupAccountPresenter.getUsername(username);
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SetupAccountActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
