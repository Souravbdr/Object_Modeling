package com.crio.jukebox.appConfig;

import com.crio.jukebox.commands.CommandInvoker;
import com.crio.jukebox.commands.CreatePlaylistCommand;
import com.crio.jukebox.commands.CreateUserCommand;
import com.crio.jukebox.commands.DeletePlaylistCommand;
import com.crio.jukebox.commands.LoadSongsCommand;
import com.crio.jukebox.commands.ModifyPlaylistCommand;
import com.crio.jukebox.commands.PlayPlaylistCommand;
import com.crio.jukebox.commands.PlaySongCommand;
import com.crio.jukebox.repositories.IPlaylistRepository;
import com.crio.jukebox.repositories.ISongRepository;
import com.crio.jukebox.repositories.IUserRepository;
import com.crio.jukebox.repositories.implementations.PlaylistRepository;
import com.crio.jukebox.repositories.implementations.SongRepository;
import com.crio.jukebox.repositories.implementations.UserRepository;
import com.crio.jukebox.services.IPlaylistService;
import com.crio.jukebox.services.ISongService;
import com.crio.jukebox.services.IUserService;
import com.crio.jukebox.services.implementations.PlaylistService;
import com.crio.jukebox.services.implementations.SongService;
import com.crio.jukebox.services.implementations.UserService;

public class ApplicationConfig {
    private final ISongRepository songRepository = new SongRepository();
    private final IUserRepository userRepository = new UserRepository();
    private final IPlaylistRepository playlistRepository = new PlaylistRepository();

    private final ISongService songService = new SongService(songRepository);
    private final IUserService userService = new UserService(userRepository, playlistRepository, songRepository);
    private final IPlaylistService playlistService = new PlaylistService(playlistRepository, songRepository);

    private final LoadSongsCommand loadSongsCommand = new LoadSongsCommand(songService);
    private final CreateUserCommand createUserCommand = new CreateUserCommand(userService);
    private final CreatePlaylistCommand createPlaylistCommand = new CreatePlaylistCommand(playlistService);
    private final DeletePlaylistCommand deletePlaylistCommand = new DeletePlaylistCommand(playlistService);
    private final ModifyPlaylistCommand modifyPlaylistCommand = new ModifyPlaylistCommand(playlistService);
    private final PlayPlaylistCommand playPlaylistCommand = new PlayPlaylistCommand(userService);
    private final PlaySongCommand playSongCommand = new PlaySongCommand(userService);

    private final CommandInvoker commandInvoker = new CommandInvoker();

    public CommandInvoker getCommandInvoker(){
        commandInvoker.registerCommand("LOAD-DATA", loadSongsCommand);
        commandInvoker.registerCommand("CREATE-USER", createUserCommand);
        commandInvoker.registerCommand("CREATE-PLAYLIST", createPlaylistCommand);
        commandInvoker.registerCommand("DELETE-PLAYLIST", deletePlaylistCommand);
        commandInvoker.registerCommand("MODIFY-PLAYLIST", modifyPlaylistCommand);
        commandInvoker.registerCommand("PLAY-PLAYLIST", playPlaylistCommand);
        commandInvoker.registerCommand("PLAY-SONG", playSongCommand);
        return commandInvoker;
    }
}
