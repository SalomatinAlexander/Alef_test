package com.example.alefapp;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class PhotoRecycler extends RecyclerView.Adapter<PhotoRecycler.PhotoHolder> {
    ArrayList<String> list;
    public static String EXTRA_URL = "extra_url";

    PhotoRecycler(ArrayList<String> _list) {
        list = _list;
    }

    @NonNull
    @Override
    public PhotoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_layout, parent, false);

        return new PhotoHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return list.size() ;
    }

    class PhotoHolder extends RecyclerView.ViewHolder {
        ImageView photo1;


        public PhotoHolder(@NonNull View itemView) {
            super(itemView);
            photo1 = itemView.findViewById(R.id.photo_image1);

        }

        public void onBind(int position) {
            String s1 = list.get(position);
            Picasso.with(itemView.getContext()).load(s1).transform(new TransformImage()).into(photo1);
            photo1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), Photoctivity.class);
                    intent.putExtra(EXTRA_URL, s1);
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}

