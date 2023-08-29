package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.services.ISongService;

public class LoadSongsCommand implements ICommand{

    private ISongService songService;

    public LoadSongsCommand(ISongService songService){
        this.songService = songService;
    }

    @Override
    public void execute(List<String> tokens) {
        songService.loadSongs(tokens.get(1));
        System.out.println("Songs Loaded successfully");
    }
    
}
