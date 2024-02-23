package com.example.demo.services;

import com.example.demo.entities.Genre;
import com.example.demo.entities.Singer;
import com.example.demo.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    public Genre findById(Integer id) {
        Optional<Genre> optional = genreRepository.findById(id);
        return optional.orElse(null);
    }

    public Genre saveGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    public void deleteGenre(Integer genreId) {
        Genre genre = genreRepository.findById(genreId).orElse(null);
        if (genre != null) {
            List<Singer> singers = genre.getSingers();
            if (singers.isEmpty()) {
                genreRepository.delete(genre);
            } else {
                throw new RuntimeException("Cannot delete genre because it is associated with singers.");
            }
        } else {
            throw new RuntimeException("Genre not found with id: " + genreId);
        }
    }

    public Genre updateGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    public List<Singer> getSingersByGenreId(Integer genreId) {
        Optional<Genre> optionalGenre = genreRepository.findById(genreId);
        if (optionalGenre.isPresent()) {
            return optionalGenre.get().getSingers();
        } else {
            throw new RuntimeException("Genre with ID " + genreId + " not found.");
        }
    }
}
