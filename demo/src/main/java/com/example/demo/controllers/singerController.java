package com.example.demo.controllers;

import com.example.demo.entities.Agent;
import com.example.demo.entities.Genre;
import com.example.demo.entities.Singer;
import com.example.demo.entities.Song;
import com.example.demo.responses.ResponseHandler;
import com.example.demo.services.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class singerController {

    @Autowired
    SingerService singerService;

    @GetMapping("/singers")
    public ResponseEntity<Object> findAllSingers() {
        try {
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, singerService.findAll());
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping("/singers/{id}")
    public ResponseEntity<Object> findSingerById(@PathVariable Integer id) {
        try {
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, singerService.findById(id));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PostMapping("/singers")
    public ResponseEntity<Object> addSinger(@RequestBody Singer singer) {
        try {
            return ResponseHandler.generateResponse("Singer added successfully", HttpStatus.CREATED, singerService.saveSinger(singer));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @DeleteMapping("/singers/{id}")
    public ResponseEntity<Object> deleteSinger(@PathVariable Integer id) {
        try {
            Singer singer = singerService.findById(id);
            if (singer != null) {
                singerService.deleteSinger(singer);
                return ResponseHandler.generateResponse("Singer deleted successfully", HttpStatus.OK, singer);
            }
            return ResponseHandler.generateResponse("Singer not found", HttpStatus.NOT_FOUND, null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PutMapping("/singers/{id}")
    public ResponseEntity<Object> updateSinger(@PathVariable Integer id, @RequestBody Singer singer) {
        try {
            singer.setId(id);
            return ResponseHandler.generateResponse("Singer updated successfully", HttpStatus.OK, singerService.updateSinger(singer));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping("/singers/{id}/songs")
    public ResponseEntity<Object> getSongsBySingerId(@PathVariable Integer id) {
        try {
            List<Song> songs = singerService.getSongsBySingerId(id);
            return ResponseHandler.generateResponse("Songs retrieved successfully", HttpStatus.OK, songs);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping("/singers/{id}/genres")
    public ResponseEntity<Object> getGenresBySingerId(@PathVariable Integer id) {
        try {
            List<Genre> genres = singerService.getGenresBySingerId(id);
            return ResponseHandler.generateResponse("Genres retrieved successfully", HttpStatus.OK, genres);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping("/singers/{id}/agent")
    public ResponseEntity<Object> getAgentBySingerId(@PathVariable Integer id) {
        try {
            Agent agent = singerService.getAgentBySingerId(id);
            return ResponseHandler.generateResponse("Agent retrieved successfully", HttpStatus.OK, agent);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
