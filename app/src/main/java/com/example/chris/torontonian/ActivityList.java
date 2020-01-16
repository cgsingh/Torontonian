package com.example.chris.torontonian;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ActivityList extends AppCompatActivity {

    int catID = 0;
    String[] categoryPOI = null;
    int[] idPOI = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        catID = getIntent().getExtras().getInt("catID");
        setTitle("Torontonian (" + POIData.categorizedPOI.elementAt(catID).firstElement().category + ")");
        categoryPOI = new String[POIData.categorizedPOI.elementAt(catID).size()];
        idPOI = new int[POIData.categorizedPOI.elementAt(catID).size()];

        int i = 0;
        for (POI poi : POIData.categorizedPOI.elementAt(catID))
        {
            categoryPOI[i] = poi.title;
            idPOI[i++] = poi.id;
        }

        ListView listView = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, categoryPOI);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ActivityList.this, ActivityPlaceInfo.class);
                intent.putExtra("poiID", idPOI[position]);
                intent.putExtra("catID", catID);
                startActivity(intent);
            }
        });
    }
}
