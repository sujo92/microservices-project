package com.suziesta.moviecatalogservice.resources;


import com.suziesta.moviecatalogservice.models.CatalogItem;
import com.suziesta.moviecatalogservice.models.Movie;
import com.suziesta.moviecatalogservice.models.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalogue(@PathVariable("userId") String userId){
        //get all rated movie id
        List<Rating> ratings = Arrays.asList(
                new Rating("1234",4),
                new Rating("4678",3)
        );

        //for each movie id call movie info service and get details
        return ratings.stream()
                .map(rating->{
                    //get instance of restTemplate and call method getForObject
                    //1st parameter-url you want to call,it gets back string
                    //2nd - unload it in class with same template as json
                    Movie movie = restTemplate.getForObject("http://localhost:8082/movies/"+rating.getMovieId(),Movie.class);
                    // put all of them together
                    return new CatalogItem(movie.getName(),"desc",rating.getRating());
                })
                .collect(Collectors.toList());

    }
}
