package org.electromob.drinksecomerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

public class NavigationActivity extends AppCompatActivity {

    private LinearLayout logout;
    private TextView username,user_email;

    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        logout = findViewById(R.id.lllogout);

        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width=dm.widthPixels;
        int height=dm.heightPixels;
        getWindow().setLayout((int)(width*0.6),(height));

        reference = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getUid());

        username = findViewById(R.id.username);
        user_email = findViewById(R.id.user_email);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                {
                    String username1 = snapshot.child("firebase_name").getValue().toString();
                    String email1 = snapshot.child("firebase_email").getValue().toString();

                    username.setText(username1);
                    user_email.setText(email1);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //try {
        //    final StorageReference storageReference = firebaseStorage.getReference("Users");
        //    storageReference.child(user_uid).child("Profile Pic")
        //            .getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
        //        @Override
        //        public void onSuccess(Uri uri) {
        //            Picasso.get().load(uri).into(profile);
        //        }
        //    });
        //    profile.setVisibility(View.VISIBLE);
        //}
        //catch (Exception e) {
        //    Toast.makeText(getApplicationContext(),"Doesn't have a profile picture",Toast.LENGTH_SHORT).show();
        //}

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });

        WindowManager.LayoutParams params=getWindow().getAttributes();
        params.gravity= Gravity.LEFT;
        params.dimAmount=0.5f;
        params.flags= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        getWindow().setAttributes(params);

    }
}
