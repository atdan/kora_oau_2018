package com.example.root.quiko;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.root.quiko.common.Common;
import com.example.root.quiko.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    EditText phone_no,password;
    Button signInBtn;
    TextView registerTxtView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        phone_no = findViewById(R.id.edtPhoneSignIn);
        password = findViewById(R.id.edtPasswordSignIn);

        signInBtn = findViewById(R.id.btn_signIn_activity);

        registerTxtView = findViewById(R.id.registerTextView);



        //init Firebase
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("Users");

        registerTxtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
                progressDialog.setMessage("Please Wait...");
                progressDialog.show();
                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        // To check if user does not exist in db
                        if (dataSnapshot.child(phone_no.getText().toString()).exists()) {


                            // Get user info

                            progressDialog.dismiss();
                            User user = dataSnapshot.child(phone_no.getText().toString()).getValue(User.class);
                            user.setPhone(phone_no.getText().toString()); // set phone
                            if (user.getPassword().equals(password.getText().toString())) {
                                {
                                    Toast.makeText(LoginActivity.this, "Signed in successfully", Toast.LENGTH_SHORT).show();
                                    Intent homeIntent = new Intent(LoginActivity.this, MainActivity.class);
                                    Common.current_user = user;
                                    startActivity(homeIntent);
                                    finish();
                                }



                            } else {
                                Toast.makeText(LoginActivity.this, "Incorrect Password", Toast.LENGTH_SHORT).show();

                            }
                        }else {
                            progressDialog.dismiss();
                            Toast.makeText(LoginActivity.this, "User does not exist", Toast.LENGTH_SHORT).show();

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }
}
