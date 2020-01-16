package com.example.chris.torontonian;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ActivityFavoriteCategory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_category);
        setTitle("Torontonian (Favorites)");

        ImageButton attractionBtn = (ImageButton) findViewById(R.id.attractionBtn);
        attractionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchListActivity(0);
            }
        });

        ImageButton beachBtn = (ImageButton) findViewById(R.id.beachBtn);
        beachBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchListActivity(1);
            }
        });

        ImageButton museumBtn = (ImageButton) findViewById(R.id.museumBtn);
        museumBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchListActivity(2);
            }
        });

        ImageButton tradeBtn = (ImageButton) findViewById(R.id.conventionBtn);
        tradeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchListActivity(3);
            }
        });

        ImageButton parkBtn = (ImageButton) findViewById(R.id.parkBtn);
        parkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchListActivity(4);
            }
        });

        ImageButton galleryBtn = (ImageButton) findViewById(R.id.galleryBtn);
        galleryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchListActivity(5);
            }
        });

        ImageButton gardenBtn = (ImageButton) findViewById(R.id.gardenBtn);
        gardenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchListActivity(6);
            }
        });

        ImageButton landmarkBtn = (ImageButton) findViewById(R.id.landmarkBtn);
        landmarkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchListActivity(7);
            }
        });

        ImageButton artsBtn = (ImageButton) findViewById(R.id.artsBtn);
        artsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchListActivity(8);
            }
        });

        ImageButton shoppingBtn = (ImageButton) findViewById(R.id.shoppingBtn);
        shoppingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchListActivity(9);
            }
        });

        ImageButton transportBtn = (ImageButton) findViewById(R.id.transportBtn);
        transportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchListActivity(10);
            }
        });

        ImageButton visitorBtn = (ImageButton) findViewById(R.id.visitorsBtn);
        visitorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchListActivity(11);
            }
        });

        ImageButton sportsBtn = (ImageButton) findViewById(R.id.entertainBtn);
        sportsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchListActivity(12);
            }
        });
    }

    public void launchListActivity(int catID)
    {
        Intent intent = new Intent(ActivityFavoriteCategory.this, ActivityFavoriteList.class);
        intent.putExtra("catID", catID);
        startActivity(intent);
    }
}
