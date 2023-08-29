package com.crio.jukebox.services;

import java.util.List;
import com.crio.jukebox.entities.Playlist;

public interface IPlaylistService {
    public Playlist create(String name, String userId, List<String> songs);
    public void delete(String userId, String id);
    public Playlist modifyAdd(String userId, String id, List<String> songs);
    public Playlist modifyDelete(String userId, String id, List<String> songs);
}
