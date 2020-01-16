package com.example.chris.torontonian;

/**
 * This class stores a Place of Interest
 */

public class POI {
    String address;
    String postalCode;
    String title;
    String description;
    String category;
    String phone;
    String URL;
    int id;

    public POI()
    {
    }

    public POI(String address, String postalCode, String title, String description, String category, String phone, String URL)
    {
        id = POIData.listOfPOI.size();
        this.address = address;
        this.postalCode = postalCode;
        this.title = title;
        this.description = description;
        this.category = category;
        this.phone = phone;
        this.URL = URL;
    }
}
