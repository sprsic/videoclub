package com.sprsic.resource;

import com.sprsic.entity.Customer;
import com.sprsic.entity.CustomerBonusLog;
import com.sprsic.model.common.FailureJsonResponse;
import com.sprsic.model.common.JsonResponse;
import com.sprsic.model.common.ResponseMsg;
import com.sprsic.model.common.SuccessJsonResponse;
import com.sprsic.service.ICustomerBonusService;
import com.sprsic.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * Written with love
 *
 * @author Sasa Prsic 11/06/2017
 */
@RestController
public class CustomerResource {

    @Autowired
    private ICustomerService customerService;
    @Autowired
    private ICustomerBonusService customerBonusService;

    @RequestMapping(value = "/customer/{customerId}/bonus", method = RequestMethod.GET)
    public JsonResponse bonus(@PathVariable("customerId") Long customerId) {
        Customer customer = customerService.getCustomer(customerId);

        Optional<Customer> customerSearch = Optional.ofNullable(customer);
        if (customerSearch.isPresent()) {
            HashMap<String, Integer> bonusMap = new HashMap<>();
            bonusMap.put("bonusPoints", customer.getBonusPoints());
            return new SuccessJsonResponse(ResponseMsg.OK, bonusMap);
        } else {
            return new FailureJsonResponse(ResponseMsg.FAILURE, "Not customer found");
        }
    }

    @RequestMapping(value = "/customer/{customerId}/bonusHistory", method = RequestMethod.GET)
    public JsonResponse bonusHistory(@PathVariable("customerId") Long customerId) {
        List<CustomerBonusLog> log = customerBonusService.getBonusLogForCustomer(customerId);

        Optional<List<CustomerBonusLog>> customerBonusSearch = Optional.ofNullable(log);
        if (customerBonusSearch.isPresent()) {
            HashMap<String, List<CustomerBonusLog>> bonusMap = new HashMap<>();
            bonusMap.put("bonusHistory", customerBonusSearch.get());
            return new SuccessJsonResponse(ResponseMsg.OK, bonusMap);
        } else {
            return new FailureJsonResponse(ResponseMsg.FAILURE, "No records for customer");
        }
    }
}
