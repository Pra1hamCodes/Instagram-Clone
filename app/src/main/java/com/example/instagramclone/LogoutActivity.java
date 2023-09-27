package com.example.instagramclone;



        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;

        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.auth.FirebaseUser;

public class LogoutActivity extends AppCompatActivity {


    private  String TAG= LogoutActivity.class.getSimpleName();
    private TextView showLoggedInUserEmail;

    private Button LogoutButton;

    private FirebaseAuth mAuth;

    private FirebaseUser user;
    private String msg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);


        showLoggedInUserEmail = findViewById(R.id.show_loggedIn_user_email);
        LogoutButton = findViewById(R.id.btn_Logout);
        mAuth=FirebaseAuth.getInstance();
        user=mAuth.getCurrentUser();
        String retrieveLoggedInUserEmail;
        retrieveLoggedInUserEmail = user.getEmail().toString();

        if(user ==null){
            Log.i(TAG,  msg:"retrieveloggedInUserEmail:"+retrieveLoggedInUserEmail);
            showLoggedInUserEmail.setText(retrieveLoggedInUserEmail);

        }

        LogoutButton.setOnClickListener(new view.OnClickListener() {
            @Override
            public void onClick(View v) {


                // sign out from loggedIn account
                //               mAuth.signout();

                // again it should navigate the user to the login screen
                Intent intent = new Intent(LogoutActivity.this, LoginActivity.class);
                startActivity(intent);

            }


        }
    }
