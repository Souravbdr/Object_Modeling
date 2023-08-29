package com.crio.jukebox.services.implementations;

import java.util.ArrayList;
import java.util.List;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.exceptions.PlaylistNotFoundException;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.exceptions.UnAuthorizedException;
import com.crio.jukebox.repositories.IPlaylistRepository;
import com.crio.jukebox.repositories.ISongRepository;
import com.crio.jukebox.services.IPlaylistService;

public class PlaylistService implements IPlaylistService{

    private IPlaylistRepository playlistRepository;
    private ISongRepository songRepository;

    public PlaylistService(IPlaylistRepository playlistRepository, ISongRepository songRepository) {
        this.playlistRepository = playlistRepository;
        this.songRepository = songRepository;
    }

    @Override
    public Playlist create(String name, String userId, List<String> songsId) throws SongNotFoundException{
        List<Song> songs = new ArrayList<>();
        for(String id:songsId){
            Song song = songRepository.findById(id).orElseThrow(()->new SongNotFoundException("Some Requested Songs Not Available. Please try again. ID->"+id));
            songs.add(song);
        }
        return playlistRepository.save(new Playlist(name, songs, userId));
    }

    @Override
    public void delete(String userId, String id) throws PlaylistNotFoundException {
        Playlist playlist = playlistRepository.findById(id).orElseThrow(()->new PlaylistNotFoundException("Playlist Not Found"));
        if(playlist.getUserId().equals(userId)){
            playlistRepository.deleteById(id);
        }
    }

    @Override
    public Playlist modifyAdd(String userId, String id, List<String> songs) throws PlaylistNotFoundException, UnAuthorizedException, SongNotFoundException{
        Playlist playlist = playlistRepository.findById(id).orElseThrow(()->new PlaylistNotFoundException());
        if(!playlist.getUserId().equals(userId)){
            throw new UnAuthorizedException("User is not creator of this playlist");
        }
        for(String songId:songs){
            Song song = songRepository.findById(songId).orElseThrow(()-> new SongNotFoundException("Some Requested Songs Not Available. Please try again."));
            if(!playlist.getSongs().contains(song)){
                playlist.getSongs().add(song);    
                playlistRepository.save(playlist);
            }
        }
        return playlist;
    }

    @Override
    public Playlist modifyDelete(String userId, String id, List<String> songs) throws SongNotFoundException{
        
        Playlist playlist = playlistRepository.findById(id).orElseThrow(()->new PlaylistNotFoundException());
        if(playlist.getUserId().equals(userId)){
            for(String songId:songs){
                Song song = songRepository.findById(songId).orElseThrow(()->new SongNotFoundException());
                int index = playlist.getSongs().indexOf(song);
                if(index>=0){
                    playlist.getSongs().remove(index);    
                    playlistRepository.save(playlist);
                }
            }
        }
        return playlist;
    }
}
