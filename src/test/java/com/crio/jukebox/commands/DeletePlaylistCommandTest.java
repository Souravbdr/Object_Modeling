package com.crio.jukebox.commands;

import com.crio.jukebox.services.implementations.PlaylistService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("DeletePlaylistCommandTest")
@ExtendWith(MockitoExtension.class)
public class DeletePlaylistCommandTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Mock
    PlaylistService playlistService;

    @InjectMocks
    DeletePlaylistCommand deletePlaylistCommand;


    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @DisplayName("The Delete playlist command should delete successfull")
    void CreateUserCommandExecuteTest(){
        deletePlaylistCommand.execute(List.of("DELETE-PLAYLIST","1"));
        String expectedOutput = "Delete Successful";
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

}
