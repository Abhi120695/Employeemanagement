package com.au.employeemanagement2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

public class Launch extends AppCompatActivity {

    private SharedPreferences settings2;
    SharedPreferences.Editor editor ;
    private String choice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        settings2 = getSharedPreferences("Choice", Context.MODE_PRIVATE);
        //   settings = this.getSharedPreferences(Login_Activity.PREFS_NAME, 0);
        editor = settings2.edit();


        boolean hasLoggedIn2 = settings2.getBoolean("haschosen",false);
        choice = settings2.getString("Choice", "no Choice");

        if (hasLoggedIn2) {
            Log.v("yag1","wtf");
            switch (choice) {
                case "Admin":
                    Intent intent = new Intent();
                    intent.setClass(Launch.this, Admin.class);
                    intent.putExtra("Choice", choice);
                    startActivity(intent);
                    Launch.this.finish();
                    break;
                case "Employee":

                    Intent intent2 = new Intent();
                    intent2.setClass(Launch.this, Employee.class);
                    intent2.putExtra("Choice", choice);
                    startActivity(intent2);
                    Launch.this.finish();
                    break;


            }


        }
    }

    public void LaunchAdmin(View view) {
        editor.putBoolean("haschosen", true);
        editor.putString("Choice", "Admin");
        Intent intent =new Intent(this,Admin.class);
        startActivity(intent);

        Launch.this.finish();
        editor.commit();

    }

    public void Launchemployee(View view) {
        editor.putBoolean("haschosen", true);
        editor.putString("Choice", "Employee");
        Intent intent =new Intent(this,Employee.class);
        startActivity(intent);
        Launch.this.finish();
        editor.commit();

    }

}
