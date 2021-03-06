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

    static int MaxFails = 30;

    EditText PhoneNum;
    EditText MessageToSend;

    //Macro Values
    String NameMacro1 = "Macro1";
    String NameMacro2 = "Macro2";
    String NameMacro3 = "Macro3";
    String TxtMacro1 =  "Test1";
    String TxtMacro2 = "Test2";
    String TxtMacro3 = "Test3";
    String Num1 = "1111111";
    String Num2 = "1111111";
    String Num3 = "1111111";
    public static boolean IsConnected = true;

    public static String[] FailedMessage = new String[MaxFails];
    public static String[] FailedNumber = new String[MaxFails];
    public static int FailCount = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent connectionIntent = new Intent(this, ConnectionReceiver.class);
        startService(connectionIntent);


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
            Num1 = Macros.getString("Num1", "1111111");
            Num2 = Macros.getString("Num2", "1111111");
            Num3 = Macros.getString("Num3", "1111111");


            Macro1.setText(NameMacro1);
            Macro2.setText(NameMacro2);
            Macro3.setText(NameMacro3);
    }
    public void Send(View view) { //Respond to press of the Send Button
            String phoneNo = PhoneNum.getText().toString();
            String message = MessageToSend.getText().toString();
            SendSMS(phoneNo, message);


    }
    public void SendSMS(String Num, String Message){
        Log.i("Send SMS", "");
        if(IsConnected == true) {
            try {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(Num, null, Message, null, null);
                Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Input a message and number", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
        else{
            //Deal with storing messages here
            FailedMessage[FailCount] = Message;
            FailedNumber[FailCount] = Num;
            FailCount++;
            Toast.makeText(getApplicationContext(), "SMS failed, will send when connection is available", Toast.LENGTH_SHORT).show();
        }
    }

    public void MacroSettings(View view){
        Intent macro = new Intent(MainActivity.this, MacroSettings.class);
        startActivity(macro);
    }

    public void Macro1(View view){
        SendSMS(Num1,TxtMacro1);
    }

    public void Macro2(View view){
        SendSMS(Num2,TxtMacro2);
    }

    public void Macro3(View view){
        SendSMS(Num3,TxtMacro3);
    }

    public void SendFailedMessages(){
        for(int i = 0; i<FailCount;i++){
            SendSMS(FailedNumber[i],FailedMessage[i]);
        }
        FailCount = 0; //Reset the count
    }
}
