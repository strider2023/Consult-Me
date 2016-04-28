package com.ndtv.consult;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ndtv.consult.util.AppPreferences;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    private Button signInButton, recoverPasswordButton;
    private EditText signInEmail, signInPassword, forgotPasswordEmail;
    private TextView forgotPassworButton;

    private AppPreferences appPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        appPreferences = new AppPreferences(this);

        signInButton = (Button) findViewById(R.id.sign_in_button);
        forgotPassworButton = (TextView) findViewById(R.id.forgot_password_button);
        recoverPasswordButton = (Button) findViewById(R.id.recover_password_button);
        signInEmail = (EditText) findViewById(R.id.sign_in_email);
        signInPassword = (EditText) findViewById(R.id.sign_in_password);
        forgotPasswordEmail = (EditText) findViewById(R.id.forgot_password_email);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    if(signInEmail.getText().toString().equals("doc@example.com")) {
                        appPreferences.setDoctorLoggedIn();
                        appPreferences.setLoggedIn();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    } else if(signInEmail.getText().toString().equals("user@example.com")) {
                        appPreferences.setLoggedIn();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    } else {
                        Toast.makeText(v.getContext(), "Invalid Email Id", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        findViewById(R.id.sign_up_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
                finish();
            }
        });

        forgotPassworButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.sign_in_container).setVisibility(View.GONE);
                findViewById(R.id.forgot_password_container).setVisibility(View.VISIBLE);
            }
        });

        recoverPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateRecoverPassword()) {
                    findViewById(R.id.sign_in_container).setVisibility(View.VISIBLE);
                    findViewById(R.id.forgot_password_container).setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(findViewById(R.id.sign_in_container).getVisibility() == View.VISIBLE) {
            startActivity(new Intent(this, SplashActivity.class));
            finish();
        } else {
            findViewById(R.id.sign_in_container).setVisibility(View.VISIBLE);
            findViewById(R.id.forgot_password_container).setVisibility(View.GONE);
        }
    }

    public boolean validate() {
        boolean valid = true;
        String email = signInEmail.getText().toString();
        String password = signInPassword.getText().toString();
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            signInEmail.setError("enter a valid email address");
            valid = false;
        } else {
            signInEmail.setError(null);
        }

        if (password.isEmpty()) {
            signInPassword.setError("password cannot be empty");
            valid = false;
        } else {
            signInPassword.setError(null);
        }
        return valid;
    }

    public boolean validateRecoverPassword() {
        boolean valid = true;
        String email = forgotPasswordEmail.getText().toString();
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            forgotPasswordEmail.setError("enter a valid email address");
            valid = false;
        } else {
            forgotPasswordEmail.setError(null);
        }
        return valid;
    }
}

