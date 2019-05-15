package tn.esprims.moviecatalogservice.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import tn.esprims.moviecatalogservice.models.Movie;
import tn.esprims.moviecatalogservice.models.MovieList;

import java.lang.reflect.ParameterizedType;
import java.util.List;

@RestController
@RequestMapping("/")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/movies")
    public List<Movie> getMovies()
    {
        MovieList list = restTemplate.getForObject("http://MOVIE-INFO-SERVICE/movies/all", MovieList.class);
        return list.getMovieList();
    }
}
