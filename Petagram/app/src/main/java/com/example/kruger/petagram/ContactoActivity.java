package com.example.kruger.petagram;

import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.kruger.petagram.mail.Mail;

import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;

public class ContactoActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextInputLayout til_name;
    private TextInputLayout til_email;
    private TextInputLayout til_message;
    private Button bt_send_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);
        toolbar = findViewById(R.id.action_bar);
        til_name = findViewById(R.id.til_name);
        til_email = findViewById(R.id.til_email);
        til_message = findViewById(R.id.til_message);
        bt_send_message = findViewById(R.id.bt_send_message);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        bt_send_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateForm(til_name.getEditText().getText().toString().trim(),
                        til_email.getEditText().getText().toString().trim(),
                        til_message.getEditText().getText().toString().trim(),
                        view)){
                    sendMail(view);
                }
            }
        });
    }

    private void sendMail(View view){
        String[] recipients = { til_email.getEditText().getText().toString().trim() };
        SendEmailAsyncTask sendEmailAsyncTask = new SendEmailAsyncTask();
        sendEmailAsyncTask.view = view;
        sendEmailAsyncTask.mail = new Mail("petagramks@gmail.com", "petagramks123456");
        sendEmailAsyncTask.mail.set_from("petagramks@gmail.com");
        sendEmailAsyncTask.mail.setBody(til_message.getEditText().getText().toString().trim());
        sendEmailAsyncTask.mail.set_to(recipients);
        sendEmailAsyncTask.mail.set_subject(til_name.getEditText().getText().toString().trim());
        sendEmailAsyncTask.execute();
    }

    private boolean validateForm(String name, String email, String message, View view){
        if(name.equals("")){
            Snackbar.make(view, getString(R.string.validate_name), Snackbar.LENGTH_SHORT).show();
            return false;
        } else if(email.equals("")){
            Snackbar.make(view, getString(R.string.validate_email), Snackbar.LENGTH_SHORT).show();
            return false;
        } else if(message.equals("")){
            Snackbar.make(view, getString(R.string.validate_message), Snackbar.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void clearEditText(){
        til_name.getEditText().setText("");
        til_email.getEditText().setText("");
        til_message.getEditText().setText("");
    }

    private class SendEmailAsyncTask extends AsyncTask<Void, Void, Boolean> {

        View view;
        Mail mail;

        public SendEmailAsyncTask() {
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                if (mail.send()) {
                    Snackbar.make(view, getString(R.string.email_sent), Snackbar.LENGTH_LONG).show();
                }
                else {
                    Snackbar.make(view, getString(R.string.email_send_failed), Snackbar.LENGTH_LONG).show();
                }
                return true;
            }
            catch (AuthenticationFailedException error) {
                Snackbar.make(view, getString(R.string.authentication_failed), Snackbar.LENGTH_LONG).show();
                return false;
            }
            catch (MessagingException error) {
                Snackbar.make(view, getString(R.string.email_send_failed), Snackbar.LENGTH_LONG).show();
                return false;
            }
            catch (Exception error) {
                Snackbar.make(view, getString(R.string.unexpected_error), Snackbar.LENGTH_LONG).show();
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            clearEditText();
        }
    }
}
