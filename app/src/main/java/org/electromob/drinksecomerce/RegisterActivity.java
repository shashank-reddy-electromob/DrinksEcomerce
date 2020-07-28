package org.electromob.drinksecomerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private EditText name,email,phone_number,password;
    private Button register;
    private String full_name,storage_email,storage_phone_number,storage_password;

    private FirebaseUser firebaseUser;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        changeStatusBarColor();

        name = findViewById(R.id.editTextName);
        email = findViewById(R.id.editTextEmail);
        phone_number = findViewById(R.id.editTextMobile);
        password = findViewById(R.id.editTextPassword);
        register = findViewById(R.id.cirRegisterButton);

        reference = FirebaseDatabase.getInstance().getReference("Users");

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                full_name = name.getText().toString().trim();
                storage_email = email.getText().toString().trim();
                storage_phone_number = phone_number.getText().toString().trim();
                storage_password = password.getText().toString().trim();

                if (!full_name.isEmpty() && !storage_email.isEmpty() && !storage_phone_number.isEmpty() && !storage_password.isEmpty())
                {
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(storage_email, storage_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                sendemail();
                            } else {
                                FirebaseAuthException e = (FirebaseAuthException)task.getException();
                                Toast.makeText(getApplicationContext(), "registration failed.... "+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    Toast.makeText(getApplicationContext(), "Data taken ....", Toast.LENGTH_LONG).show();

                }else
                {
                    Toast.makeText(getApplicationContext(),"Please enter all the details.....",Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void sendemail(){
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if(firebaseUser != null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        reference = reference.child(FirebaseAuth.getInstance().getUid());
                        UserInfoClass userinfo;
                        userinfo = new UserInfoClass(full_name,storage_email,storage_phone_number,storage_password);
                        reference.setValue(userinfo);
                        Toast.makeText(getApplicationContext(),"Successfully registered, verification mail has sent",Toast.LENGTH_LONG).show();
                        FirebaseAuth.getInstance().signOut();
                        finish();
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    }else{
                        name.setText(null);
                        phone_number.setText(null);
                        email.setText(null);
                        password.setText(null);
                        Toast.makeText(getApplicationContext(),"Verification mail failed to send try after sometime", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }

    public void onLoginClick(View view){
        startActivity(new Intent(this,LoginActivity.class));
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);

    }
}
