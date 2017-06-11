package com.sprsic.service;

import com.sprsic.model.common.MovieRentDetailsModel;
import com.sprsic.model.common.PriceCalculationModel;
import org.joda.time.DateTime;

import java.util.List;

/**
 * Written with love
 *
 * @author Sasa Prsic 11/06/2017
 */
public interface ILeaseService {

    PriceCalculationModel calculateLeasePrice(Long leaseId);


    PriceCalculationModel calculateOverDuePrice(Long leaseId);

    PriceCalculationModel createLease(Long customerId, DateTime leaseDate, List<MovieRentDetailsModel> movieRentDetailsModel, Long employeId);


}
