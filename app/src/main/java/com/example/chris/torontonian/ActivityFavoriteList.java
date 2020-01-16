package com.example.chris.torontonian;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ActivityFavoriteList extends AppCompatActivity {

    int catID = 0;
    ArrayList<String> categoryPOI = new ArrayList<String>();
    ArrayList<Integer> idPOI = new ArrayList<Integer>();
    ListView listView = null;

    public void setupListAdapter()
    {
        categoryPOI.clear();
        idPOI.clear();

        int i = 0;
        for (FavoriteItem item : POIData.localFavList)
        {
            if (item.catid == catID)
            {
                categoryPOI.add(POIData.listOfPOI.get(item.id).title);
                idPOI.add(item.id);
            }
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, categoryPOI);
        listView.setAdapter(arrayAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_list);
        catID = getIntent().getExtras().getInt("catID");
        setTitle("Torontonian (" + POIData.categorizedPOI.elementAt(catID).firstElement().category + ")");
        listView = (ListView) findViewById(R.id.listView);

        setupListAdapter();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ActivityFavoriteList.this, ActivityPlaceInfo.class);
                intent.putExtra("poiID", idPOI.get(position));
                intent.putExtra("catID", catID);
                startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1) {
            setupListAdapter();
        }
    }
}
