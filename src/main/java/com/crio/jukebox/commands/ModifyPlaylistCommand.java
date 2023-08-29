package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.exceptions.PlaylistNotFoundException;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.exceptions.UnAuthorizedException;
import com.crio.jukebox.services.IPlaylistService;

public class ModifyPlaylistCommand implements ICommand {
    private IPlaylistService playlistService;

    public ModifyPlaylistCommand(IPlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @Override
    public void execute(List<String> tokens) {
        String command = tokens.get(1);
        Playlist playlist;
        try {
            if (command.equals("ADD-SONG")) {
                playlist = playlistService.modifyAdd(tokens.get(2), tokens.get(3),
                        tokens.subList(4, tokens.size()));
            } else {
                playlist = playlistService.modifyDelete(tokens.get(2), tokens.get(3),
                        tokens.subList(4, tokens.size()));
            }
            System.out.println(playlist.toString());
        } catch (PlaylistNotFoundException | UnAuthorizedException | SongNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

}
