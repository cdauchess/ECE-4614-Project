package com.example.charlie.a4614app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MacroSettings extends AppCompatActivity {
    Button SaveBtn;
    EditText Name1;
    EditText Name2;
    EditText Name3;
    EditText Text1;
    EditText Text2;
    EditText Text3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_macro_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SharedPreferences Macros = getSharedPreferences("Macros", MODE_PRIVATE);
        String NameMacro1 = Macros.getString("NameMacro1", "");
        String NameMacro2 = Macros.getString("NameMacro2", "");
        String NameMacro3 = Macros.getString("NameMacro3", "");
        String TxtMacro1 = Macros.getString("TxtMacro1", "");
        String TxtMacro2 = Macros.getString("TxtMacro2", "");
        String TxtMacro3 = Macros.getString("TxtMacro3", "");

        SaveBtn = (Button) findViewById(R.id.SaveMacroBtn);
        Name1 = (EditText) findViewById(R.id.Macro1Name);
        Name2 = (EditText) findViewById(R.id.Macro2Name);
        Name3 = (EditText) findViewById(R.id.Macro3Name);
        Text1 = (EditText) findViewById(R.id.Macro1Txt);
        Text2 = (EditText) findViewById(R.id.Macro2Txt);
        Text3 = (EditText) findViewById(R.id.Macro3Txt);


        //Check if values exist, if they do set them to display.
        if(NameMacro1 != ""){
            Name1.setText(NameMacro1);
        }
        if(NameMacro2 != ""){
            Name2.setText(NameMacro2);
        }
        if(NameMacro3 != ""){
            Name3.setText(NameMacro3);
        }
        if(TxtMacro1 != ""){
            Text1.setText(TxtMacro1);
        }
        if(TxtMacro2 != ""){
            Text2.setText(TxtMacro2);
        }
        if(TxtMacro3 != ""){
            Text3.setText(TxtMacro3);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void SaveMacros(View view){
        //Put all of the values into a string
        String NameMacro1 = Name1.getText().toString();
        String NameMacro2 = Name2.getText().toString();
        String NameMacro3 = Name3.getText().toString();
        String TxtMacro1 = Text1.getText().toString();
        String TxtMacro2 = Text2.getText().toString();
        String TxtMacro3 = Text3.getText().toString();

        SharedPreferences Macros = getSharedPreferences("Macros", MODE_PRIVATE);
        SharedPreferences.Editor editor = Macros.edit();

        editor.putString("NameMacro1", NameMacro1);
        editor.putString("NameMacro2", NameMacro2);
        editor.putString("NameMacro3", NameMacro3);
        editor.putString("TxtMacro1", TxtMacro1);
        editor.putString("TxtMacro2", TxtMacro2);
        editor.putString("TxtMacro3", TxtMacro3);
        editor.commit();

        Toast.makeText(getApplicationContext(), "Macro Settings Saved", Toast.LENGTH_SHORT).show();
    }
}
