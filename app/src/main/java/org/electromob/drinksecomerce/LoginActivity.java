package org.electromob.drinksecomerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;

import static android.widget.Toast.LENGTH_SHORT;

public class LoginActivity extends AppCompatActivity {

    private EditText email,password;
    private String email_id,password_login;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.editTextEmaillogin);
        password = findViewById(R.id.editTextPasswordlogin);

        firebaseAuth = FirebaseAuth.getInstance();
//
        if(firebaseAuth.getCurrentUser() != null){
            finish();
            overridePendingTransition(0, 0);
            startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
            overridePendingTransition(0, 0);
        }


    }

    private void checkemailverification(){
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        Boolean emailflag = firebaseUser.isEmailVerified();

        if (emailflag){
            finish();
            startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
        }else{
            Toast.makeText(getApplicationContext(),"Verify your email..", LENGTH_SHORT).show();
            firebaseAuth.signOut();
        }
    }

    public void onLoginClick(View view){
        startActivity(new Intent(this,RegisterActivity.class));
        overridePendingTransition(R.anim.slide_in_right,R.anim.stay);
    }

    public void onLoginClick1(View view){
        startActivity(new Intent(this,PrivacyPolicy.class));
        overridePendingTransition(R.anim.slide_in_right,R.anim.stay);
    }

    public void login(View view) {

        email_id = email.getText().toString();
        password_login = password.getText().toString();

        Log.d("test",email_id);
        Log.d("test1",password_login);

        if (!email_id.isEmpty() && !password_login.isEmpty())
        {
            firebaseAuth.signInWithEmailAndPassword(email_id,password_login).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(),"Login successfull...", Toast.LENGTH_SHORT).show();
                        checkemailverification();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Username does not exist", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else {
            Toast.makeText(getApplicationContext(),"Enter all the details....",Toast.LENGTH_LONG).show();
        }
    }
}
