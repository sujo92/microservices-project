package com.suziesta.movieinfoservice.repository;


import com.suziesta.movieinfoservice.models.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie,String> {

}
