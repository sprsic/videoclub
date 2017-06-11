package com.sprsic.service;

import com.sprsic.entity.Movie;

import java.util.List;

/**
 * Written with love
 *
 * @author Sasa Prsic 11/06/2017
 */
public interface IMovieService {

    List<Movie> getAllMovies(List<Long> movieIds);

    Movie getMovie(Long movieId);


}
