package com.example.root.quiko;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.root.quiko.common.Common;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference category;


    TextView edtNameSignUp;
    EditText edtPhoneSignUp,  edtPasswordSignUp, edtEmail, edtAccountNumber, edtCVV, edtCardNumber, edtCardExpMnth, edtCardExpYr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // init Firebase
        database = FirebaseDatabase.getInstance();
        category = database.getReference("Category");


        edtPhoneSignUp = findViewById(R.id.edtPhoneProfile);
        edtNameSignUp = findViewById(R.id.full_name_profile);
        edtPasswordSignUp = findViewById(R.id.edtPasswordProfile);
        edtEmail = findViewById(R.id.edtEmailProfile);
        edtAccountNumber = findViewById(R.id.edtAcctProfile);
        edtCVV = findViewById(R.id.edtcvvProfile);
        edtCardNumber = findViewById(R.id.edtCardNoProfile);
        edtCardExpMnth = findViewById(R.id.edtExpMnthProfile);
        edtCardExpYr = findViewById(R.id.edtExpYrProfile);


        edtPhoneSignUp.setText(Common.current_user.getPhone());
        edtNameSignUp.setText(Common.current_user.getName());
        edtPasswordSignUp.setText(Common.current_user.getPassword());
        edtEmail.setText(Common.current_user.getEmail());
        edtAccountNumber.setText(Common.current_user.getAccountNumber());
        edtCVV.setText(Common.current_user.getCVV());
        edtCardNumber.setText(Common.current_user.getCardNumber());
        edtCardExpMnth.setText(Common.current_user.getCardExpMnth());
        edtCardExpYr.setText(Common.current_user.getCardExpYr());




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
