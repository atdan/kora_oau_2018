package com.example.root.quiko;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.root.quiko.common.Common;
import com.google.android.gms.tasks.OnSuccessListener;

import co.paystack.android.Paystack;
import co.paystack.android.PaystackSdk;
import co.paystack.android.Transaction;
import co.paystack.android.model.Card;
import co.paystack.android.model.Charge;

public class CarDetailActivity extends AppCompatActivity {

    private String your_api_key = "pk_live_712e73db8cebbb48e68f4afaf8a1c907c995c6c1"; //the API key generated from step 1 above.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_car_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton button =findViewById(R.id.btn_add_plan);


        //This following line is very important, you must initialize the Paystack SDK before using any Paystack class or interface.
        PaystackSdk.initialize(this.getApplicationContext());

        //Also, set your public key (from step 1 above) like this:
        PaystackSdk.setPublicKey(your_api_key);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //check whether user filled all the fields and there is internet connection

                if(isInternetAvailable()){
                    prepareToChargeUser(2 * 100);//In this case, we're charging the user #2000

                }

                else{
//                    Notify the user to check his internet connection or validate his entries
                }
            }
        });

    }
    private boolean isUserEntryValid(){
        boolean isValid = true;

        //check whether the email field is empty
        if(TextUtils.isEmpty(Common.current_user.getEmail()) || Common.current_user.getEmail()!= null){
//            emailField.setError("This field is required");
            Toast.makeText(this, "eMail is not set", Toast.LENGTH_SHORT).show();
            isValid = false;
        }

        //check whether the card-number field is empty
        else if(TextUtils.isEmpty(Common.current_user.getCardNumber()) || Common.current_user.getCardNumber() != null){
//            cardNumberField.setError("This field is required");
            Toast.makeText(this, "Card number is not set", Toast.LENGTH_SHORT).show();

            isValid = false;
        }

        //check whether the expiry-month field is empty
        else if(TextUtils.isEmpty(Common.current_user.getCardExpMnth() ) || Common.current_user.getCardExpMnth()!= null){
//            expiryMonthField.setError("This field is required");
            Toast.makeText(this, "Card expiry month is not set", Toast.LENGTH_SHORT).show();

            isValid = false;
        }

        //check whether the expiry-year field is empty
        else if(TextUtils.isEmpty(Common.current_user.getCardExpYr()) || Common.current_user.getCardExpYr()!= null){
//            expiryYearField.setError("This field is required");
            Toast.makeText(this, "Card expiry year is not set", Toast.LENGTH_SHORT).show();

            isValid = false;
        }

        //check whether the card-cvv field is empty
        else if(TextUtils.isEmpty(Common.current_user.getCVV())|| Common.current_user.getCVV()!= null){
//            cvvField.setError("This field is required");
            Toast.makeText(this, "Card cvv is not set", Toast.LENGTH_SHORT).show();

            isValid = false;
        }


        return isValid;
    }


    private boolean isInternetAvailable(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }


    private void prepareToChargeUser(int amountToBeCharged){

        //get all the EditText entries as String and int values for convenience
        String email =  "atumadann@gmail.com"; //Common.current_user.getEmail().trim();
//        String cardNumber = Common.current_user.getCardNumber().trim();
//        int expiryMonth = Integer.parseInt(Common.current_user.getCardExpMnth().trim());
//        int expiryYear = Integer.parseInt(Common.current_user.getCardExpYr().trim());
//        String cvv = Common.current_user.getCVV().trim();


            /*
            For testing purposes, Paystack provides a test card.. If you're still in the testing stage of your project,
            consider using the test cards, they are awesome!
            String cardNumber = "4084084084084081";
            int expiryMonth = 11; //any month in the future
            int expiryYear = 18; // any year in the future. '2018' would work also!
            String cvv = "408";  // cvv of the test card
            */


        //Create a new Card object based on the card details
        Card card = new Card("5399235063241582", 04, 21, "747");

        //check whether the card is valid like this:
        if (!(card.isValid())) {
            Toast.makeText(getApplicationContext(), "Card is not Valid", Toast.LENGTH_LONG).show();
            return;
        }


        //Create a new Charge object
        Charge charge = new Charge();

        charge.setCard(card);
        charge.setEmail(email);
        charge.setAmount(amountToBeCharged);

        //proceed to charge the user
        chargeTheUser(charge);


    }


    public void chargeTheUser(Charge charge){

        PaystackSdk.chargeCard(this, charge, new Paystack.TransactionCallback(){
            @Override
            public void beforeValidate(Transaction transaction){
                //You can set a progress bar or progress dialog so that the user knows something is going on
            }


            @Override
            public void onSuccess(Transaction transaction){
                Toast.makeText(getApplicationContext(), "Payment successful!!!", Toast.LENGTH_LONG).show();
                //Payment successful; proceed to give your user the product/service they paid for.
                startActivity(new Intent(CarDetailActivity.this,MainActivity.class));
            }

            @Override
            public void onError(Throwable error, Transaction transaction){
                new AlertDialog.Builder(CarDetailActivity.this)
                        .setMessage("Payment unsuccessful, please try again later")
                        .setCancelable(false)
                        .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //try again
                            }
                        })
                        .show();


            }
        });
    }

}
