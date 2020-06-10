package com.suziesta.moviecatalogservice.resources;


import com.suziesta.moviecatalogservice.models.CatalogItem;
import com.suziesta.moviecatalogservice.models.Movie;
import com.suziesta.moviecatalogservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

//    @Autowired
//    private WebClient.Builder webClientBuilder;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalogue(@PathVariable("userId") String userId){

        //get all rated movie id
        UserRating ratings = restTemplate.getForObject("http://localhost:8083/ratingsdata/users/"+userId,UserRating.class);


        return ratings.getUserRating().stream()
                .map(rating->{
                    //for each movie id call movie info service and get details
                    //get instance of restTemplate and call method getForObject
                    //1st parameter-url you want to call,it gets back string
                    //2nd - unload it in class with same template as json
                    Movie movie = restTemplate.getForObject("http://localhost:8082/movies/"+rating.getMovieId(),Movie.class);

//                    Movie movie = webClientBuilder.build()
//                            .get()
//                            .uri("http://localhost:8082/movies/"+rating.getMovieId())
//                            .retrieve()
//                            .bodyToMono(Movie.class)
//                            .block();//blocking execution till mono is returned from service

                    // put all of them together
                    return new CatalogItem(movie.getName(),"desc",rating.getRating());
                })
                .collect(Collectors.toList());

    }
}
