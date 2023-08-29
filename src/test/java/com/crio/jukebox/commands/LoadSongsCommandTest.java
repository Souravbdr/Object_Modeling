package com.crio.jukebox.commands;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import com.crio.jukebox.services.implementations.SongService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("LoadSongsCommandTest")
@ExtendWith(MockitoExtension.class)
public class LoadSongsCommandTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Mock
    SongService songService;

    @InjectMocks
    LoadSongsCommand loadSongsCommand;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @DisplayName("The load song command should print successfull")
    void LoadSongCommandExecuteTest(){
        loadSongsCommand.execute(List.of("LOAD-DATA","pathToCSV"));
        String expectedOutput = "Songs Loaded successfully";
        //Assert
        Assertions.assertEquals(expectedOutput, outputStreamCaptor.toString().trim());

        verify(songService,times(1)).loadSongs("pathToCSV");
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
