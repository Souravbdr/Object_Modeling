package com.crio.jukebox.services.implementations;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.repositories.ISongRepository;
import com.crio.jukebox.services.ISongService;

public class SongService implements ISongService {

    private ISongRepository songRepository;

    public SongService(ISongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public void loadSongs(String path) {
        String line;
        String csvSplitBy = ",";
        
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(csvSplitBy);
                String id = data[0];
                String name = data[1];
                String genre = data[2];
                String album = data[3];
                String singer= data[4];
                List<String> artist = Arrays.asList(data[5].split("#"));
                
                Song song = new Song(id,name,genre,album,singer,artist);
                songRepository.save(song);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
