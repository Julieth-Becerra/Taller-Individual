package com.example.demo.controllers;

import com.example.demo.entities.Singer;
import com.example.demo.entities.Song;
import com.example.demo.responses.ResponseHandler;
import com.example.demo.services.SingerService;
import com.example.demo.services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("songs")
public class songController {

    @Autowired
    private SongService songService;
    @Autowired
    private SingerService singerService;

    @GetMapping
    public ResponseEntity<Object> findAll(){
        try {
            List<Song> result =  songService.findAll();
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, result);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping("/song/{id}")
    public ResponseEntity<Object> findSongById(@PathVariable Integer id) {
        try {
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, songService.findById(id));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<Object> saveSong (@RequestBody Song song, @PathVariable Integer id){
        try {
            Singer singer = singerService.findById(id);
            if (singer != null){
               Song result = songService.saveSong(song, singer);

                return ResponseHandler.generateResponse("Success Song", HttpStatus.CREATED, song);

            }
            return ResponseHandler.generateResponse("Success Song", HttpStatus.NOT_FOUND, null);

        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PutMapping("/updateSong/{id}")
    public ResponseEntity<Object> updateSong(@PathVariable Integer id, @RequestBody Song updatedSong) {
        try {
            updatedSong.setId(id); // Establece el ID de la canción actualizada
            Song updated = songService.updateSong(updatedSong);
            if (updated != null) {
                return ResponseHandler.generateResponse("Song updated successfully", HttpStatus.OK, updated);
            } else {
                return ResponseHandler.generateResponse("Song not found", HttpStatus.NOT_FOUND, null);
            }
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSong(@PathVariable Integer id) {
        try {
            Song song = songService.findById(id);
            if (song != null){

                songService.deleteSong(song);

                return ResponseHandler.generateResponse("Success Song", HttpStatus.ACCEPTED, song);

            }
            return ResponseHandler.generateResponse("Success Song", HttpStatus.NOT_FOUND, null);

        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
