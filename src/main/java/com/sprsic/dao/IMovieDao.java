package com.sprsic.dao;

import com.sprsic.entity.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Written with love
 *
 * @author Sasa Prsic 11/06/2017
 */
@Repository
public interface IMovieDao extends CrudRepository<Movie, Long> {

}
