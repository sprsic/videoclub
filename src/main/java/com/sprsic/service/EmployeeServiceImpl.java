package com.sprsic.service;

import com.sprsic.dao.IEmployeeDao;
import com.sprsic.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Written with love
 *
 * @author Sasa Prsic 11/06/2017
 */
@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    private IEmployeeDao employeeDao;

    @Override
    public Employee getEmployee(Long employeeId) {
        return employeeDao.findOne(employeeId);
    }
}
