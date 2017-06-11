package com.sprsic.model;

import com.sprsic.entity.MovieType;

import java.math.BigDecimal;

/**
 * Written with love
 *
 * @author Sasa Prsic 11/06/2017
 */
public class MoviePriceRentModel {
    private String movieName;
    private MovieType movieType;
    private BigDecimal priceRent;

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public BigDecimal getPriceRent() {
        return priceRent;
    }

    public MovieType getMovieType() {
        return movieType;
    }

    public void setMovieType(MovieType movieType) {
        this.movieType = movieType;
    }

    public void setPriceRent(BigDecimal priceRent) {
        this.priceRent = priceRent;
    }
}
