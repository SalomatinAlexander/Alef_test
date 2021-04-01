package com.example.alefapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class Photoctivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photoctivity);
        ImageView image = findViewById(R.id.image_act);
        Intent intent = getIntent();
        String url = intent.getStringExtra(PhotoRecycler.EXTRA_URL);
        Picasso.with(this).load(url).transform(new TransformImageForActivity()).into(image);
    }
}