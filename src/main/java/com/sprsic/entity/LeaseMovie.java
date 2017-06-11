package com.sprsic.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.Date;

/**
 * Written with love
 *
 * @author Sasa Prsic 11/06/2017
 */
@Entity
@Table(name = "lease_details")
public class LeaseMovie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "lease_details_id")
    private Long leaseDetailsId;
    @PrimaryKeyJoinColumn(name = "movie_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;
    @Column(name = "return_date", nullable = false)
    private Date returnDate;

    public Long getLeaseDetailsId() {
        return leaseDetailsId;
    }

    public void setLeaseDetailsId(Long leaseDetailsId) {
        this.leaseDetailsId = leaseDetailsId;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
