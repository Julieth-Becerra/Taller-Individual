package com.example.demo.controllers;

import com.example.demo.entities.Genre;
import com.example.demo.entities.Singer;
import com.example.demo.responses.ResponseHandler;
import com.example.demo.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class genreController {

    @Autowired
    private GenreService genreService;

    @GetMapping("/genres")
    public ResponseEntity<Object> findAllGenres() {
        try {
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, genreService.findAll());
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping("/genres/{id}")
    public ResponseEntity<Object> findGenreById(@PathVariable Integer id) {
        try {
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, genreService.findById(id));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PostMapping("/genres")
    public ResponseEntity<Object> addGenre(@RequestBody Genre genre) {
        try {
            return ResponseHandler.generateResponse("Genre added successfully", HttpStatus.CREATED, genreService.saveGenre(genre));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @DeleteMapping("/genres/{id}")
    public ResponseEntity<Object> deleteGenre(@PathVariable Integer id) {
        try {
            Genre genre = genreService.findById(id);
            if (genre != null) {
                genreService.deleteGenre(id);
                return ResponseHandler.generateResponse("Genre deleted successfully", HttpStatus.OK, genre);
            }
            return ResponseHandler.generateResponse("Genre not found", HttpStatus.NOT_FOUND, null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PutMapping("/genres/{id}")
    public ResponseEntity<Object> updateGenre(@PathVariable Integer id, @RequestBody Genre genre) {
        try {
            genre.setId(id); // Establece el ID en el género proporcionado
            return ResponseHandler.generateResponse("Genre updated successfully", HttpStatus.OK, genreService.updateGenre(genre));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping("/genres/{id}/singers")
    public ResponseEntity<Object> getSingersByGenreId(@PathVariable Integer id) {
        try {
            List<Singer> singers = genreService.getSingersByGenreId(id);
            return ResponseHandler.generateResponse("Singers retrieved successfully", HttpStatus.OK, singers);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
