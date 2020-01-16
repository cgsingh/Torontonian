package com.example.chris.torontonian;

import java.util.Vector;

/**
 * This class stores a static list of POI objects, added to by the XML parser
 */

public class POIData {
    static Vector<POI> listOfPOI = new Vector<POI>();

    static Vector<Vector<POI>> categorizedPOI = new Vector<Vector<POI>>(13);

    static Vector<FavoriteItem> localFavList = new Vector<FavoriteItem>();

    public static int favoriteContains(int id)
    {
        int count = 0;
        for (FavoriteItem item : localFavList)
        {
            if (item.id == id)
            {
                return count;
            }
            count++;
        }
        return -1;
    }

    public static void populateLists()
    {
        for (int i = 0; i < 13; i++)
        {
            categorizedPOI.add(new Vector<POI>());
        }

        for (POI poi : listOfPOI)
        {
            if (poi.category.equals("Attraction"))
            {
                categorizedPOI.elementAt(0).add(poi);
            }
            if (poi.category.equals("Beach"))
            {
                categorizedPOI.elementAt(1).add(poi);
            }
            if (poi.category.equals("Museum"))
            {
                categorizedPOI.elementAt(2).add(poi);
            }
            if (poi.category.equals("Convention and Trade Centres"))
            {
                categorizedPOI.elementAt(3).add(poi);
            }
            if (poi.category.equals("Featured Park"))
            {
                categorizedPOI.elementAt(4).add(poi);
            }
            if (poi.category.equals("Gallery"))
            {
                categorizedPOI.elementAt(5).add(poi);
            }
            if (poi.category.equals("Garden / Conservatory"))
            {
                categorizedPOI.elementAt(6).add(poi);
            }
            if (poi.category.equals("Landmark"))
            {
                categorizedPOI.elementAt(7).add(poi);
            }
            if (poi.category.equals("Performing Arts"))
            {
                categorizedPOI.elementAt(8).add(poi);
            }
            if (poi.category.equals("Shopping"))
            {
                categorizedPOI.elementAt(9).add(poi);
            }
            if (poi.category.equals("Transportation"))
            {
                categorizedPOI.elementAt(10).add(poi);
            }
            if (poi.category.equals("Visitor Information"))
            {
                categorizedPOI.elementAt(11).add(poi);
            }
            if (poi.category.equals("Sports / Entertainment Venue"))
            {
                categorizedPOI.elementAt(12).add(poi);
            }
        }
    }
}
