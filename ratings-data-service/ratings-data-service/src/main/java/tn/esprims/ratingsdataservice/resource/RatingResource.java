package tn.esprims.ratingsdataservice.resource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprims.ratingsdataservice.models.Rating;
import tn.esprims.ratingsdataservice.models.RatingList;
import tn.esprims.ratingsdataservice.repositories.RatingRepository;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingResource {

    @Autowired
    RatingRepository ratingRepository;

    @GetMapping("/all")
    public List<Rating> getAll(){
        return ratingRepository.findAll();
    }

    @PostMapping("/add")
    public List<Rating> persist(@RequestBody final Rating rating){
        ratingRepository.save(rating);
        return ratingRepository.findAll();
    }


    //Les deux microservices
    @RequestMapping("/api/all")
    public RatingList getRatings( ){
        RatingList ratingList = new RatingList();
        ratingList.setRatingList(ratingRepository.findAll());
        return ratingList;
    }

    @RequestMapping("/api/{userName}")
    public RatingList getUserRatings(@PathVariable("userName") String userName){
        RatingList ratingList = new RatingList();
        ratingList.setRatingList(ratingRepository.findByUserName(userName));
        return ratingList;
    }
}
