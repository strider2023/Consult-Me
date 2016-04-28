package com.ndtv.consult;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ndtv.consult.dao.PatientData;
import com.ndtv.consult.threads.LoaderUser;
import com.squareup.picasso.Picasso;

public class UserProfileActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<PatientData>{

    private Bundle queryData;
    private TextView gender, age, address, bloodGroup, email, phone;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        image = (ImageView) findViewById(R.id.user_image);
        gender = (TextView) findViewById(R.id.user_gender);
        age = (TextView) findViewById(R.id.user_age);
        address = (TextView) findViewById(R.id.user_address);
        bloodGroup = (TextView) findViewById(R.id.user_blood_group);
        email = (TextView) findViewById(R.id.user_email);
        phone  = (TextView) findViewById(R.id.user_mobile);

        if(getIntent().getStringExtra("name") != null) {
            getSupportActionBar().setTitle(getIntent().getStringExtra("name"));
        }

        queryData = new Bundle();
        queryData.putString("query", "");
        getSupportLoaderManager().initLoader(3, queryData, this).forceLoad();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<PatientData> onCreateLoader(int id, Bundle args) {
        return new LoaderUser(this, args);
    }

    @Override
    public void onLoadFinished(Loader<PatientData> loader, PatientData data) {
        if(data != null) {
            gender.setText(data.getGender());
            age.setText(data.getAge() + " Yrs");
            address.setText(data.getAddress());
            bloodGroup.setText(data.getBloodGroup());
            email.setText(data.getEmail());
            phone.setText(data.getMobile());
            Picasso.with(this)
                    .load(data.getImage())
                    .into(image);
        }
    }

    @Override
    public void onLoaderReset(Loader<PatientData> loader) {

    }
}
