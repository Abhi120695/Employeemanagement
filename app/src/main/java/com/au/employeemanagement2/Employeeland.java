package com.au.employeemanagement2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class Employeeland extends AppCompatActivity {
    private EditText Password, confirmpassword;
    private Button changepass, check, cancel, confirm;
    private ProgressBar progressbar;
    private DatabaseReference databaseReference;
    private DatabaseReference databaseReference2;
    private Integer percentvalue;
    private String detail;
    private FirebaseAuth auth;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employeeland);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        auth = FirebaseAuth.getInstance();

        String data = getIntent().getStringExtra("key1");
        email = getIntent().getStringExtra("key2");
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("users");
        mDatabase.child(email).child(data).setValue("1");
        Password = (EditText) findViewById(R.id.editText);
       Password.setTextColor(Color.WHITE);
        confirmpassword = (EditText) findViewById(R.id.editText2);
        confirmpassword.setTextColor(Color.WHITE);
        changepass = (Button) findViewById(R.id.button100);
        check = (Button) findViewById(R.id.button5);
        cancel = (Button) findViewById(R.id.button2);
        confirm = (Button) findViewById(R.id.button4);
        progressbar = (ProgressBar) findViewById(R.id.progressBar4);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference2 = databaseReference.child("users");
        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot mDataSnapshot) {
                getUser(mDataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


    }

    public void changepass(View view) {
        changepass.setVisibility(View.INVISIBLE);
        check.setVisibility(View.INVISIBLE);
        Password.setVisibility(View.VISIBLE);
        confirmpassword.setVisibility(View.VISIBLE);
        cancel.setVisibility(View.VISIBLE);
        confirm.setVisibility(View.VISIBLE);


    }

    public void cancel(View view) {
        changepass.setVisibility(View.VISIBLE);
        check.setVisibility(View.VISIBLE);
        Password.setVisibility(View.INVISIBLE);
        confirmpassword.setVisibility(View.INVISIBLE);
        cancel.setVisibility(View.INVISIBLE);
        confirm.setVisibility(View.INVISIBLE);

    }

    public void confirm(View view) {

        String pass = Password.getText().toString();
        String conpass = confirmpassword.getText().toString();
        if (pass.equals(conpass) && pass.length() >= 8) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

            user.updatePassword(pass.trim())
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Employeeland.this, "Password is updated!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Employeeland.this, "Failed to update password!", Toast.LENGTH_SHORT).show();
                                progressbar.setVisibility(View.INVISIBLE);
                                changepass.setVisibility(View.VISIBLE);
                                check.setVisibility(View.VISIBLE);
                                Password.setVisibility(View.INVISIBLE);
                                confirmpassword.setVisibility(View.INVISIBLE);
                                cancel.setVisibility(View.INVISIBLE);
                                confirm.setVisibility(View.INVISIBLE);
                            }
                        }
                    });

        }

    }


    public void Viewattendence(View view) {
        Intent intent = new Intent(Employeeland.this, Viewattendence.class);
        intent.putExtra("percent", percentvalue);
        intent.putExtra("detail", detail);
        Log.v("percent",percentvalue.toString());
        startActivity(intent);


    }

    private void getUser(DataSnapshot dataSnapshot) {
        //for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
        user muser = dataSnapshot.child(email).getValue(user.class);

        Integer value = Integer.valueOf(muser.mgetDay_1()) + Integer.valueOf(muser.mgetDay_2()) + Integer.valueOf(muser.mgetDay_3()) + Integer.valueOf(muser.mgetDay_4()) + Integer.valueOf(muser.mgetDay_5()) + Integer.valueOf(muser.mgetDay_6()) + Integer.valueOf(muser.mgetDay_7()) + Integer.valueOf(muser.mgetDay_8()) + Integer.valueOf(muser.mgetDay_9()) + Integer.valueOf(muser.mgetDay_10()) +
                +Integer.valueOf(muser.mgetDay_11()) + Integer.valueOf(muser.mgetDay_12()) + Integer.valueOf(muser.mgetDay_13()) + Integer.valueOf(muser.mgetDay_14()) + Integer.valueOf(muser.mgetDay_15()) + Integer.valueOf(muser.mgetDay_16()) + Integer.valueOf(muser.mgetDay_17())
                + Integer.valueOf(muser.mgetDay_18()) + Integer.valueOf(muser.mgetDay_19()) + Integer.valueOf(muser.mgetDay_20()) + Integer.valueOf(muser.mgetDay_21()) + Integer.valueOf(muser.mgetDay_22()) + Integer.valueOf(muser.mgetDay_23())
                + Integer.valueOf(muser.mgetDay_24()) + Integer.valueOf(muser.mgetDay_25()) + Integer.valueOf(muser.mgetDay_26()) + Integer.valueOf(muser.mgetDay_27()) + Integer.valueOf(muser.mgetDay_28()) + Integer.valueOf(muser.mgetDay_29())
                + Integer.valueOf(muser.mgetDay_30()) + Integer.valueOf(muser.mgetDay_31());
        Log.v("check1", value.toString());
        detail = "Day_1-"  + getString(muser.mgetDay_1()) + "\n" + "Day_2-"  + getString(muser.mgetDay_2()) + "\n" + "Day_3-"  + getString(muser.mgetDay_3()) + "\n" + "Day_4-"  + getString(muser.mgetDay_4()) + "\n" + "Day_5-" + getString(muser.mgetDay_5()) + "\n"+
                "Day_6-"  + getString(muser.mgetDay_6()) + "\n" + "Day_7-"  + getString(muser.mgetDay_7()) + "\n" + "Day_8-"  + getString(muser.mgetDay_8()) + "\n" + "Day_9-"  + getString(muser.mgetDay_9()) + "\n" + "Day_10-"  + getString(muser.mgetDay_10()) + "\n"+
                "Day_11-"  + getString(muser.mgetDay_11()) + "\n" + "Day_12-"  + getString(muser.mgetDay_12()) + "\n" + "Day_13-"  + getString(muser.mgetDay_13()) + "\n" + "Day_14-"  + getString(muser.mgetDay_14()) + "\n" + "Day_15-"  + getString(muser.mgetDay_15()) + "\n"+
                "Day_16-"  + getString(muser.mgetDay_17()) + "\n" + "Day_17-"  + getString(muser.mgetDay_17()) + "\n" + "Day_18-"  + getString(muser.mgetDay_18()) + "\n" + "Day_19-"  + getString(muser.mgetDay_18()) + "\n" + "Day_20-"  + getString(muser.mgetDay_20()) + "\n"+
                "Day_21-"  + getString(muser.mgetDay_22()) + "\n" + "Day_22-"  + getString(muser.mgetDay_22()) + "\n" + "Day_23-"  + getString(muser.mgetDay_23()) + "\n" + "Day_24-"  + getString(muser.mgetDay_23()) + "\n" + "Day_25-"  + getString(muser.mgetDay_25()) + "\n"+
                "Day_26-"  + getString(muser.mgetDay_26()) + "\n" + "Day_27-"  + getString(muser.mgetDay_27()) + "\n" + "Day_28-"  + getString(muser.mgetDay_28()) + "\n" + "Day_29-"  + getString(muser.mgetDay_29()) + "\n" + "Day_30-"  + getString(muser.mgetDay_30()) + "\n"+
                "Day_31-"  + getString(muser.mgetDay_31());
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int totaldays = countWeekendDays(year, month);
        Log.v("check2", String.valueOf(totaldays));
        float percent = getpercent(value, totaldays);
        int percentfinal = Math.round(percent);

        String percentstring = Float.toString(percent);
        Toast.makeText(Employeeland.this, "Your attendence till now is " + percentstring, Toast.LENGTH_SHORT).show();
        percentvalue = percentfinal;

    }

    private float getpercent(Integer mValue, int mTotaldays) {
        float proportionCorrect = ((float) mValue) / ((float) mTotaldays);
        return proportionCorrect * 100;
    }


    public int countWeekendDays(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        // Note that month is 0-based in calendar, bizarrely.
        // calendar.set(year, month - 1, 1);
        //int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int daysInMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int count = 0;
        Log.v("check3", String.valueOf(daysInMonth));

        for (int day = 1; day <= daysInMonth; day++) {
            calendar.set(year, month, day);
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

            if (dayOfWeek == Calendar.SUNDAY || dayOfWeek == Calendar.SATURDAY) {
                count++;
                // Or do whatever you need to with the result.
            }
        }
        int totaldays = daysInMonth - count;
        return totaldays;
    }

    public void Logout(View view) {
        auth.signOut();
// this listener will be called when there is change in firebase user session
        FirebaseAuth.AuthStateListener authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(Employeeland.this, Employee.class));
                    finish();
                }
            }
        };
        startActivity(new Intent(Employeeland.this, Employee.class));
        finish();
    }
  String  getString(String s){
        switch (s){
            case"0":return "absent";
            case "1":return "present";
        }
    return null;
    }
}
