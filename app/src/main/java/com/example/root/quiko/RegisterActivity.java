package com.example.root.quiko;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.root.quiko.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity {

    EditText edtPhoneSignUp, edtNameSignUp, edtPasswordSignUp, edtEmail, edtAccountNumber, edtCVV, edtCardNumber, edtCardExpMnth, edtCardExpYr;
    Button signUpBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtPhoneSignUp = findViewById(R.id.edtPhoneSignUp);
        edtNameSignUp = findViewById(R.id.edtNameSignUp);
        edtPasswordSignUp = findViewById(R.id.edtPasswordSignUp);
        edtEmail = findViewById(R.id.edtEmailSignUp);
        edtAccountNumber = findViewById(R.id.edtAcctSignUp);
        edtCVV = findViewById(R.id.edtcvvSignUp);
        edtCardNumber = findViewById(R.id.edtCardNoSignUp);
        edtCardExpMnth = findViewById(R.id.edtExpMnthSignUp);
        edtCardExpYr = findViewById(R.id.edtExpYrSignUp);

        signUpBtn = findViewById(R.id.btn_signUp_activity);

        //init Firebase
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this);
                progressDialog.setMessage("Please Wait...");
                progressDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        // check the phone

                        if(dataSnapshot.child(edtPhoneSignUp.getText().toString()).exists()){
                            progressDialog.dismiss();

                            Toast.makeText(RegisterActivity.this, "Phone number already registered", Toast.LENGTH_SHORT).show();

                        }else {
                            progressDialog.dismiss();

                            User user = new User(edtNameSignUp.getText().toString(),edtPasswordSignUp.getText().toString(),edtPhoneSignUp.getText().toString(),
                                    edtEmail.getText().toString(),edtAccountNumber.getText().toString(),edtCVV.getText().toString(),
                                    edtCardNumber.getText().toString(),edtCardExpMnth.getText().toString(),edtCardExpYr.getText().toString());
                            table_user.child(edtPhoneSignUp.getText().toString()).setValue(user);
                            Toast.makeText(RegisterActivity.this, "Signed up successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
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
