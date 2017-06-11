package com.sprsic.service;

import com.sprsic.entity.Customer;

/**
 * Written with love
 *
 * @author Sasa Prsic 11/06/2017
 */
public interface ICustomerService {

    Customer getCustomer(Long customerId);
}
