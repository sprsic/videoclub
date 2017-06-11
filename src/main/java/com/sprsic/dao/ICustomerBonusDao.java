package com.sprsic.dao;

import com.sprsic.entity.CustomerBonusLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Written with love
 *
 * @author Sasa Prsic 11/06/2017
 */
@Repository
public interface ICustomerBonusDao extends CrudRepository<CustomerBonusLog, Long> {

    List<CustomerBonusLog> findByCustomerId(Long customerId);
}
