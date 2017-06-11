package com.sprsic.model.common;

import java.util.Date;

/**
 * Written with love
 *
 * @author Sasa Prsic 11/06/2017
 */
public class MovieRentDetailsModel {
    private Long movieId;
    private Date returnDate;

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
