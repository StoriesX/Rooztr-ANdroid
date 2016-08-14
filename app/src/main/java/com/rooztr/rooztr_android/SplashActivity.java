package com.rooztr.rooztr_android;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.rooztr.helper.RequestHandler;



/**
 * Created by anandsuresh on 8/13/16.
 */
public class SplashActivity extends Activity {


    private static int SPLASH_TIME_OUT = 3000;
    private Button send,login;
    private EditText phoneNumber,code;
    private static String phoneNo = null,messageCode,token;
    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash_layout);

        SharedPreferences prefs = getApplicationContext().getSharedPreferences("tokenstorage", 0);
        token = prefs.getString("token","");
        phoneNo = prefs.getString("Phone Number","");
        if(token.length() != 0 && phoneNo.length() != 0) {
            try {
                if(RequestHandler.validate(phoneNo, token)) {
                   Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                   startActivity(intent);
                }
            }catch(Exception e) {

            }
        }


        send = (Button) findViewById(R.id.b1);
        phoneNumber = (EditText) findViewById(R.id.p2);
        code = (EditText) findViewById(R.id.p3);
        relativeLayout = (RelativeLayout) findViewById(R.id.relative);
        login = (Button) findViewById(R.id.login);

        send.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneNo = phoneNumber.getText().toString();
                send.setText("RESEND");
                relativeLayout.setVisibility(View.VISIBLE);
                try {

                    RequestHandler.register(phoneNo);

                }catch (Exception e) {

                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                messageCode = code.getText().toString();
                try {
                    token = RequestHandler.login(phoneNo,messageCode);
                    SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("tokenstorage",0);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("Phone Number",phoneNo);
                    editor.putString("token",token);
                    editor.apply();

                }catch (Exception e) {

                }
            }
        });

    }
}
