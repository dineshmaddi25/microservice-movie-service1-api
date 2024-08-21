package com.dineshmaddi.movie_service.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.*;


@Entity // means hibernate move ayetappdudu table la convert avithadi
@Data
public class movie {
    @Id // to mention this primary key or identfy we shoul give @ID anotation
    @GeneratedValue
    private long id;
    private String name;
    private String director;
    @ElementCollection  // we need to save in daatbase as collection right
    private List<String> actors = new ArrayList<>();


}
