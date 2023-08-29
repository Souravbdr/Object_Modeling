package com.crio.jukebox.services.implementations;

import java.util.List;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.exceptions.PlaylistNotFoundException;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.exceptions.UserNotFoundException;
import com.crio.jukebox.repositories.IPlaylistRepository;
import com.crio.jukebox.repositories.ISongRepository;
import com.crio.jukebox.repositories.IUserRepository;
import com.crio.jukebox.services.IUserService;

public class UserService implements IUserService {

    private IUserRepository userRepository;
    private IPlaylistRepository playlistRepository;
    private ISongRepository songRepository;

    public UserService(IUserRepository userRepository, IPlaylistRepository playlistRepository,
            ISongRepository songRepository) {
        this.userRepository = userRepository;
        this.playlistRepository = playlistRepository;
        this.songRepository = songRepository;
    }

    @Override
    public User create(String name) {
        return userRepository.save(new User(name));
    }

    @Override
    public Song playPlaylist(String id, String playlistId)
            throws SongNotFoundException, UserNotFoundException, PlaylistNotFoundException {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User Not Found"));
        user.setSongSequenceId(0);
        user.setCurrentPlaylistId(playlistId);
        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new PlaylistNotFoundException("No Playlist Found"));
        List<Song> songs = playlist.getSongs();
        if (songs.isEmpty()) {
            throw new SongNotFoundException("Playlist is Empty");
        }
        return songs.get(0);
    }

    @Override
    public Song playNextSong(String userId) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException());
        Playlist playlist = playlistRepository.findById(user.getCurrentPlaylistId()).get();
        user.setSongSequenceId((user.getSongSequenceId() + 1) % playlist.getSongs().size());
        return playlist.getSongs().get(user.getSongSequenceId());
    }

    @Override
    public Song playPrevSong(String userId) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException());
        Playlist playlist = playlistRepository.findById(user.getCurrentPlaylistId()).get();
        int playlistSize = playlist.getSongs().size();
        user.setSongSequenceId((playlistSize + user.getSongSequenceId() - 1) % playlistSize);
        return playlist.getSongs().get(user.getSongSequenceId());
    }

    @Override
    public Song playSong(String userId, String songId)
            throws SongNotFoundException, UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException());
        Playlist playlist = playlistRepository.findById(user.getCurrentPlaylistId()).get();
        Song song = songRepository.findById(songId).get();
        int songIndex = playlist.getSongs().indexOf(song);
        if (songIndex < 0) {
            throw new SongNotFoundException("Song Not Found in the current active playlist.");
        }
        user.setSongSequenceId(songIndex);
        return song;
    }


}
