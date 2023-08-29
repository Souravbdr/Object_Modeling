package com.crio.jukebox.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import com.crio.jukebox.repositories.implementations.SongRepository;
import com.crio.jukebox.services.implementations.SongService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SongServiceTest {
    
    @Mock
    SongRepository songRepository;

    @InjectMocks
    SongService songService;

    @Test
    void loadSongTest(){
        songService.loadSongs("songs.csv");
        verify(songRepository,times(30)).save(any());
    }
}
