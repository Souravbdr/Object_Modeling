package com.crio.jukebox.entities;

public class User {
    private String id;
    private String name;
    private Integer songSequenceId;
    private String currentPlaylistId;

    public User(String id, String name){
        this(name);
        this.id = id;
    }
    
    public User(String name){
        this.name = name;
        this.songSequenceId = 0;
        this.currentPlaylistId = "0";
    }
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getSongSequenceId() {
        return songSequenceId;
    }

    public void setSongSequenceId(Integer songSequenceId) {
        this.songSequenceId = songSequenceId;
    }

    public String getCurrentPlaylistId() {
        return currentPlaylistId;
    }

    public void setCurrentPlaylistId(String currentPlaylistId) {
        this.currentPlaylistId = currentPlaylistId;
    }

    @Override
    public String toString() {
        return id + " " + name;
    }
    

}
