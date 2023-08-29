package com.crio.jukebox.services;

import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.User;

public interface IUserService {
    
    public User create(String name);
    public Song playPlaylist(String userId, String id);
    public Song playNextSong(String userId);
    public Song playPrevSong(String userId);
    public Song playSong(String userId,String songId);
}
