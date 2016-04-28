package com.ndtv.consult;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.ndtv.consult.util.AppPreferences;

/**
 * Created by arindamnath on 26/04/16.
 */
public class SplashActivity extends AppCompatActivity implements View.OnClickListener {

    private AppPreferences appPreferences;
    private Button splashSignIn, splashSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        appPreferences = new AppPreferences(this);

        splashSignIn = (Button) findViewById(R.id.splash_sign_in_btn);
        splashSignUp = (Button) findViewById(R.id.splash_sign_up_btn);

        splashSignIn.setOnClickListener(this);
        splashSignUp.setOnClickListener(this);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                if (appPreferences.isLoggedIn()) {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                } else {
                    findViewById(R.id.splash_promo_container).setVisibility(View.VISIBLE);
                }
            }
        }, 2500);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.splash_sign_in_btn:
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
            case R.id.splash_sign_up_btn:
                startActivity(new Intent(this, SignUpActivity.class));
                finish();
                break;
        }
    }
}
