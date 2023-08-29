package com.crio.jukebox.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.repositories.implementations.PlaylistRepository;
import com.crio.jukebox.repositories.implementations.SongRepository;
import com.crio.jukebox.repositories.implementations.UserRepository;
import com.crio.jukebox.services.implementations.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("UserServiceTest")
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    UserRepository userRepository;

    @Mock
    PlaylistRepository playlistRepository;

    @Mock
    SongRepository songRepository;
    
    @InjectMocks
    UserService userService;

    @Test
    void createTest(){
        User user = new User("Sourav");
        when(userRepository.save(any())).thenReturn(user);
        User actual = userService.create("Sourav");
        String expected = user.toString();
        assertEquals(expected, actual.toString());
    }

    @Test
    void PlayPlaylistTest(){
        User user = new User("Sourav");
        List<Song> songs = new ArrayList<Song>(){
            {
                add(new Song("1","name","genre","album","singer",List.of("artist")));
            }
        };
        Playlist playlist = new Playlist("name", songs, "userId");
        when(userRepository.findById(anyString())).thenReturn(Optional.of(user));
        when(playlistRepository.findById(anyString())).thenReturn(Optional.of(playlist));
        Song actual = userService.playPlaylist("1", "1");
        String expected = playlist.getSongs().get(0).toString();

        assertEquals(expected, actual.toString());

    }

    @Test
    void PlayNextSongTest(){
        User user = new User("Sourav");
        List<Song> songs = new ArrayList<Song>(){
            {
                add(new Song("1","name","genre","album","singer",List.of("artist")));
                add(new Song("2","name2","genre2","album2","singer2",List.of("artist2")));
            }
        };
        Playlist playlist = new Playlist("name", songs, "userId");
        when(userRepository.findById(anyString())).thenReturn(Optional.of(user));
        when(playlistRepository.findById(anyString())).thenReturn(Optional.of(playlist));
        Song actual = userService.playNextSong("1");
        String expected = playlist.getSongs().get(1).toString();

        assertEquals(expected, actual.toString());

    }

    @Test
    void PlayPrevSongTest(){
        User user = new User("Sourav");
        List<Song> songs = new ArrayList<Song>(){
            {
                add(new Song("1","name","genre","album","singer",List.of("artist")));
                add(new Song("2","name2","genre2","album2","singer2",List.of("artist2")));
            }
        };
        Playlist playlist = new Playlist("name", songs, "userId");
        when(userRepository.findById(anyString())).thenReturn(Optional.of(user));
        when(playlistRepository.findById(anyString())).thenReturn(Optional.of(playlist));
        Song actual = userService.playPrevSong("1");
        String expected = playlist.getSongs().get(1).toString();

        assertEquals(expected, actual.toString());

    }
    
    @Test
    void PlaySongTest(){
        User user = new User("Sourav");
        Song song = new Song("1","name","genre","album","singer",List.of("artist"));
        List<Song> songs = new ArrayList<Song>(){
            {
                add(song);
                add(new Song("2","name2","genre2","album2","singer2",List.of("artist2")));
            }
        };
        Playlist playlist = new Playlist("name", songs, "userId");
        when(userRepository.findById(anyString())).thenReturn(Optional.of(user));
        when(playlistRepository.findById(anyString())).thenReturn(Optional.of(playlist));
        when(songRepository.findById(anyString())).thenReturn(Optional.of(song));
        Song actual = userService.playSong("1","2");
        String expected = playlist.getSongs().get(0).toString();

        assertEquals(expected, actual.toString());

    }
}
