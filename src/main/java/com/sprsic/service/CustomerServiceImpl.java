package com.sprsic.service;

import com.sprsic.dao.ICustomerDao;
import com.sprsic.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Customer getCustomer(Long customerId) {
        return customerDao.findOne(customerId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Customer updateCustomerBonusPoints(Long customerId, Integer bonusPoints) {
        Customer customer = customerDao.findOne(customerId);
        int oldBOnusPoints = customer.getBonusPoints();
        customer.setBonusPoints(oldBOnusPoints + bonusPoints);
        return customerDao.save(customer);
    }
}
