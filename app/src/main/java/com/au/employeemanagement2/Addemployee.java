package com.au.employeemanagement2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Abhishek upadhyay on 02-02-2017.
 */

public class Addemployee extends Fragment {
    public static Addemployee newInstance() {
        Addemployee fragment = new Addemployee();
        return fragment;
    }
    public Addemployee(){

    }

    private EditText inputEmail, inputPassword,inputName,Username2;
    private Button btnLogin ;
    private ProgressBar progressBar;
    private FirebaseAuth auth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.addemployee, container, false);
        super.onCreate(savedInstanceState);

        auth = FirebaseAuth.getInstance();

        inputName = (EditText) rootView.findViewById(R.id.Name);
        inputName.setTextColor(Color.WHITE);
        inputEmail = (EditText) rootView.findViewById(R.id.Username);
        inputEmail.setTextColor(Color.WHITE);
        inputPassword = (EditText) rootView.findViewById(R.id.Password);
        inputPassword.setTextColor(Color.WHITE);
        btnLogin=(Button)rootView.findViewById(R.id.button3);
        progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.INVISIBLE);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String name = inputName.getText().toString().trim();
                final String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();

                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(getContext(), "Enter Name!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                //create user
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(getActivity(), "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.INVISIBLE);
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(getActivity(), "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getActivity(), "New Employee added " + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("users");

// Creating new user node, which returns the unique key value
// new user node would be /users/$userid/
                                    //mDatabase.child("username1").push()
                                   // String userId = mDatabase.push().getKey();

// creating user object

                                    user muser = new user(name, email);
                                    String emails[]=email.split("@");
                                    String email2=emails[0];
                                    mDatabase.child(email2).setValue(muser);
// pushing user to 'users' node using the userId
                                    //mDatabase.child(userId).setValue(muser);

                            }
                            }
                        });

            }
        });
        return rootView;

    }
}
