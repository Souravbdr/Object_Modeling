package com.crio.jukebox.commands;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.services.implementations.PlaylistService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("CreatePlaylistCommandTest")
@ExtendWith(MockitoExtension.class)
public class CreatePlaylistCommandTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Mock
    PlaylistService playlistService;

    @InjectMocks
    CreatePlaylistCommand createPlaylistCommand;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @DisplayName("The create playlist command should print playlist id")
    void CreatePlaylistCommandExecuteTest(){
        
        Playlist playlist = new Playlist("1","2",new ArrayList<>(),"2");
        when(playlistService.create(anyString(),anyString(), anyList())).thenReturn(playlist);
        createPlaylistCommand.execute(List.of("CREATE-PLAYLIST","1","2","1","2"));
        String expectedOutput = "Playlist ID - 1";
        //Assert
        Assertions.assertEquals(expectedOutput, outputStreamCaptor.toString().trim());

        verify(playlistService,times(1)).create(anyString(),anyString(),anyList());
    }

    @Test
    @DisplayName("The create playlist command should throw SongNotFoundException")
    void CreatePlaylistCommandExecute_SongNotFoundTest(){
        doThrow(new SongNotFoundException("Song not found")).when(playlistService).create(anyString(),anyString(), anyList());
        createPlaylistCommand.execute(List.of("CREATE-PLAYLIST","1","2","1","2"));
        String expectedOutput = "Song not found";
        //Assert
        Assertions.assertEquals(expectedOutput, outputStreamCaptor.toString().trim());

        verify(playlistService,times(1)).create(anyString(),anyString(),anyList());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
