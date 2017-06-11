package com.sprsic.service;

import com.sprsic.dao.ILeaseDao;
import com.sprsic.entity.Customer;
import com.sprsic.entity.Employee;
import com.sprsic.entity.Lease;
import com.sprsic.entity.LeaseMovie;
import com.sprsic.entity.Movie;
import com.sprsic.entity.MovieType;
import com.sprsic.model.MoviePriceRentModel;
import com.sprsic.model.PriceCalculationModel;
import com.sprsic.model.common.MovieRentDetailsModel;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.sprsic.entity.MovieType.NEW_RELEASE;

/**
 * Do not let them tell you that math is hard, math is life and life is hard.
 *
 * @author Sasa Prsic 11/06/2017
 */

@Service
public class LeaseServiceImpl implements ILeaseService {

    private static final Integer REGULAR_MOVIE_LEASE_STANDARD_DAYS = 3;
    private static final Integer OLD_MOVIE_LEASE_STANDARD_DAYS = 5;

    @Autowired
    private IMovieService movieService;
    @Autowired
    private ILeaseDao leaseDao;
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private ICustomerBonusService customerBonusService;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public PriceCalculationModel calculateLeasePrice(Long leaseId) {
        Lease lease = leaseDao.findOne(leaseId);
        Iterable<LeaseMovie> leaseMovies = lease.getLeaseDetails();
        return getPriceCalculationModel(leaseMovies, leaseId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public PriceCalculationModel calculateOverDuePrice(Long leaseId) {

        Lease lease = leaseDao.findOne(leaseId);

        DateTime dateOfReturn = new DateTime();
        List<LeaseMovie> leaseDetails = lease.getLeaseDetails();
        BigDecimal total = BigDecimal.ZERO;

        List<MoviePriceRentModel> leasePrices = new ArrayList<>(leaseDetails.size());
        for (LeaseMovie ld : leaseDetails) {
            int daysOverRegulardays = Days.daysBetween(new DateTime(ld.getReturnDate()), dateOfReturn).getDays();
            if (daysOverRegulardays > 0) {
                Movie movie = ld.getMovie();
                MoviePriceRentModel moviePriceRentModel = new MoviePriceRentModel();
                moviePriceRentModel.setMovieName(movie.getName());
                moviePriceRentModel.setMovieType(movie.getMovieType());
                BigDecimal moviePriceExtra = ld.getMovie().getMovieType().getRentPrice().multiply(BigDecimal.valueOf(daysOverRegulardays));
                moviePriceRentModel.setPriceRent(moviePriceExtra);
                leasePrices.add(moviePriceRentModel);
                total = total.add(moviePriceExtra);
            }
        }

        return new PriceCalculationModel(leasePrices, total, leaseId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public PriceCalculationModel createLease(Long customerId, DateTime leaseDate, List<MovieRentDetailsModel> movieRentDetailsModel, Long employeeId) {
        Lease lease = new Lease();
        Customer customer = customerService.getCustomer(customerId);
        Employee employee = employeeService.getEmployee(employeeId);
        lease.setCustomer(customer);
        lease.setEmployee(employee);
        lease.setLeaseDate(leaseDate.toDate());
        List<LeaseMovie> leaseMovies = new ArrayList<>();

        int bonusPoints = 0;
        for (MovieRentDetailsModel mrm : movieRentDetailsModel) {
            Movie movie = movieService.getMovie(mrm.getMovieId());
            LeaseMovie ld = new LeaseMovie();
            ld.setMovie(movie);
            ld.setReturnDate(mrm.getReturnDate());
            leaseMovies.add(ld);
            bonusPoints += movie.getMovieType().getBonusPoints();
            customerBonusService.createBonusLog(customerId, movie.getMovieType().getBonusPoints());
        }

        customerService.updateCustomerBonusPoints(customerId, bonusPoints);

        lease.setLeaseDetails(leaseMovies);
        leaseDao.save(lease);

        return calculateLeasePrice(lease.getLeaseId());
    }

    private BigDecimal calculateOldMoviePrice(int days) {
        BigDecimal total = BigDecimal.ZERO;
        if (days <= OLD_MOVIE_LEASE_STANDARD_DAYS) {
            total = total.add(MovieType.OLD_FILM.getRentPrice());
        } else {
            int daysOverRegularDays = days - OLD_MOVIE_LEASE_STANDARD_DAYS;
            BigDecimal extraPrice = MovieType.OLD_FILM.getRentPrice().multiply(BigDecimal.valueOf(daysOverRegularDays));
            total = total.add(MovieType.OLD_FILM.getRentPrice()).add(extraPrice);
        }

        return total;
    }

    private BigDecimal calculateRegularMoviePrice(int days) {
        BigDecimal total = BigDecimal.ZERO;
        if (days <= REGULAR_MOVIE_LEASE_STANDARD_DAYS) {
            total = total.add(MovieType.REGULAR_FILM.getRentPrice());
        } else {
            int daysOverRegularDays = days - REGULAR_MOVIE_LEASE_STANDARD_DAYS;
            BigDecimal extraPrice = MovieType.REGULAR_FILM.getRentPrice().multiply(BigDecimal.valueOf(daysOverRegularDays));
            total = total.add(MovieType.REGULAR_FILM.getRentPrice()).add(extraPrice);
        }

        return total;
    }

    private BigDecimal calculateNewReleaseMoviePrice(int days) {
        BigDecimal total = BigDecimal.ZERO;
        return total.add(NEW_RELEASE.getRentPrice().multiply(BigDecimal.valueOf(days)));
    }

    private PriceCalculationModel getPriceCalculationModel(Iterable<LeaseMovie> movies, Long leaseId) {
        List<MoviePriceRentModel> leasePrices = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;
        DateTime currentDate = new DateTime().withTimeAtStartOfDay();
        for (LeaseMovie lm : movies) {
            Movie m = lm.getMovie();
            MoviePriceRentModel moviePriceRentModel = new MoviePriceRentModel();
            moviePriceRentModel.setMovieName(m.getName());
            moviePriceRentModel.setMovieType(m.getMovieType());
            int days = Days.daysBetween(currentDate, new DateTime(lm.getReturnDate()).withTimeAtStartOfDay()).getDays();
            switch (m.getMovieType()) {
                case NEW_RELEASE:
                    BigDecimal newReleaseMoviePrice = calculateNewReleaseMoviePrice(days);
                    total = total.add(newReleaseMoviePrice);
                    moviePriceRentModel.setPriceRent(newReleaseMoviePrice);
                    leasePrices.add(moviePriceRentModel);
                    break;
                case REGULAR_FILM:
                    BigDecimal regularMoviePrice = calculateRegularMoviePrice(days);
                    total = total.add(regularMoviePrice);
                    moviePriceRentModel.setPriceRent(regularMoviePrice);
                    leasePrices.add(moviePriceRentModel);
                    break;
                case OLD_FILM:
                    BigDecimal oldMoviePrice = calculateOldMoviePrice(days);
                    total = total.add(oldMoviePrice);
                    moviePriceRentModel.setPriceRent(oldMoviePrice);
                    leasePrices.add(moviePriceRentModel);
                    break;
            }
        }


        return new PriceCalculationModel(leasePrices, total, leaseId);
    }
}
