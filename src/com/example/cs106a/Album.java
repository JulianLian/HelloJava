package com.example.cs106a;

import java.util.ArrayList;

/**
 * Created by Julian on 2017/4/8.
 */
public class Album {
    private final String title;
    private final String band;
    private ArrayList<Song> songs = new ArrayList<>();

    public Album(String albumName, String bandName) {
        title = albumName;
        band = bandName;
    }

    public String getTitle() {
        return title;
    }

    public String getBand() {
        return band;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public String toString() {
        return ("\"" + title + " by " + band);
    }
}
