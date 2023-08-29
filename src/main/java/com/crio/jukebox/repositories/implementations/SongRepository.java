package com.crio.jukebox.repositories.implementations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.repositories.ISongRepository;

public class SongRepository implements ISongRepository{
    private final Map<String,Song> songsMap;
    private Integer autoIncrement = 0;

    public SongRepository(){
        songsMap = new HashMap<>();
    }

    @Override
    public Song save(Song entity) {
        if(songsMap.get(entity.getId())==null){
            ++autoIncrement;
            entity = new Song(autoIncrement.toString(),entity.getName(),entity.getGenre(), entity.getAlbum(), entity.getSinger(),entity.getArtist());
        }
        songsMap.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public List<Song> findAll() {
        return new ArrayList<>(songsMap.values());
    }

    @Override
    public Optional<Song> findById(String id) {
        return Optional.ofNullable(songsMap.get(id));
    }

    @Override
    public boolean existsById(String id) {
        return songsMap.containsKey(id);
    }

    @Override
    public void deleteById(String id) {
        songsMap.remove(id);
    }

    @Override
    public void delete(Song entity) {
        songsMap.remove(entity.getId());
    }

    @Override
    public long count() {
        return songsMap.size();
    }
    
}
