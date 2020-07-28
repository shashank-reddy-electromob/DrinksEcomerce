package org.electromob.drinksecomerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Queue;

public class DashboardActivity extends AppCompatActivity {

    private ImageView navigation,cart,beer,whisky,rum,vodka,home,search,favourite,offer,profile;

    private DatabaseReference reference;

    //private Query query;

    private static int count = 0;
    private static int count1 = 0;
    private static int count2 = 0;
    private static int count3 = 0;

    private static int price = 250;
    private static int price1 = 500;
    private static int price2 = 1000;
    private static int price3 = 1250;

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;

    private TextView text,text1,text2,text3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        navigation = findViewById(R.id.imageView);
        cart = findViewById(R.id.cart_activity);
        beer = findViewById(R.id.beer);
        whisky = findViewById(R.id.whisky);
        rum = findViewById(R.id.rum);
        vodka = findViewById(R.id.vodka);
        home = findViewById(R.id.home);
        search = findViewById(R.id.search);
        favourite = findViewById(R.id.favourite);
        offer = findViewById(R.id.offers);
        profile = findViewById(R.id.profile);
        text = findViewById(R.id.textview11);
        text1 = findViewById(R.id.textview1);
        text2 = findViewById(R.id.textview2);
        text3 = findViewById(R.id.textview3);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        reference = firebaseDatabase.getReference("Users").child(firebaseAuth.getUid()).child("Cart");
        //Log.d("test",firebaseAuth.getUid());

        //Toast.makeText(getApplicationContext(),firebaseAuth.getUid(),Toast.LENGTH_LONG).show();

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),DashboardActivity.class));
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SearchActivity.class));
            }
        });

        favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),FavouriteActivity.class));
            }
        });

        offer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),OffersActivity.class));
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
            }
        });

        beer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Addcart("beer");
            }
        });

        whisky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Addcart("whisky");
            }
        });

        rum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Addcart("rum");
            }
        });

        vodka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Addcart("vodka");
            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),CartActivity.class));
            }
        });

        navigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),NavigationActivity.class));
            }
        });

    }

    private void Addcart(final String type) {

        reference.child(type).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                {
                    if (type.equals("beer"))
                    {
                        String t1 = snapshot.child("itemcount").getValue().toString();
                        text.setText(t1);
                    }else if (type.equals("whisky"))
                    {
                        String t2 = snapshot.child("itemcount").getValue().toString();
                        text1.setText(t2);
                    }else if (type.equals("rum"))
                    {
                        String t3 = snapshot.child("itemcount").getValue().toString();
                        text2.setText(t3);
                    }else if (type.equals("vodka"))
                    {
                        String t4 = snapshot.child("itemcount").getValue().toString();
                        text3.setText(t4);
                    }
                }
                else {
                    if (type.equals("beer"))
                    {
                        Toast.makeText(getApplicationContext(),"Beer has been added to the cart...",Toast.LENGTH_SHORT).show();
                        count = count + 1;
                        CartAddition cartAddition = new CartAddition(type,String.valueOf(price*count),String.valueOf(count));
                        reference.child(type).setValue(cartAddition);
                    }else if (type.equals("whisky"))
                    {
                        Toast.makeText(getApplicationContext(),"Whisky has been added to the cart...",Toast.LENGTH_SHORT).show();
                        count1 = count1 + 1;
                        CartAddition cartAddition = new CartAddition(type,String.valueOf(price1*count1),String.valueOf(count1));
                        reference.child(type).setValue(cartAddition);
                    }else if (type.equals("rum"))
                    {
                        Toast.makeText(getApplicationContext(),"Rum has been added to the cart...",Toast.LENGTH_SHORT).show();
                        count2 = count2 + 1;
                        CartAddition cartAddition = new CartAddition(type,String.valueOf(price2*count2),String.valueOf(count2));
                        reference.child(type).setValue(cartAddition);
                    }else if (type.equals("vodka"))
                    {
                        Toast.makeText(getApplicationContext(),"Vodka has been added to the cart...",Toast.LENGTH_SHORT).show();
                        count3 = count3 + 1;
                        CartAddition cartAddition = new CartAddition(type,String.valueOf(price3*count3),String.valueOf(count3));
                        reference.child(type).setValue(cartAddition);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        try {
            if (type.equals("beer"))
            {
                Toast.makeText(getApplicationContext(),"Beer has been added to the cart...",Toast.LENGTH_SHORT).show();
                count = Integer.parseInt(text.getText().toString());
                count = count + 1;
                CartAddition cartAddition = new CartAddition(type,String.valueOf(price*count),String.valueOf(count));
                reference.child(type).setValue(cartAddition);
            }else if (type.equals("whisky"))
            {
                Toast.makeText(getApplicationContext(),"Whisky has been added to the cart...",Toast.LENGTH_SHORT).show();
                count1 = Integer.parseInt(text1.getText().toString());
                count1 = count1 + 1;
                CartAddition cartAddition = new CartAddition(type,String.valueOf(price1*count1),String.valueOf(count1));
                reference.child(type).setValue(cartAddition);
            }else if (type.equals("rum"))
            {
                Toast.makeText(getApplicationContext(),"Rum has been added to the cart...",Toast.LENGTH_SHORT).show();
                count2 = Integer.parseInt(text2.getText().toString());
                count2 = count2 + 1;
                CartAddition cartAddition = new CartAddition(type,String.valueOf(price2*count2),String.valueOf(count2));
                reference.child(type).setValue(cartAddition);
            }else if (type.equals("vodka"))
            {
                Toast.makeText(getApplicationContext(),"Vodka has been added to the cart...",Toast.LENGTH_SHORT).show();
                count3 = Integer.parseInt(text3.getText().toString());
                count3 = count3 + 1;
                CartAddition cartAddition = new CartAddition(type,String.valueOf(price3*count3),String.valueOf(count3));
                reference.child(type).setValue(cartAddition);
            }
        }catch (Exception e)
        {
            Log.e("error",e.toString());
        }


        //query = reference.child(type).orderByChild("item").equalTo(type);
        //query.addListenerForSingleValueEvent(new ValueEventListener() {
        //    @Override
        //    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        //        if (dataSnapshot.exists()) {
        //            CartAddition user = null;
        //            for (DataSnapshot childDss : dataSnapshot.getChildren()) {
        //                user = childDss.getValue(CartAddition.class);
        //                count = Integer.parseInt(user.itemcount);
//
        //                count = count + 1;
//
        //            }
        //        }else {
        //            count = 0;
        //        }
        //
        //    }
//
        //    @Override
        //    public void onCancelled(@NonNull DatabaseError error) {
        //
        //    }
        //});

    }
}
