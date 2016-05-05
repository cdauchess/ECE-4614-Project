package com.example.charlie.a4614app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.util.Log;
import android.net.NetworkInfo;
import android.net.ConnectivityManager;
import android.widget.Toast;
import android.content.Context;


public class ConnectionReceiver extends BroadcastReceiver {
    boolean connectionStatus;
    @Override
    public void onReceive( Context context, Intent intent )
    {
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        connectionStatus = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        MainActivity.IsConnected = connectionStatus;

        if(connectionStatus == true){
            Toast.makeText(context,"Connection is active.", Toast.LENGTH_SHORT).show();
            SendFailedMessages(context);
        }
        else{
            Toast.makeText(context,"There is no Connection.", Toast.LENGTH_SHORT).show();
        }

    }
    public void SendFailedMessages(Context context){
        if(MainActivity.FailCount == 0){
            Toast.makeText(context, "No Failed Messages to Send", Toast.LENGTH_SHORT).show();
        }
        else {
            for (int i = 0; i < MainActivity.FailCount; i++) {
                SendSMS(MainActivity.FailedNumber[i], MainActivity.FailedMessage[i]);
            }
            MainActivity.FailCount = 0; //Reset the count
            Toast.makeText(context, "Failed Messages Sent", Toast.LENGTH_SHORT).show();
        }
    }

    public void SendSMS(String Num, String Message){
        Log.i("Send SMS", "");

            try {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(Num, null, Message, null, null);

            } catch (Exception e) {

                e.printStackTrace();
            }


    }
}


