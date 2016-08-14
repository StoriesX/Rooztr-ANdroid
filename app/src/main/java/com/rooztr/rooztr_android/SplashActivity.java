package com.rooztr.rooztr_android;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.rooztr.helper.RequestHandler;



/**
 * Created by anandsuresh on 8/13/16.
 */
public class SplashActivity extends Activity {


    private static int SPLASH_TIME_OUT = 3000;
    private Button send;
    private EditText phoneNumber,code;
    private LinearLayout linearLayout;
    private static String phoneNo = null;
    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash_layout);

        send = (Button) findViewById(R.id.b1);
        phoneNumber = (EditText) findViewById(R.id.p2);
        linearLayout = (LinearLayout) findViewById(R.id.l1);
        code = (EditText) findViewById(R.id.p3);
        relativeLayout = (RelativeLayout) findViewById(R.id.relative);


        phoneNo = phoneNumber.getText().toString();

        send.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send.setText("RESEND");
                relativeLayout.setVisibility(View.VISIBLE);
                RequestHandler.register(phoneNo);
            }
        });



    }
}
