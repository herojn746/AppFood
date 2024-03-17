package com.loginform.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.loginform.Activity.BinhLuanActivity;
import com.loginform.Activity.CTQuanAn;
import com.loginform.Class.NguoiDung;
import com.loginform.Database.EateryDbHelper;
import com.loginform.Class.Eatery;
import com.loginform.R;

import java.util.List;
import java.util.Locale;

public class EateryAdapter extends RecyclerView.Adapter<EateryAdapter.EateryViewHolder> {
    public List<Eatery> eateryList;

    EateryDbHelper dbHelper;
    public Context context;
    public EateryAdapter(List<Eatery> eateryList, Context context) {
        this.eateryList = eateryList;
        this.context = context;
    }

    @NonNull
    @Override
    public EateryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_food, parent, false);
        return new EateryViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull EateryViewHolder holder, int position) {
        Eatery eatery = eateryList.get(position);
        holder.nameTextView.setText(eatery.getName());
        holder.addressTextView.setText(eatery.getAddress());
        holder.ratingBar.setRating(eatery.getStarRating());
        holder.descriptionView.setText(eatery.getDescription());

        byte[] imageData = eatery.getImage();
        if (imageData != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.length);
            holder.imageView.setImageBitmap(bitmap);
        }

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent intent=new Intent(context, CTQuanAn.class);
                intent.putExtra("MaQA",eatery.MaQA);
                context.startActivity(intent);







            }
        });

        holder.addressTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uri = String.format(Locale.ENGLISH, "geo:%f,%f?q=%s", eatery.getLatitude(), eatery.getLonglatitude(), eatery.getAddress());
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                intent.setPackage("com.google.android.apps.maps");
                context.startActivity(intent);
            }
        });

    }
    @Override
    public int getItemCount() {
        return eateryList.size();
    }

    public class EateryViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView nameTextView;
        private TextView addressTextView;
        private TextView descriptionView;
        private RatingBar ratingBar;
        public EateryViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView  = itemView.findViewById(R.id.eatery_image_view);
            nameTextView = itemView.findViewById(R.id.eatery_name_text_view);
            addressTextView = itemView.findViewById(R.id.eatery_address_text_view);
            descriptionView =  itemView.findViewById(R.id.eatery_description_text_view);
            ratingBar = itemView.findViewById(R.id.eatery_rating_bar);
        }
    }

}

