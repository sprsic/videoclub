package com.sprsic.service;

import com.sprsic.dao.ICustomerBonusDao;
import com.sprsic.entity.CustomerBonusLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Written with love
 *
 * @author Sasa Prsic 11/06/2017
 */
@Service
public class CustomerBonusServiceImpl implements ICustomerBonusService {
    @Autowired
    private ICustomerBonusDao customerBonusDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public CustomerBonusLog createBonusLog(Long customerId, int bonusAmount) {
        CustomerBonusLog customerBonusLog = new CustomerBonusLog();
        customerBonusLog.setCustomerId(customerId);
        customerBonusLog.setBonusAmount(bonusAmount);
        customerBonusLog.setCreatedDate(new Date());
        return customerBonusDao.save(customerBonusLog);
    }

    @Override
    public List<CustomerBonusLog> getBonusLogForCustomer(Long customerId) {
        return customerBonusDao.findByCustomerId(customerId);
    }
}
