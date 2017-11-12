package com.atilano.atilano_sharedpreferences_internalstorage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main2Activity extends AppCompatActivity {

    Button btnLoadPref, btnLoadStorage, btnClear, btnBack;
    TextView tvDisplay;

    SharedPreferences preferences;
    FileInputStream fis;

    String lUser, lPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btnLoadPref = (Button) findViewById(R.id.btnLoadPref);
        btnLoadStorage = (Button) findViewById(R.id.btnLoadStorage);
        btnClear = (Button) findViewById(R.id.btnClear);
        btnBack = (Button) findViewById(R.id.btnBack);
        tvDisplay = (TextView) findViewById(R.id.tvDisplay);

        preferences = getSharedPreferences("sharedText", MODE_PRIVATE);
    }

    public void loadPref (View view){
        String user = preferences.getString("user", "");
        String pwd = preferences.getString("pwd","");
        tvDisplay.setText("User: " + user + " | Password: " + pwd);
    }

    public void loadStorage (View view){
        StringBuffer buffer = new StringBuffer();
        int read=0;

        try {
            fis = openFileInput("output.txt");
            while ((read = fis.read()) != -1) {
                buffer.append((char) read);
            }
            fis.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

        tvDisplay.setText(buffer.toString());
    }

    public void clearDisplay (View view){
        tvDisplay.setText("");
    }

    public void goBack (View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
