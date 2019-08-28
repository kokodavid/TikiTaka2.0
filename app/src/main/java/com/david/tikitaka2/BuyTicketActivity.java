package com.david.tikitaka2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.androidstudy.daraja.Daraja;
import com.androidstudy.daraja.DarajaListener;
import com.androidstudy.daraja.model.AccessToken;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BuyTicketActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.Donationbutton)
    Button Donate;
    @BindView(R.id.number)
    EditText mNumber;
    @BindView(R.id.amount)
    EditText mAmount;
    private Daraja daraja;
    private String mSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lipanampesa);
        ButterKnife.bind(this);
        mNumber.setEnabled(true);

        show();
        Donate.setOnClickListener(this);

        daraja = Daraja.with("QTTcbArY3WPutdQilglgHrppRrqt2uDL", "9Qr6qBAfWOh7ojNg", new DarajaListener<AccessToken>() {
            @Override
            public void onResult(AccessToken accessToken) {
                Log.i(BuyTicketActivity.this.getClass().getSimpleName(), accessToken.getAccess_token());
            }

            @Override
            public void onError(String error) {
                Log.e(BuyTicketActivity.this.getClass().getSimpleName(), error);

            }
        });
    }

    private void show() {
        FirebaseUser current = FirebaseAuth.getInstance().getCurrentUser();
        String uid = current.getUid();
        FirebaseDatabase.getInstance().getReference("users").child(uid).child("phoneNumber")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String number2 = dataSnapshot.getValue(String.class);
                        mNumber.setText(number2);
//                        Log.d("number",number2);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
    }

    @Override
    public void onClick(View v) {
        if (v == Donate) {
            String phonenumber = mNumber.getText().toString().trim();
            String Amount = mAmount.getText().toString().trim();

            if (TextUtils.isEmpty(phonenumber)) {
                mNumber.setError("Please Provide a Phone Number");
                return;
            }
            if (Amount.isEmpty()) {
                mAmount.setError("Amount can't be empty");
                return;
            }
            if (Integer.valueOf(Amount) < 0) {
                mAmount.setError("Minimum amount is 0/=");
                return;
            }


        }
    }
}