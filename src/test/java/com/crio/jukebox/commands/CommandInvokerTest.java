package com.crio.jukebox.commands;

import static org.mockito.ArgumentMatchers.anyList;
import java.util.ArrayList;
import com.crio.jukebox.exceptions.NoSuchCommandException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("CommandInvokerTest")
@ExtendWith(MockitoExtension.class)
public class CommandInvokerTest {
    private CommandInvoker commandInvoker;

    @Mock
    private CreatePlaylistCommand createPlaylistCommand;

    @Mock
    private CreateUserCommand createUserCommand;

    @Mock
    private DeletePlaylistCommand deletePlaylistCommand;

    @Mock
    private LoadSongsCommand loadSongsCommand;

    @Mock
    private ModifyPlaylistCommand modifyPlaylistCommand;

    @Mock
    private PlayPlaylistCommand playPlaylistCommand;

    @Mock
    private PlaySongCommand playSongCommand;

    @BeforeEach
    void setup(){
        commandInvoker = new CommandInvoker();
        commandInvoker.registerCommand("LOAD-DATA", loadSongsCommand);
        commandInvoker.registerCommand("CREATE-USER", createUserCommand);
        commandInvoker.registerCommand("CREATE-PLAYLIST", createPlaylistCommand);
        commandInvoker.registerCommand("DELETE-PLAYLIST", deletePlaylistCommand);
        commandInvoker.registerCommand("MODIFY-PLAYLIST", modifyPlaylistCommand);
        commandInvoker.registerCommand("PLAY-PLAYLIST", playPlaylistCommand);
        commandInvoker.registerCommand("PLAY-SONG", playSongCommand);
    }

    @Test
    @DisplayName("executeCommand method Should Execute Command Given CommandName and List of tokens")
    void executeCommandTest(){
        commandInvoker.executeCommand("LOAD-DATA",anyList());
        commandInvoker.executeCommand("CREATE-USER",anyList());
        commandInvoker.executeCommand("CREATE-PLAYLIST",anyList());
        commandInvoker.executeCommand("DELETE-PLAYLIST",anyList());
        commandInvoker.executeCommand("MODIFY-PLAYLIST",anyList());
        commandInvoker.executeCommand("PLAY-PLAYLIST",anyList());
        commandInvoker.executeCommand("PLAY-SONG",anyList());
    }
    @Test
    @DisplayName("executeCommand method Should Throw Exception Given Incorrect CommandName and List of tokens")
    public void executeCommand_GivenIncorrectNameAndTokens_ShouldThrowException() {
        //Act and Assert
        Assertions.assertThrows(NoSuchCommandException.class,() -> commandInvoker.executeCommand("RANDOM-COMMAND",new ArrayList<String>()));

    }

}
