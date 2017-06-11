package com.sprsic.service;

import com.sprsic.dao.ICustomerDao;
import com.sprsic.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Written with love
 *
 * @author Sasa Prsic 11/06/2017
 */
@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private ICustomerDao customerDao;

    @Override
    public Customer getCustomer(Long customerId) {
        return customerDao.findOne(customerId);
    }
}
