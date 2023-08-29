package com.crio.jukebox.entities;

import java.util.List;
import java.util.stream.Collectors;

public class Playlist {

    private String id;
    private String name;
    private List<Song> songs;
    private String userId;

    public Playlist(String id, String name, List<Song> songs, String userId){
        this(name,songs,userId);
        this.id = id;
    }
    
    public Playlist(String name, List<Song> songs, String userId){
        this.name = name;
        this.songs = songs;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "Playlist ID - " + id 
                + "\nPlaylist Name - " + name 
                + "\nSong IDs - " + songs.stream().map(e->e.getId()).collect(Collectors.joining(" "));
    }
    
    
}
