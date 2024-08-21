package com.dineshmaddi.movie_service.service;

import com.dineshmaddi.movie_service.model.movie;
import com.dineshmaddi.movie_service.repo.movierepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class movieservice  {
    @Autowired
    private movierepo movrep;
    //CRUD OPERATION : CREATE READ UPDATE DELETE
    public movie create(movie m){
        if(m == null){
            throw new RuntimeException("Invalid Movie");
        }
        return movrep.save(m);// saving movie
    }
    public movie read(Long id){
       return  movrep.findById(id).orElseThrow(() -> new RuntimeException("Movie not found"));
    }
    public void update(movie updated, Long id){
        if( updated == null || id == null){
            throw new RuntimeException("Invalid Movie");
        }
        if(movrep.existsById(id))
        {
            movie Movie = movrep.getReferenceById(id);// no need to fetch full data table Row Reference only we Get
            Movie.setName(updated.getName());
            Movie.setDirector(updated.getDirector());
            Movie.setActors(updated.getActors());
            movrep.save(Movie);
        }else{
            throw new RuntimeException("Movie not found");
        }
    }
    public void delete(long id){
        if(movrep.existsById(id)){
            movrep.deleteById(id);
        }else{
            throw new RuntimeException("Movie not Found");
        }
    }


}
