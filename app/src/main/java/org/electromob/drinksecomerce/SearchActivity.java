package org.electromob.drinksecomerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

      // DisplayMetrics dm=new DisplayMetrics();
      // getWindowManager().getDefaultDisplay().getMetrics(dm);
      // final int width=dm.widthPixels;
      // final int height=dm.heightPixels;
      // getWindow().setLayout((int)(width),(int)(height*0.07));

      // //position popup and dim the background
      // WindowManager.LayoutParams params=getWindow().getAttributes();
      // params.gravity= Gravity.TOP;
      // params.dimAmount=0.5f;
      // params.flags= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
      // getWindow().setAttributes(params);

    }
}