package tn.esprims.movieinfoservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprims.movieinfoservice.models.Movie;

public interface MoviesRepository extends JpaRepository<Movie, Integer> {
    Movie findByName(String movieName);
}
