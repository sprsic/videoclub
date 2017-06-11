package com.sprsic.resource;

import com.sprsic.model.LeaseModel;
import com.sprsic.model.PriceCalculationModel;
import com.sprsic.model.common.FailureJsonResponse;
import com.sprsic.model.common.FormErrorModel;
import com.sprsic.model.common.JsonResponse;
import com.sprsic.model.common.ResponseMsg;
import com.sprsic.model.common.SuccessJsonResponse;
import com.sprsic.service.ILeaseService;
import com.sprsic.util.ResourceUtil;
import com.sprsic.validator.LeaseValidator;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Written with love
 *
 * @author Sasa Prsic 11/06/2017
 */
@RestController
public class LeaseResource {

    @Autowired
    private ILeaseService leaseService;
    @Autowired
    private LeaseValidator leaseValidator;

    @RequestMapping(value = "/lease", method = RequestMethod.POST)
    public JsonResponse lease(@RequestBody LeaseModel leaseModel, BindingResult bindingResult) {
        FormErrorModel formError = new FormErrorModel();
        if (bindingResult.hasErrors()) {
            ResourceUtil.fieldErrors(formError, bindingResult);
            formError.setGlobalError("Error creating lease");
            return new FailureJsonResponse(ResponseMsg.FAILURE, formError);
        } else {
            ValidationUtils.invokeValidator(leaseValidator, leaseModel, bindingResult);
            if (bindingResult.hasErrors()) {
                ResourceUtil.fieldErrors(formError, bindingResult);
                return new FailureJsonResponse(ResponseMsg.FAILURE, formError);
            }
        }

        PriceCalculationModel priceCalculationModel = leaseService.createLease(leaseModel.getCustomerId(), new DateTime(leaseModel.getLeaseDate()), leaseModel.getMovies(), leaseModel.getEmployeeId());

        return new SuccessJsonResponse(ResponseMsg.OK, priceCalculationModel);
    }

    @RequestMapping(value = "/overDue/lease/{leaseId}", method = RequestMethod.GET)
    public JsonResponse overDue(@PathVariable("leaseId") Long leaseId) {

        PriceCalculationModel priceCalculationModel = leaseService.calculateOverDuePrice(leaseId);

        return new SuccessJsonResponse(ResponseMsg.OK, priceCalculationModel);
    }
}
