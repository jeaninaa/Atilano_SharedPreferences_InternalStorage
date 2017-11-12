package com.atilano.atilano_sharedpreferences_internalstorage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText etUname, etPword;
    Button btnPref, btnStorage;

    SharedPreferences preferences;
    FileOutputStream fos;

    String user, pwd;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUname = (EditText) findViewById(R.id.etUname);
        etPword = (EditText) findViewById(R.id.etPword);
        preferences = getSharedPreferences("sharedText", MODE_PRIVATE);
    }

    public void savePreferences(View view){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("user", etUname.getText().toString());
        editor.putString("pwd", etPword.getText().toString());
        editor.commit();

        Toast.makeText(this, "Saved in Shared Preferences", Toast.LENGTH_SHORT).show();
    }

    public void saveStorage(View view){
        String user = "Username: " + etUname.getText().toString() + " | ";
        String pwd = "Password: " + etPword.getText().toString();

        try{
            fos = openFileOutput("output.txt", Context.MODE_PRIVATE);
            fos.write(user.getBytes());
            fos.write(pwd.getBytes());
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try{
                fos.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        Toast.makeText(this, "Saved in Internal Storage", Toast.LENGTH_SHORT).show();
    }

    public void openNext(View view){
        intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }
}
