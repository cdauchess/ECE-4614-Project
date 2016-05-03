package com.example.charlie.a4614app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText PhoneNum;
    EditText MessageToSend;

    //Macro Values
    String NameMacro1 = "Macro1";
    String NameMacro2 = "Macro2";
    String NameMacro3 = "Macro3";
    String TxtMacro1 =  "Test1";
    String TxtMacro2 = "Test2";
    String TxtMacro3 = "Test3";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        PhoneNum = (EditText) findViewById(R.id.EnterNum);
        MessageToSend = (EditText) findViewById(R.id.Message);
        Button Macro1 = (Button)findViewById(R.id.Macro1);
        Button Macro2 = (Button)findViewById(R.id.Macro2);
        Button Macro3 = (Button)findViewById(R.id.Macro3);

            SharedPreferences Macros = getSharedPreferences("Macros", MODE_PRIVATE);
            NameMacro1 = Macros.getString("NameMacro1","Macro 1");
            NameMacro2 = Macros.getString("NameMacro2", "Macro 2");
            NameMacro3 = Macros.getString("NameMacro3", "Macro 3");
            TxtMacro1 = Macros.getString("TxtMacro1", "Not Set1");
            TxtMacro2 = Macros.getString("TxtMacro2", "Not Set2");
            TxtMacro3 = Macros.getString("TxtMacro3", "Not Set3");
            Macro1.setText(NameMacro1);
            Macro2.setText(NameMacro2);
            Macro3.setText(NameMacro3);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    public void Send(View view) {

        Log.i("Send SMS", "");
        String phoneNo = PhoneNum.getText().toString();
        String message = MessageToSend.getText().toString();

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, message, null, null);
            Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_SHORT).show();
        }

        catch (Exception e) {
            //Deal with storing messages here
            Toast.makeText(getApplicationContext(), "SMS failed, please try again.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    public void MacroSettings(View view){
        Intent macro = new Intent(MainActivity.this, MacroSettings.class);
        startActivity(macro);
    }

    public void Macro1(View view){
        MessageToSend.setText(TxtMacro1);
        Send(view);

    }
    public void Macro2(View view){
        MessageToSend.setText(TxtMacro2);
        Send(view);
    }
    public void Macro3(View view){
        MessageToSend.setText(TxtMacro3);
        Send(view);

    }

}
