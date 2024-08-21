package com.dineshmaddi.movie_service.api;

import com.dineshmaddi.movie_service.model.movie;
import com.dineshmaddi.movie_service.service.movieservice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
@Slf4j
public class Moviecontroller {
    @Autowired
    private movieservice Movieservice;
    @RequestMapping("/{id}")
    public ResponseEntity<movie> getMovie(@PathVariable Long id){
        movie mov = Movieservice.read(id);
        log.info("Returned Movie With id:"+id);
        return ResponseEntity.ok(mov);
    }
    @PostMapping
    public ResponseEntity<movie> createMovie(@RequestBody movie mov){
        movie createdMovie = Movieservice.create(mov);
        log.info("Created Movie With id:"+createdMovie.getId());
        return ResponseEntity.ok(createdMovie);
    }
    @PutMapping("/{id}")
    public void updateMovie(@PathVariable Long id,@RequestBody movie mov){
        Movieservice.update(mov,id);
        log.info("Updated Movie With id:"+id);
    }
    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable Long id){
        Movieservice.delete(id);
        log.info("Deleted Movie With id:"+id);
    }




}


