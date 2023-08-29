package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.services.IPlaylistService;

public class CreatePlaylistCommand implements ICommand{
    private IPlaylistService playlistService;

    public CreatePlaylistCommand(IPlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @Override
    public void execute(List<String> tokens) {
        try{
            Playlist playlist = playlistService.create(tokens.get(2), tokens.get(1), tokens.subList(3,tokens.size()));
            System.out.println("Playlist ID - " + playlist.getId());
        } catch(SongNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    
}
