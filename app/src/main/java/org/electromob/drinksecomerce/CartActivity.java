package org.electromob.drinksecomerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

public class CartActivity extends AppCompatActivity {

    private Button proceed;
    private RecyclerView recyclerView;

    private Query query;

    private DatabaseReference reference1;

    private static int count0  = 0;
    private static int count01 = 0 ;
    private static int count02 = 0;
    private static int count03 = 0;

    private static int price = 250;
    private static int price1 = 500;
    private static int price2 = 1000;
    private static int price3 = 1250;

    FirebaseRecyclerOptions<CartAddition> options ;
    FirebaseRecyclerAdapter<CartAddition,ItemPostViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        proceed = findViewById(R.id.proceed);
        recyclerView = findViewById(R.id.cartrv);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        reference1 = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getUid()).child("Cart");
        reference1.keepSynced(true);

        options = new FirebaseRecyclerOptions.Builder<CartAddition>()
                .setQuery(reference1, CartAddition.class).build();

        try
        {

            adapter = new FirebaseRecyclerAdapter<CartAddition, ItemPostViewHolder>(options) {
                @Override
                protected void onBindViewHolder(final ItemPostViewHolder itemPostViewHolder, int i, final CartAddition cartAddition) {

                    final String type = cartAddition.item;

                   // query = reference.child(type).orderByChild("item").equalTo(type);
                   // query.addListenerForSingleValueEvent(new ValueEventListener() {
                   //     @Override
                   //     public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                   //         if (dataSnapshot.exists()) {
                   //             Toast.makeText(getApplicationContext(),"test",Toast.LENGTH_SHORT).show();
                   //             CartAddition user = null;
                   //             for (DataSnapshot childDss : dataSnapshot.getChildren()) {
                   //                 user = childDss.getValue(CartAddition.class);
                   //                 count = Integer.parseInt(user.itemcount);
//
                   //                 itemPostViewHolder.plusone.setOnClickListener(new View.OnClickListener() {
                   //                     @Override
                   //                     public void onClick(View v) {
                   //                         count = count + 1;
                   //                     }
                   //                 });
//
                   //                 itemPostViewHolder.minusone.setOnClickListener(new View.OnClickListener() {
                   //                     @Override
                   //                     public void onClick(View v) {
                   //                         count = count - 1;
                   //                     }
                   //                 });
                   //                 itemPostViewHolder.itemcount.setText(count);
                   //             }
                   //         } else {
                   //             count = 1;
                   //                 itemPostViewHolder.itemcount.setText(""+count);
                   //         }
                   //     }
//
                   //     @Override
                   //     public void onCancelled(@NonNull DatabaseError error) {
//
                   //     }
                   // });

                    if (type.equals("beer"))
                    {
                        itemPostViewHolder.imagetype.setBackgroundResource(R.drawable.beericon);
                        itemPostViewHolder.itemcount.setText(cartAddition.itemcount);
                    }else if (type.equals("whisky"))
                    {
                        itemPostViewHolder.imagetype.setBackgroundResource(R.drawable.whisky);
                        itemPostViewHolder.itemcount.setText(cartAddition.itemcount);
                    }else if (type.equals("rum"))
                    {
                        itemPostViewHolder.imagetype.setBackgroundResource(R.drawable.rum);
                        itemPostViewHolder.itemcount.setText(cartAddition.itemcount);
                    }else if (type.equals("vodka"))
                    {
                        itemPostViewHolder.imagetype.setBackgroundResource(R.drawable.vodka);
                        itemPostViewHolder.itemcount.setText(cartAddition.itemcount);
                    }
                    itemPostViewHolder.type.setText(cartAddition.item);
                    itemPostViewHolder.price.setText(cartAddition.price);

                    itemPostViewHolder.plusone.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if (type.equals("beer"))
                            {
                                count0 = Integer.parseInt(itemPostViewHolder.itemcount.getText().toString());
                                count0 = count0 + 1;
                                itemPostViewHolder.itemcount.setText(String.valueOf(count0));
                                itemPostViewHolder.price.setText(String.valueOf(price*count0));
                                reference1.child(type).child("itemcount").setValue(itemPostViewHolder.itemcount.getText().toString());
                                reference1.child(type).child("price").setValue(String.valueOf(price*count0));
                            }else if (type.equals("whisky"))
                            {
                                count01 = Integer.parseInt(itemPostViewHolder.itemcount.getText().toString());
                                count01 = count01 + 1;
                                itemPostViewHolder.itemcount.setText(String.valueOf(count01));
                                itemPostViewHolder.price.setText(String.valueOf(price1*count01));
                                reference1.child(type).child("itemcount").setValue(itemPostViewHolder.itemcount.getText().toString());
                                reference1.child(type).child("price").setValue(String.valueOf(price1*count01));
                            }else if (type.equals("rum"))
                            {
                                count02 = Integer.parseInt(itemPostViewHolder.itemcount.getText().toString());
                                count02 = count02 + 1;
                                itemPostViewHolder.itemcount.setText(String.valueOf(count02));
                                itemPostViewHolder.price.setText(String.valueOf(price2*count02));
                                reference1.child(type).child("itemcount").setValue(itemPostViewHolder.itemcount.getText().toString());
                                reference1.child(type).child("price").setValue(String.valueOf(price2*count02));
                            }else if (type.equals("vodka"))
                            {
                                count03 = Integer.parseInt(itemPostViewHolder.itemcount.getText().toString());
                                count03 = count03 + 1;
                                itemPostViewHolder.itemcount.setText(String.valueOf(count03));
                                itemPostViewHolder.price.setText(String.valueOf(price3*count03));
                                reference1.child(type).child("itemcount").setValue(itemPostViewHolder.itemcount.getText().toString());
                                reference1.child(type).child("price").setValue(String.valueOf(price3*count03));
                            }
                        }
                    });

                    itemPostViewHolder.minusone.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if (type.equals("beer"))
                            {
                                count0 = Integer.parseInt(itemPostViewHolder.itemcount.getText().toString());
                                count0 = count0 - 1;
                                if (count0 <= 0)
                                {
                                    reference1.child(type).setValue(null);
                                }
                                else
                                {
                                    itemPostViewHolder.itemcount.setText(String.valueOf(count0));
                                    itemPostViewHolder.price.setText(String.valueOf(price*count0));
                                    reference1.child(type).child("itemcount").setValue(itemPostViewHolder.itemcount.getText().toString());
                                    reference1.child(type).child("price").setValue(String.valueOf(price*count0));
                                }
                            }else if (type.equals("whisky"))
                            {
                                count01 = Integer.parseInt(itemPostViewHolder.itemcount.getText().toString());
                                count01 = count01 - 1;
                                if (count01 <= 0)
                                {
                                    reference1.child(type).removeValue();
                                }
                                else
                                {
                                    itemPostViewHolder.itemcount.setText(String.valueOf(count01));
                                    itemPostViewHolder.price.setText(String.valueOf(price1*count01));
                                    reference1.child(type).child("itemcount").setValue(itemPostViewHolder.itemcount.getText().toString());
                                    reference1.child(type).child("price").setValue(String.valueOf(price1*count01));
                                }
                            }else if (type.equals("rum"))
                            {
                                count02 = Integer.parseInt(itemPostViewHolder.itemcount.getText().toString());
                                count02 = count02 - 1;
                                if (count02 <= 0)
                                {
                                    reference1.child(type).removeValue();
                                }
                                else
                                {
                                    itemPostViewHolder.itemcount.setText(String.valueOf(count02));
                                    itemPostViewHolder.price.setText(String.valueOf(price2*count02));
                                    reference1.child(type).child("itemcount").setValue(itemPostViewHolder.itemcount.getText().toString());
                                    reference1.child(type).child("price").setValue(String.valueOf(price2*count02));
                                }
                            }else if (type.equals("vodka"))
                            {
                                count03 = Integer.parseInt(itemPostViewHolder.itemcount.getText().toString());
                                count03 = count03 - 1;
                                if (count03 <= 0)
                                {
                                    reference1.child(type).removeValue();
                                }
                                else
                                {
                                    itemPostViewHolder.itemcount.setText(String.valueOf(count03));
                                    itemPostViewHolder.price.setText(String.valueOf(price3*count03));
                                    reference1.child(type).child("itemcount").setValue(itemPostViewHolder.itemcount.getText().toString());
                                    reference1.child(type).child("price").setValue(String.valueOf(price3*count03));
                                }
                            }
                        }
                    });

                }
                @NonNull
                @Override
                public ItemPostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_cardview, parent, false);

                    return new ItemPostViewHolder(view);
                }
            };

        }catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), "No data found",Toast.LENGTH_SHORT).show();
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter.startListening();
        recyclerView.setAdapter(adapter);


        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SucessPageActivity.class));
            }
        });

    }
}
