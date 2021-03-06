package com.au.employeemanagement2;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Admin extends AppCompatActivity {


    private EditText inputEmail, inputPassword;
    private FirebaseAuth auth;
    private ProgressBar progressBar;
    private Button btnSignup, btnLogin;
    private DatabaseReference databaseReference;
    private DatabaseReference databaseReference2;
    String email2;
    Boolean admin =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        auth = FirebaseAuth.getInstance();
        inputEmail = (EditText) findViewById(R.id.Username);
        inputEmail.setTextColor(Color.WHITE);
        inputPassword = (EditText) findViewById(R.id.Password);
        inputPassword.setTextColor(Color.WHITE);
        btnLogin=(Button)findViewById(R.id.button3);
        progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.INVISIBLE);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference2=databaseReference.child("users");
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                String emails[]=email.split("@");
                email2=emails[0];
                databaseReference2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot mDataSnapshot) {
                        getUser(mDataSnapshot);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

            auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(Admin.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    // If sign in fails, display a message to the user. If sign in succeeds
                    // the auth state listener will be notified and logic to handle the
                    // signed in user can be handled in the listener.
                    progressBar.setVisibility(View.GONE);
                    if (!task.isSuccessful()) {
                        // there was an error
                        if (password.length() < 6) {
                            inputPassword.setError(getString(R.string.minimum_password));
                        } else {
                            Toast.makeText(Admin.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                        }
                    } else {
                        if (admin) {
                            Intent intent = new Intent(Admin.this, AdminLand.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(Admin.this, "You dont have admin rights", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            });
        }
    });
}
    private void getUser(DataSnapshot dataSnapshot) {
        //for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
        user muser = dataSnapshot.child(email2).getValue(user.class);
        if (muser.getAdmin()) admin = true;
        else admin = false;
    }
}