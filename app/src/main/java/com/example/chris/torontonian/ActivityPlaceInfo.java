package com.example.chris.torontonian;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.location.Geocoder;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.location.Address;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class ActivityPlaceInfo extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private POI poi = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_info);

        poi = POIData.listOfPOI.elementAt(getIntent().getExtras().getInt("poiID"));

        ImageButton earthBtn = (ImageButton) findViewById(R.id.imageButton);
        earthBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!poi.URL.isEmpty()) {
                    String updatedURL = poi.URL;
                    if (!poi.URL.contains("http"))
                    {
                        updatedURL = "http://" + poi.URL;
                    }
                    Uri uri = Uri.parse(updatedURL);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
            }
        });

        final ImageButton favBtn = (ImageButton) findViewById(R.id.imageButton2);
        final int favIndex = POIData.favoriteContains(poi.id);
        if (favIndex != -1)
        {
            favBtn.setImageResource(R.drawable.unfavorites_icon);
        }
        else
        {
            favBtn.setImageResource(R.drawable.favorites_icon);
        }
        favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDbHelper dbHelper = new MyDbHelper(ActivityPlaceInfo.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                final int favIndex = POIData.favoriteContains(poi.id);
                if (favIndex != -1)
                {
                    favBtn.setImageResource(R.drawable.unfavorites_icon);
                }
                else
                {
                    favBtn.setImageResource(R.drawable.favorites_icon);
                }
                if (favIndex == -1) {
                    ContentValues newRow = new ContentValues();

                    newRow.put(MyDbHelper.MyDataEntry.LOC_ID_COLUMN, poi.id);
                    newRow.put(MyDbHelper.MyDataEntry.LOC_CAT_ID_COLUMN, getIntent().getExtras().getInt("catID"));
                    long newRowID = db.insert(MyDbHelper.MyDataEntry.TABLE_NAME, null, newRow);
                    POIData.localFavList.add(new FavoriteItem(poi.id, getIntent().getExtras().getInt("catID")));
                }
                else
                {
                    db.delete(MyDbHelper.MyDataEntry.TABLE_NAME, MyDbHelper.MyDataEntry.LOC_ID_COLUMN + "=" + poi.id, null);
                    POIData.localFavList.remove(favIndex);
                }
                db.close();
                if (favIndex == -1 ) { favBtn.setImageResource(R.drawable.unfavorites_icon); }
                if (favIndex != -1 ) { favBtn.setImageResource(R.drawable.favorites_icon); }
            }
        });

        TextView titleLbl = (TextView) findViewById(R.id.titleLbl);
        titleLbl.setText(poi.title);

        TextView descLbl = (TextView) findViewById(R.id.descLbl);
        descLbl.setText(poi.description);

        TextView addressLbl = (TextView) findViewById(R.id.addressLbl);
        String poiAddress = poi.address;
        if (!poi.postalCode.isEmpty())
        {
            poiAddress += ", " + poi.postalCode;
        }
        addressLbl.setText(poiAddress);

        TextView phoneLbl = (TextView) findViewById(R.id.phoneLbl);
        String poiPhone = poi.phone;
        if (poiPhone != null && !poiPhone.isEmpty())
        {
            phoneLbl.setText(poi.phone);
        }
        else
        {
            phoneLbl.setText("N/A");
        }


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        double latitude = 0;
        double longitude = 0;

        List<Address> geocodeMatches = null;

        try {
            String poiAddress = poi.address;
            if (!poi.postalCode.isEmpty())
            {
                poiAddress += ", " + poi.postalCode;
            }
            geocodeMatches = new Geocoder(this).getFromLocationName(poiAddress, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!geocodeMatches.isEmpty())
        {
            latitude = geocodeMatches.get(0).getLatitude();
            longitude = geocodeMatches.get(0).getLongitude();
        }

        if (latitude == 0 && longitude == 0)
        {
            try {
                String pCode = poi.postalCode;
                try {
                    if (pCode == null)
                    {
                        pCode = "";
                    }
                    geocodeMatches = new Geocoder(this).getFromLocationName(pCode, 1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (!geocodeMatches.isEmpty())
        {
            latitude = geocodeMatches.get(0).getLatitude();
            longitude = geocodeMatches.get(0).getLongitude();
        }

        LatLng poiLoc = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(poiLoc).title(poi.title));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(poiLoc, 16.0f));
    }
}
