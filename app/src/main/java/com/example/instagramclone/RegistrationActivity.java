package com.example.instagramclone;



        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.google.android.gms.tasks.Task;
        import com.google.firebase.auth.AuthResult;
        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.auth.FirebaseUser;

public class Registrationactivity extends AppCompatActivity {

    private TextView screenTitle;

    private ImageView appLogo;

    private EditText regEmail;

    private EditText  regPassword;

    private Button regButton;

    private TextView linkLogin;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrationactivity);


        screenTitle = findViewById(R.id.Screen_title);
        appLogo = findViewById(R.id.app_logo);
        regEmail = findViewById(R.id.reg_email);
        regPassword = findViewById(R.id.reg_password);
        regButton = findViewById(R.id.btn_reg);
        linkLogin = findViewById(R.id.link_login);
        mAuth= FirebaseAuth.getInstance();

        regButton.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View view) {

                                             String retrieveEmail, retrievePassword;
                                             retrieveEmail=regEmail.getText().toString();
                                             retrievePassword=regPassword.getText().toString();


                                             if(retrieveEmail.isEmpty()){
                                                 Toast.makeText(Context:Registrationactivity.this,text: "Email field is empty",Toast.LENGTH_LONG).show();
                                                 return;
                                             }
                                             if(retrievePassword.isEmpty()){
                                                 Toast.makeText(Context:Registrationactivity.this,text:"Password field is empty",Toast.LENGTH_LONG).show();
                                                 return;

                                             }
                                             //              below code will be responsible for sending register user data to server
                                             mAuth.createUserWithEmailAndPassword(retrieveEmail,retrievePassword)
                                                     .addOnCompleteListener(this, new OnCompletionListener<AuthResult>(){

                                                         @Override
                                                         public void onComplete(@NonNull Task<AuthResult> task){
                                                             if(task.isSuccessful()) {
                                                                 Toast.makeText(context:Registrationactivity.this,
                                                                         text:"user account is created successfully", Toast.LENGTH_LONG).show();

                                                                 //   after account is created it will nevigate to logout page, it will shoe user
                                                                 Intent intent =  new Intent(Registrationactivity.this,LoginActivity.class)
                                                                 startActivity(intent);



                                                             }else{
                                                                 // If sign in fails, display a message to the user.
                                                                 Toast.makeText(EmailPasswordActivity.this,"Authentication failed.",
                                                                         Toast.LENGTH_LONG).show();
                                                                 updateUI(null);



                                                             }
                                                         }
                                                     });

                                         }
                                     }

                linkLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Registrationactivity.this, LoginActivity.class);
                        startActivity(intent);

                    }
                });
    }
    // IMP: if user is loggedIn already then won't open the LOGIN/REG screens


    @Override
    public void onStart(){
        super.onStart();
        //Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentuser = mAuth.getCurrentUser();
        if(currentUser != null ){
            Intent intent = new Intent(Registrationactivity.this, LoginActivity.class);
            startActivity(intent);

        }
    }
}