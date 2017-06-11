package com.sprsic.validator;

import com.sprsic.entity.Customer;
import com.sprsic.entity.Employee;
import com.sprsic.entity.Movie;
import com.sprsic.model.LeaseModel;
import com.sprsic.model.common.MovieRentDetailsModel;
import com.sprsic.service.ICustomerService;
import com.sprsic.service.IEmployeeService;
import com.sprsic.service.IMovieService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Date;
import java.util.List;

/**
 * Written with love
 * <p>
 * Validator for validating creation of lease
 *
 * @author Sasa Prsic 11/06/2017
 */
@Component
public class LeaseValidator implements Validator {

    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IMovieService movieService;

    @Override
    public boolean supports(Class<?> aClass) {
        return LeaseModel.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LeaseModel command = (LeaseModel) target;
        Date currentDate = new DateTime().withTimeAtStartOfDay().toDate();

        if (command.getCustomerId() == null) {
            errors.rejectValue("customerId", "Customer id can't be null");
        } else {
            Customer c = customerService.getCustomer(command.getCustomerId());
            if (c == null) {
                errors.rejectValue("customerId", "Customer not found");
            }
        }

        if (command.getEmployeeId() == null) {
            errors.rejectValue("employeeId", "Employee id can't be null");
        } else {
            Employee e = employeeService.getEmployee(command.getEmployeeId());
            if (e == null) {
                errors.rejectValue("employeeId", "Employee not found");
            }
        }

        // for simplicity just don't allow empty lease date
        if (command.getLeaseDate() == null) {
            errors.rejectValue("leaseDate", "Lease date can't be null");
        } else if (currentDate.after(command.getLeaseDate())) {
            errors.rejectValue("leaseDate", "Lease date can't be in the past");
        }

        if (command.getMovies() == null || command.getMovies().isEmpty()) {
            errors.rejectValue("movies", "List of movies can't be empty");
        } else {
            // too complex
            int i = 0;
            List<MovieRentDetailsModel> moviesList = command.getMovies();

            for (MovieRentDetailsModel mdrm : moviesList) {
                if (mdrm.getReturnDate() == null) {
                    errors.rejectValue("movies[" + i + "].returnDate", "Return date can't be null");
                } else if (command.getLeaseDate() != null && command.getLeaseDate().after(new DateTime(mdrm.getReturnDate()).withTimeAtStartOfDay().toDate())) {
                    errors.rejectValue("movies[" + i + "].returnDate", "Return date can't be before or equal to lease date");
                }

                // caching option will be good
                Movie m = movieService.getMovie(mdrm.getMovieId());
                if (m == null) {
                    errors.rejectValue("movies[" + i + "].movieId", "Movie " + mdrm.getMovieId() + " not found");
                }
                i++;
            }
        }
    }
}
