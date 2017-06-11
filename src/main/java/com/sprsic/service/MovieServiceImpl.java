package com.sprsic.service;

import com.sprsic.dao.IMovieDao;
import com.sprsic.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Written with love
 *
 * @author Sasa Prsic 11/06/2017
 */

@Service
public class MovieServiceImpl implements IMovieService {
    @Autowired
    private IMovieDao movieDao;

    @Override
    public List<Movie> getAllMovies(List<Long> movieIds) {
        Iterable<Movie> movies = movieDao.findAll(movieIds);
        List<Movie> movieList = new ArrayList<>();
        movies.forEach(movieList::add);
        return movieList;
    }

    @Override
    public Movie getMovie(Long movieId) {
        return movieDao.findOne(movieId);
    }
}
