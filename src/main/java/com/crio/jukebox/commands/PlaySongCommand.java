package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.services.IUserService;

public class PlaySongCommand implements ICommand{
    private IUserService userService;

    public PlaySongCommand(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(List<String> tokens) {
        String subCommand = tokens.get(2);
        Song currentSong;
        try{
            if(subCommand.equals("BACK")){
                currentSong = userService.playPrevSong(tokens.get(1));
            }
            else if(subCommand.equals("NEXT")){
                currentSong = userService.playNextSong(tokens.get(1));
            }
            else{
                currentSong = userService.playSong(tokens.get(1),subCommand);
            }
            System.out.println("Current Song Playing");
            System.out.println(currentSong);
        } catch(SongNotFoundException e){
            System.out.println(e.getMessage());
        }

        
    }
    

}
