package com.dineshmaddi.movie_service.api;

import com.dineshmaddi.movie_service.model.movie;
import com.dineshmaddi.movie_service.repo.movierepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;


import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest // Integration test, calling the entire application
@AutoConfigureMockMvc
class MovieControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    movierepo MovieRepo;

    @BeforeEach
    void cleanUp() {
        MovieRepo.deleteAll();
    }



    @Test
    void givenMovie_whenCreate_thenReturnMovie() throws Exception {
        movie Movie = new movie();
        Movie.setName("RRR");
        Movie.setDirector("SS Rajamouli");
        Movie.setActors(List.of("NTR", "Ram Charan", "Ajay Devgn"));

        ResultActions response = mockMvc.perform(post("/movies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(Movie))
        );

        // Then verify saved movie
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(notNullValue())))
                .andExpect(jsonPath("$.name", is(Movie.getName())))
                .andExpect(jsonPath("$.director", is(Movie.getDirector())))
                .andExpect(jsonPath("$.actors", is(Movie.getActors())));

    }
    @Test
    void givenSavedMovie_WhenUpdateMovie_thenMovieUpdatedInDb() throws Exception {

        // Given
        movie Movie = new movie();
        Movie.setName("rrr");
        Movie.setDirector("ss rajamouli");
        Movie.setActors(List.of("ntr", "ramcharan", "aliabhatt"));

        movie savedMovie = MovieRepo.save(Movie);
        Long id = savedMovie.getId();

        // update movie
        Movie.setActors(List.of("ntr", "ramcharan", "aliabhatt", "ajaydevgan"));

        var response = mockMvc.perform(put("/movies/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(Movie)));

        //Then verify updated movie
        response.andDo(print())
                .andExpect(status().isOk());

        var fetchResponse = mockMvc.perform(get("/movies/" + id));

        fetchResponse.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(Movie.getName())))
                .andExpect(jsonPath("$.director", is(Movie.getDirector())))
                .andExpect(jsonPath("$.actors", is(Movie.getActors())));

    }

}
