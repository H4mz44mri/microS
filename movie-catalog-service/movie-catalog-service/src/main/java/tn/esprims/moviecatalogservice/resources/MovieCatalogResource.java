package tn.esprims.moviecatalogservice.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import tn.esprims.moviecatalogservice.models.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/movies")
    public List<Movie> getMovies()
    {
        MovieList list = restTemplate.getForObject("http://MOVIE-INFO-SERVICE/movies/api/all", MovieList.class);
        return list.getMovieList();
    }

    @RequestMapping("/ratings")
    public List<Rating> getRatings()
    {
        RatingList list = restTemplate.getForObject("http://RATINGS-DATA-SERVICE/ratings/api/all", RatingList.class);
        return list.getRatingList();
    }

    @RequestMapping("/ratings/{userName}")
    public List<MovieRatingList> getUserRatingsAndMovies(@PathVariable("userName") String userName)
    {
        List<MovieRatingList> movieRatingList= new ArrayList<MovieRatingList>();
        RatingList ratingList = restTemplate.getForObject("http://RATINGS-DATA-SERVICE/ratings/api/"+userName, RatingList.class);
        Rating rating=new Rating();
        for (int i=0;i< ratingList.getRatingList().size();i++)
        {
            rating=ratingList.getRatingList().get(i);
            Movie movie = restTemplate.getForObject("http://MOVIE-INFO-SERVICE/movies/api/"+rating.getMovieName(), Movie.class);
            movieRatingList.add(new MovieRatingList(userName,movie.getName(),rating.getRating(),movie.getDescription(),movie.getPicture()));
        }
        return movieRatingList;
    }
}
