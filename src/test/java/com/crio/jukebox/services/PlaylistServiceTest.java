package com.crio.jukebox.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.repositories.implementations.PlaylistRepository;
import com.crio.jukebox.repositories.implementations.SongRepository;
import com.crio.jukebox.services.implementations.PlaylistService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PlaylistServiceTest {
    
    @Mock
    PlaylistRepository playlistRepository;

    @Mock
    SongRepository songRepository;

    @InjectMocks
    PlaylistService playlistService;

    @Test
    void createTest(){
        Song song = new Song("1","name","genre","album","singer",List.of("artist"));
        when(songRepository.findById(anyString())).thenReturn(Optional.of(song));
        playlistService.create("name","userid",List.of("1"));
    }

    @Test
    void deleteTest(){
        Song song = new Song("1","name","genre","album","singer",List.of("artist"));
        Song song2 = new Song("2","name2","genre2","album2","singer2",List.of("artist2"));
        List<Song> songs = new ArrayList<Song>(){
            {
                add(song);
                add(song2);
            }
        };
        Playlist playlist = new Playlist("name", songs, "userId");
        when(playlistRepository.findById(anyString())).thenReturn(Optional.of(playlist));
        
        playlistService.delete("userId","id");
    }

    @Test
    void modifyAddTest(){
        Song song = new Song("1","name","genre","album","singer",List.of("artist"));
        Song song2 = new Song("2","name2","genre2","album2","singer2",List.of("artist2"));
        Song song3 = new Song("3","name3","genre3","album3","singer3",List.of("artist3"));
        List<Song> songs = new ArrayList<Song>(){
            {
                add(song);
                add(song2);
            }
        };
        Playlist playlist = new Playlist("name", songs, "userId");
        when(playlistRepository.findById(anyString())).thenReturn(Optional.of(playlist));
        when(songRepository.findById(anyString())).thenReturn(Optional.of(song3));
        assertEquals(3, playlistService.modifyAdd("userId","id",List.of("3")).getSongs().size());
    }

    @Test
    void modifyDeleteTest(){
        Song song = new Song("1","name","genre","album","singer",List.of("artist"));
        Song song2 = new Song("2","name2","genre2","album2","singer2",List.of("artist2"));
        List<Song> songs = new ArrayList<Song>(){
            {
                add(song);
                add(song2);
            }
        };
        Playlist playlist = new Playlist("name", songs, "userId");
        when(playlistRepository.findById(anyString())).thenReturn(Optional.of(playlist));
        when(songRepository.findById(anyString())).thenReturn(Optional.of(song2));
        assertEquals(1, playlistService.modifyDelete("userId","id",List.of("2")).getSongs().size());
    }
}
