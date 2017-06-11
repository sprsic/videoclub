package com.sprsic.model;

import com.sprsic.model.common.MovieRentDetailsModel;

import java.util.Date;
import java.util.List;

/**
 * Model object that is going to be used for creating a lease.
 *
 * @author Sasa Prsic 11/06/2017
 */

public class LeaseModel {
    private Long customerId;
    private Long employeeId; //needed because there is no application aware of the logged in user
    private List<MovieRentDetailsModel> movies;

    private Date leaseDate;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public List<MovieRentDetailsModel> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieRentDetailsModel> movies) {
        this.movies = movies;
    }

    public Date getLeaseDate() {
        return leaseDate;
    }

    public void setLeaseDate(Date leaseDate) {
        this.leaseDate = leaseDate;
    }
}
