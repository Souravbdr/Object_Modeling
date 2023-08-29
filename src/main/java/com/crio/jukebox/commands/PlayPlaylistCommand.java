package com.crio.jukebox.commands;

import java.util.List;
import com.crio.codingame.exceptions.UserNotFoundException;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.exceptions.PlaylistNotFoundException;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.services.IUserService;

public class PlayPlaylistCommand implements ICommand{

    private IUserService userService;
   
    public PlayPlaylistCommand(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(List<String> tokens) {
        try{
            Song song = userService.playPlaylist(tokens.get(1), tokens.get(2));
            System.out.println(song.toString());
        } catch (UserNotFoundException | SongNotFoundException | PlaylistNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

}
