package com.ndtv.consult;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ndtv.consult.util.AppPreferences;

/**
 * Created by arindamnath on 26/04/16.
 */
public class SignUpActivity extends AppCompatActivity {

    private EditText signUpName, signUpEmail, signUpMobile, signUpPassword;
    private Button signUpButton;

    private AppPreferences appPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        appPreferences = new AppPreferences(this);

        signUpName = (EditText) findViewById(R.id.sign_up_name);
        signUpEmail = (EditText) findViewById(R.id.sign_up_email);
        signUpMobile = (EditText) findViewById(R.id.sign_up_phone);
        signUpPassword = (EditText) findViewById(R.id.sign_up_password);
        signUpButton = (Button) findViewById(R.id.sign_up_button);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                    finish();
                }
            }
        });

        findViewById(R.id.sign_in_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, SplashActivity.class));
        finish();
    }

    public boolean validate() {
        boolean valid = true;
        String name = signUpName.getText().toString();
        String email = signUpEmail.getText().toString();
        String phone = signUpMobile.getText().toString();
        String password = signUpPassword.getText().toString();

        if (name.isEmpty()) {
            signUpName.setError("enter your full name");
            valid = false;
        } else {
            signUpName.setError(null);
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            signUpEmail.setError("enter a valid email address");
            valid = false;
        } else {
            signUpEmail.setError(null);
        }

        if (phone.isEmpty() || !Patterns.PHONE.matcher(phone).matches()) {
            signUpMobile.setError("enter a valid mobile number");
            valid = false;
        } else {
            signUpMobile.setError(null);
        }

        if (password.isEmpty()) {
            signUpPassword.setError("password cannot be empty");
            valid = false;
        } else {
            signUpPassword.setError(null);
        }
        return valid;
    }
}
