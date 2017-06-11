package com.sprsic.service;

import com.sprsic.model.PriceCalculationModel;
import com.sprsic.model.common.MovieRentDetailsModel;
import org.joda.time.DateTime;

import java.util.List;

/**
 * Written with love
 *
 * @author Sasa Prsic 11/06/2017
 */
public interface ILeaseService {

    /**
     * Calculates price for a given lease.
     *
     * @param leaseId
     * @return
     */
    PriceCalculationModel calculateLeasePrice(Long leaseId);

    /**
     * Calculates possible surcharges.
     *
     * @param leaseId
     * @return
     */
    PriceCalculationModel calculateOverDuePrice(Long leaseId);

    /**
     * Creates a lease, also adds a bonus points to a customer and logs bonus points
     *
     * @param customerId
     * @param leaseDate
     * @param movieRentDetailsModel
     * @param employeId
     * @return
     */
    PriceCalculationModel createLease(Long customerId, DateTime leaseDate, List<MovieRentDetailsModel> movieRentDetailsModel, Long employeId);
}
