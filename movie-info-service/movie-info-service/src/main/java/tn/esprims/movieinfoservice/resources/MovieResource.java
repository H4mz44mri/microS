package tn.esprims.movieinfoservice.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprims.movieinfoservice.models.Movie;
import tn.esprims.movieinfoservice.models.MovieList;
import tn.esprims.movieinfoservice.repositories.MoviesRepository;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieResource {
    @Autowired
    MoviesRepository moviesRepository;
    @RequestMapping("/{movieName}")
    public Movie getMovieInfo(@PathVariable("movieName") String movieName){
        return moviesRepository.findByName(movieName);
    }

    @GetMapping("/all")
    public MovieList getAll(){
        MovieList movieList = new MovieList();
        movieList.setMovieList(moviesRepository.findAll());
        return movieList;
    }

    @PostMapping("/add")
    public List<Movie> persist(@RequestBody final Movie movie){
        moviesRepository.save(movie);
        return moviesRepository.findAll();
    }

}
