package com.example.userregister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.logging.LogRecord;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference rootRef = firebaseDatabase.getReference();
    DatabaseReference userRef = rootRef.child("USER");

    EditText name, userName, email;
    Button regBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        name = findViewById(R.id.name);
        userName = findViewById(R.id.userName);
        email = findViewById(R.id.email);
        regBtn = findViewById(R.id.btnReg);

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String myName = name.getText().toString().trim();
                String myUserName = userName.getText().toString().trim();
                String myEmail = email.getText().toString().trim();

                if (myName.length() == 0) {
                    name.setError("Enter name ");
                } else if (myUserName.length() == 0) {
                    userName.setError("Enter UserName ");

                } else if (myEmail.length() == 0) {
                    email.setError("Enter Email ");

                } else {
                    HashMap<String, String> userMap = new HashMap<String, String>();
                    userMap.put("Name", myName);
                    userMap.put("UserName", myUserName);
                    userMap.put("Email", myEmail);
                    userRef.push().setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Data Added to Database", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });

                }
            }
        });

    }
}