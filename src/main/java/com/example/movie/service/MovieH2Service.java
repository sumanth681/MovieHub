
package com.example.movie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.movie.repository.MovieRepository;
import com.example.movie.model.MovieRowMapper;
import com.example.movie.model.Movie;

import java.util.*;

@Service
public class MovieH2Service implements MovieRepository {
    @Autowired
    public JdbcTemplate db;

    @Override
    public ArrayList<Movie> getMovies() {
        List<Movie> movies = db.query("SELECT * FROM MOVIELIST", new MovieRowMapper());
        ArrayList<Movie> allMovies = new ArrayList<>(movies);
        return allMovies;
    }

    @Override

    public Movie addMovie(Movie movie) {
        db.update("INSERT INTO MOVIELIST (movieName , leadActor ) VALUES (? , ?)", movie.getMovieName(),
                movie.getLeadActor());

        Movie savedMovie = db.queryForObject("SELECT * FROM MOVIELIST WHERE movieName = ? AND leadActor = ?",
                new MovieRowMapper(), movie.getMovieName(), movie.getLeadActor());

        return savedMovie;
    }

    @Override

    public Movie getMovieById(int movieId) {
        try {
            Movie Uniquemovie = db.queryForObject("SELECT * FROM MOVIELIST WHERE  movieId = ?",
                    new MovieRowMapper(), movieId);
            return Uniquemovie;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }


    @Override
    public Movie updateMovie(int movieId, Movie movie) {
        if (movie.getMovieName() != null) {
            db.update("UPDATE MOVIELIST SET movieName = ? WHERE movieId = ?", movie.getMovieName(), movieId);
        }
        if (movie.getLeadActor() != null) {
            db.update("UPDATE MOVIELIST SET leadActor  = ? WHERE movieId = ?", movie.getLeadActor(), movieId);
        }
        return getMovieById(movieId);
    }


    @Override

    public void deleteMovie(int movieId) {
        db.update("DELETE FROM MOVIELIST WHERE movieId = ?", movieId);

    
    }

}