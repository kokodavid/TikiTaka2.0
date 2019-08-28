package com.david.tikitaka2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import butterknife.BindView;
import butterknife.ButterKnife;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.editTextName)
    EditText mUsername;
    @BindView(R.id.editTextEmail)
    EditText mEmail;
    @BindView(R.id.editTextPassword)
    EditText mPassword;
    @BindView(R.id.cirRegisterButton)
    Button mSignUp;
    @BindView(R.id.editTextMobile)
    EditText mNumber;
    private ProgressDialog mAuthProgressDialog;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        changeStatusBarColor();
        ButterKnife.bind(this);
        createAuthProgressDialog();
        mAuth = FirebaseAuth.getInstance();
        mSignUp.setOnClickListener(this);
          }

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }

    public void onLoginClick(View view) {
        startActivity(new Intent(this, LoginActivity.class));
        overridePendingTransition(R.anim.slide_in_left, android.R.anim.slide_out_right);

    }

    private void createAuthProgressDialog() {
        mAuthProgressDialog = new ProgressDialog(this);
        mAuthProgressDialog.setTitle("Registering new user");
        mAuthProgressDialog.setMessage("please wait...");
        mAuthProgressDialog.setCancelable(false);
    }


        @Override
    public void onClick(View v) {
        if (v == mSignUp) {
            mAuthProgressDialog.show();
            String username = mUsername.getText().toString().trim();
            String email = mEmail.getText().toString().trim();
            String password = mPassword.getText().toString().trim();
            String numberr = mNumber.getText().toString().trim();

            if (username.isEmpty()) {
                mUsername.setError("Username is required");
            } else if (!(Patterns.EMAIL_ADDRESS.matcher(email).matches())) {
                mEmail.setError("Invalid email");
            } else if (password.isEmpty()) {
                mPassword.setError("invalid password");
            }
            /*else if (!confirmPassword.matches(password)) {
                mConfirmPassword.setError("password mismatch");
            }*/
            else if (numberr.isEmpty()) {
                mNumber.setError("Invalid number");
            } else if (!email.isEmpty() && !username.isEmpty()) {
                mAuthProgressDialog.show();
                String username2 = mUsername.getText().toString();
                String email2 = mEmail.getText().toString();
                String number = mNumber.getText().toString();

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("users");
                                    myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot snapshot) {
                                            if (snapshot.hasChild(username2)) {
                                                mUsername.setError("Username already exists");
                                            } else {
                                                FirebaseUser current = mAuth.getCurrentUser();
                                                String uid = current.getUid();
                                                DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("users").child(uid);
                                                myRef.child("username").setValue(username2);
                                                myRef.child("Email").setValue(email2);
                                                myRef.child("phoneNumber").setValue(number);
                                                Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                                                startActivityForResult(intent, 0);
                                                finish();
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                } else {
                                    mAuthProgressDialog.dismiss();

                                }

                            }
                        });


            }


        }
    }
}
