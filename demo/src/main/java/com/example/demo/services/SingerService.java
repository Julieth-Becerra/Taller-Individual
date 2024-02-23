package com.example.demo.services;

import com.example.demo.entities.Agent;
import com.example.demo.entities.Genre;
import com.example.demo.entities.Singer;
import com.example.demo.entities.Song;
import com.example.demo.repositories.SingerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SingerService {

    @Autowired
    private SingerRepository singerRepository;

    public List<Singer> findAll() {
        return singerRepository.findAll();
    }

    public Singer findById(Integer id) {
        Optional<Singer> optional = singerRepository.findById(id);
        return optional.orElse(null);
    }

    public Singer saveSinger(Singer singer) {
        return singerRepository.save(singer);
    }

    public void deleteSinger(Singer singer) {
        if (!singer.getSongs().isEmpty() || singer.getAgent() != null || !singer.getGenres().isEmpty()) {
            throw new RuntimeException("Cannot delete singer because it has associations with other entities.");
        }
        singerRepository.delete(singer);
    }

    public Singer updateSinger(Singer singer) {
        return singerRepository.save(singer);
    }

    public List<Song> getSongsBySingerId(Integer singerId) {
        Optional<Singer> optionalSinger = singerRepository.findById(singerId);
        if (optionalSinger.isPresent()) {
            return optionalSinger.get().getSongs();
        } else {
            throw new RuntimeException("Singer with ID " + singerId + " not found.");
        }
    }

    public List<Genre> getGenresBySingerId(Integer singerId) {
        Optional<Singer> optionalSinger = singerRepository.findById(singerId);
        if (optionalSinger.isPresent()) {
            return optionalSinger.get().getGenres();
        } else {
            throw new RuntimeException("Singer with ID " + singerId + " not found.");
        }
    }

    public Agent getAgentBySingerId(Integer singerId) {
        Optional<Singer> optionalSinger = singerRepository.findById(singerId);
        if (optionalSinger.isPresent()) {
            return optionalSinger.get().getAgent();
        } else {
            throw new RuntimeException("Singer with ID " + singerId + " not found.");
        }
    }
}

