package org.electromob.drinksecomerce;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    private ImageView back;
    private TextView name;
    private CircleImageView profile;

    private FirebaseAuth firebaseAuth;
    private FirebaseStorage firebaseStorage;
    private StorageReference mref;
    private DatabaseReference reference;

    private StorageTask muploadtask;

    private static final int PICK_IMAGE = 123;
    private ProgressDialog progressDialog;
    private Uri imagepath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profile = findViewById(R.id.profile);
        back = findViewById(R.id.back);
        name = findViewById(R.id.name);

        progressDialog = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        mref = firebaseStorage.getReference();
        reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseAuth.getUid()).child("firebase_name");

        try {
            final StorageReference storageReference = firebaseStorage.getReference("Users");
            storageReference.child(firebaseAuth.getUid()).child("Profile Pic")
                    .getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Picasso.get().load(uri).into(profile);
                }
            });
            profile.setVisibility(View.VISIBLE);
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(),"click and upload your profile image",Toast.LENGTH_SHORT).show();
        }

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                {
                    String name1 = snapshot.getValue().toString();
                    name.setText(name1);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Select from Camera roll: ", Toast.LENGTH_SHORT).show();
                if (muploadtask != null && muploadtask.isInProgress()) {
                    Toast.makeText(getApplicationContext(), "Upload in progress ...", Toast.LENGTH_SHORT).show();

                } else {
                    openfile();
                }
            }
        });

    }

    private void openfile() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imagepath = data.getData();

            Picasso.get().load(imagepath).into(profile);

            if (imagepath != null ){
                progressDialog.setMessage("Saving profile picture");
                progressDialog.show();
                StorageReference fileReference = mref.child("Users").child(firebaseAuth.getUid()).child("Profile Pic");

                muploadtask = fileReference.putFile(imagepath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(),"Process success",Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(),"Process failed",Toast.LENGTH_SHORT).show();
                    }
                });
            }else{
                Toast.makeText(getApplicationContext(),"IMAGE NOT SELECTED: ",Toast.LENGTH_SHORT).show();
            }
        }
    }
}