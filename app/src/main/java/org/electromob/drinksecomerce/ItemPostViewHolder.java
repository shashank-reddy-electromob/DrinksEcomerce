package org.electromob.drinksecomerce;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemPostViewHolder extends RecyclerView.ViewHolder {

    public TextView price,itemcount,type;
    public ImageView imagetype,plusone,minusone;

    public ItemPostViewHolder(@NonNull View itemView) {
        super(itemView);

        type = itemView.findViewById(R.id.type);
        price = itemView.findViewById(R.id.price);
        itemcount = itemView.findViewById(R.id.itemcount);
        imagetype = itemView.findViewById(R.id.imagetype);
        plusone = itemView.findViewById(R.id.plusone);
        minusone = itemView.findViewById(R.id.minusone);

    }
}
