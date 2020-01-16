package com.example.chris.torontonian;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    public void populateLocalFavoriteList()
    {
        MyDbHelper dbHelper = new MyDbHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] query_columns = {
                MyDbHelper.MyDataEntry._ID,
                MyDbHelper.MyDataEntry.LOC_ID_COLUMN,
                MyDbHelper.MyDataEntry.LOC_CAT_ID_COLUMN
        };

        String selectQuery = MyDbHelper.MyDataEntry.LOC_ID_COLUMN + " = ?";
        String[] selectionArgs = {" Filter string "};
        String sortOrder = MyDbHelper.MyDataEntry.LOC_ID_COLUMN + " DESC";

        Cursor cursor = db.query(
                MyDbHelper.MyDataEntry.TABLE_NAME,
                query_columns,
                null,
                null,
                null,
                null,
                sortOrder
        );

        boolean hasMoreData = cursor.moveToFirst();
        while (hasMoreData) {
            long key = cursor.getLong(cursor.getColumnIndexOrThrow(MyDbHelper.MyDataEntry._ID));
            int locID = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(MyDbHelper.MyDataEntry.LOC_ID_COLUMN)));
            int locCatId = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(MyDbHelper.MyDataEntry.LOC_CAT_ID_COLUMN)));
            POIData.localFavList.add(new FavoriteItem(locID, locCatId));
            System.out.println("local fav: " + locID + " - " + locCatId);
            hasMoreData = cursor.moveToNext();
        }
    }
    // a one time (per application launch) parsing of the local XML file (poi.xml)
    public void parsePOIXML()
    {
        InputStreamReader inputStreamReader = null;
        InputStream inputStream = null;
        try {
            inputStream = getAssets().open("poi.xml");
            inputStreamReader = new InputStreamReader(inputStream);
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(inputStreamReader);
            int event = xpp.getEventType();
            String tagName = "";
            POI tempPOI = new POI();
            boolean startingTag = false;
            while (event != XmlPullParser.END_DOCUMENT)
            {
                if (event == XmlPullParser.START_TAG)
                {
                    startingTag = true;
                    tagName = xpp.getName();
                }
                else if (event == XmlPullParser.END_TAG)
                {
                    startingTag = false;
                    tagName = xpp.getName();
                    if (tagName.equals("Item-item"))
                    {
                        POIData.listOfPOI.add(new POI(tempPOI.address, tempPOI.postalCode, tempPOI.title, tempPOI.description, tempPOI.category, tempPOI.phone, tempPOI.URL));
                    }
                }
                else if (event == XmlPullParser.TEXT)
                {
                    String text = xpp.getText();
                    if (startingTag)
                    {
                        if (tagName.equals("Address"))
                        {
                            tempPOI.address = text;
                        }
                        if (tagName.equals("PostalCode"))
                        {
                            tempPOI.postalCode = text;
                        }
                        if (tagName.equals("Title"))
                        {
                            tempPOI.title = text;
                        }
                        if (tagName.equals("Description"))
                        {
                            tempPOI.description = text;
                        }
                        if (tagName.equals("Category"))
                        {
                            tempPOI.category = text;
                        }
                        if (tagName.equals("Phone"))
                        {
                            tempPOI.phone = text;
                        }
                        if (tagName.equals("URL"))
                        {
                            tempPOI.URL = text;
                        }
                    }
                }
                event = xpp.next();
            }

        } catch (Exception e) {
            Log.d("An exception happened", e.getMessage());
        }
        POIData.populateLists();
    }

    public void launchListActivity(int catID)
    {
        Intent intent = new Intent(MainActivity.this, ActivityList.class);
        intent.putExtra("catID", catID);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        populateLocalFavoriteList();
        parsePOIXML();

        ImageButton favoritesBtn = (ImageButton) findViewById(R.id.favoritesBtn);
        favoritesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityFavoriteCategory.class);
                startActivity(intent);
            }
        });

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
}