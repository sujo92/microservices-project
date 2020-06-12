package com.suziesta.movieinfoservice.resources;

import com.suziesta.movieinfoservice.models.Movie;
import com.suziesta.movieinfoservice.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieResource {
    @Autowired
    private MovieRepository repo;

    @RequestMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId){
        return repo.findById(movieId).get();
    };
}
