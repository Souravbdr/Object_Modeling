package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.exceptions.PlaylistNotFoundException;
import com.crio.jukebox.services.IPlaylistService;

public class DeletePlaylistCommand implements ICommand{
    private IPlaylistService playlistService;

    public DeletePlaylistCommand(IPlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @Override
    public void execute(List<String> tokens) {
        try{
            playlistService.delete(tokens.get(1), tokens.get(2));
            System.out.println("Delete Successful");
        } catch(PlaylistNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
    
}
