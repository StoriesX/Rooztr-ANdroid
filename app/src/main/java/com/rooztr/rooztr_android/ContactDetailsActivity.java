package com.rooztr.rooztr_android;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;



/**
 * Created by anandsuresh on 7/21/16.
 */
public class ContactDetailsActivity extends Activity {

    public ContactDetailsActivity() {
        super();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_details);


    }

    @Override
    protected void onStart() {
        super.onStart();


        Intent intent = getIntent();
        String id = intent.getStringExtra("data");
        Log.d("into activity",id);
        Bundle bundle = new Bundle();
        bundle.putString("id",id);

        ContactDetails contactDetails = new ContactDetails();
        contactDetails.setArguments(bundle);
        getFragmentManager().beginTransaction().add(R.id.detailscontainer,contactDetails).commit();
    }


}
