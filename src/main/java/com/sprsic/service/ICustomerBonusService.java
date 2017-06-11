package com.sprsic.service;

import com.sprsic.entity.CustomerBonusLog;

import java.util.List;

/**
 * Written with love
 *
 * @author Sasa Prsic 11/06/2017
 */

public interface ICustomerBonusService {
    CustomerBonusLog createBonusLog(Long customerId, int bonusAmount);

    List<CustomerBonusLog> getBonusLogForCustomer(Long customerId);
}
