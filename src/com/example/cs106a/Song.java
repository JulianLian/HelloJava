package com.example.cs106a;

/**
 * Created by Julian on 2017/4/8.
 */
public class Song {
    private final String name;
    private final String band;
    private double price;

    public Song(String songName, String bandName, double songPrice) {
        name = songName;
        band = bandName;
        price = songPrice;
    }

    public String getName() {
        return name;
    }

    public String getBand() {
        return band;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String toString() {
        return ("\""+ name + "\" by " + band + " costs $" + price );
    }
}
