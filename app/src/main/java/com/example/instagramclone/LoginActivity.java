package com.example.instagramclone;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity {

    private TextView screenTitle;
    private ImageView appLogo;

    private EditText logInEmail;

    private EditText  logInPassword;

    private Button  logInButton;

    private TextView linkRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        screenTitle = findViewById(R.id.Screen_title);
        appLogo = findViewById(R.id.app_logo);
        logInEmail = findViewById(R.id.login_email);
        logInPassword = findViewById(R.id.login_password);
        logInButton = findViewById(R.id.btn_login);
        linkRegister = findViewById(R.id.link_register);

        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String retrieveEmail, retrievePassword;
                retrieveEmail = logInEmail.getText().toString();
                retrievePassword = logInPassword.getText().toString();


                if (retrieveEmail.isEmpty()) {
                    Toast.makeText(Context:LoginActivity.this, text:
                    "Email field is empty", Toast.LENGTH_LONG).show();
                    return;
                }
                if (retrievePassword.isEmpty()) {
                    Toast.makeText(Context:LoginActivity.this, text:
                    "Password field is empty", Toast.LENGTH_LONG).show();
                    return;
                }

//              this below method will validate is user is registered or not
                mAuth.signInWithEmailAndPassword(retrieveEmail,retrievePassword)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>(){
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task){
                                if (task.isSuccessful()){

                                    Toast.makeText(context:LoginActivity.this,
                                            text:"Login is successful", Toast.LENGTH_LONG).show();


                                    //   after account is created it will nevigate to logout page, it will shoe user
                                    Intent intent =  new Intent(Registrationactivity.this,LoginActivity.class)
                                    startActivity(intent);

                                }else{
                                    // If sign in fails,displays a message to the user
                                    Toast.makeText(context:LoginActivity.this, text="Authentication failed. User is not registered",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
            }
        });

        linkRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });

    }
    // IMP: if user is loggedIn already then won't open the LOGIN/REG screens


    @Override
    public void onStart() {
        super.onStart();
        //Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentuser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(LoginActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }
}