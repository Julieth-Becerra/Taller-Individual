package com.example.demo.services;

import com.example.demo.entities.Singer;
import com.example.demo.entities.Song;
import com.example.demo.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongService {

    @Autowired
    private SongRepository songRepository;

    public List<Song> findAll(){
        return songRepository.findAll();
    }

    public Song saveSong (Song song, Singer singer){
        song.setSinger(singer);
        return songRepository.save(song);
    }

    public Song findById (Integer id){
        Optional<Song> optional = songRepository.findById(id);

        return optional.isPresent() ? optional.get() : null;
    }

    public Song updateSong(Song updatedSong) {
        Optional<Song> optional = songRepository.findById(updatedSong.getId());
        if (optional.isPresent()) {
            Song existingSong = optional.get();
            existingSong.setTitle(updatedSong.getTitle());
            existingSong.setDateRelease(updatedSong.getDateRelease());
            return songRepository.save(existingSong);
        }
        return null;
    }

    public void deleteSong(Song song) {
        if (song.getSinger() != null) {
            throw new RuntimeException("Cannot delete song because it is associated with a singer.");
        }
        songRepository.delete(song);
    }

}
