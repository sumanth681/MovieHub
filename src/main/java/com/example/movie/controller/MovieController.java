package com.example.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.movie.model.Movie;
import java.util.*;
import com.example.movie.service.MovieH2Service;

@RestController

public class MovieController {
    @Autowired
    MovieH2Service movieH2Service;

    @GetMapping("/movies")
    public ArrayList<Movie> getMovies() {
        return movieH2Service.getMovies();
    }

    @PostMapping("/movies")
    public Movie addMovie(@RequestBody Movie movie) {
        return movieH2Service.addMovie(movie);
    }

    @GetMapping("/movies/{movieId}")

    public Movie getMovieById(@PathVariable("movieId") int movieId) {
        return movieH2Service.getMovieById(movieId);
    }

    @PutMapping("/movies/{movieId}")

    public Movie updateMovie(@PathVariable("movieId") int movieId, @RequestBody Movie movie) {
        return movieH2Service.updateMovie(movieId, movie);
    }

    @DeleteMapping("/movies/{movieId}")

    public void deleteMovie(@PathVariable("movieId") int movieId) {
        movieH2Service.deleteMovie(movieId);
    }

}
