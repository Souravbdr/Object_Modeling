package com.crio.jukebox.repositories.implementations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.repositories.IPlaylistRepository;

public class PlaylistRepository implements IPlaylistRepository{

    private final Map<String,Playlist> playlistMap;
    private Integer autoIncrement = 0;

    public PlaylistRepository(){
        playlistMap = new HashMap<>();
    }

    @Override
    public Playlist save(Playlist entity) {
        if(playlistMap.get(entity.getId())==null){
            ++autoIncrement;
            entity = new Playlist(autoIncrement.toString(),entity.getName(),entity.getSongs(),entity.getUserId());
        }
        playlistMap.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public List<Playlist> findAll() {
        return new ArrayList<>(playlistMap.values());
    }

    @Override
    public Optional<Playlist> findById(String id) {
        return Optional.ofNullable(playlistMap.get(id));
    }

    @Override
    public boolean existsById(String id) {
        return playlistMap.containsKey(id);
    }

    @Override
    public void deleteById(String id) {
        playlistMap.remove(id);
    }

    @Override
    public void delete(Playlist entity) {
        playlistMap.remove(entity.getId());
    }

    @Override
    public long count() {
        return playlistMap.size();
    }
    
}
