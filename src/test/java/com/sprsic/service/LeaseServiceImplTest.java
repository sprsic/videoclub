package com.sprsic.service;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.sprsic.entity.Customer;
import com.sprsic.entity.CustomerBonusLog;
import com.sprsic.entity.MovieType;
import com.sprsic.model.PriceCalculationModel;
import com.sprsic.model.common.MovieRentDetailsModel;
import org.joda.time.DateTime;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * Written with love
 *
 * @author Sasa Prsic 11/06/2017
 */

@DatabaseSetup("classpath:lease-dataset.xml")
public class LeaseServiceImplTest extends BaseITest {

    @Autowired
    private ILeaseService leaseService;
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private ICustomerBonusService customerBonusService;
    private Long employeeId = 1L;
    private Long customerId = 1L;
    private DateTime currentDate = new DateTime();

    @Test
    @Transactional
    @Rollback
    @Ignore("Ignore it for now")
    public void regularNewReleaseRentPriceTest() {
        BigDecimal rentPriceExpected = BigDecimal.valueOf(40);

        // given
        List<MovieRentDetailsModel> movieRentDetailsModelList = new ArrayList<>();
        MovieRentDetailsModel medm = new MovieRentDetailsModel();
        medm.setMovieId(1L);
        medm.setReturnDate(new DateTime().plusDays(1).toDate());
        movieRentDetailsModelList.add(medm);

        //test
        PriceCalculationModel rentPrice = leaseService.createLease(1L, new DateTime(), movieRentDetailsModelList, 1L);

        //validate
        Customer customer = customerService.getCustomer(customerId);
        assertEquals(Integer.valueOf(2), customer.getBonusPoints());

        List<CustomerBonusLog> bonusLog = customerBonusService.getBonusLogForCustomer(customerId);
        assertEquals(1, bonusLog.size());

        assertEquals(rentPriceExpected, rentPrice.getTotal());
        assertNotNull(rentPrice.getLeasePrices());
        assertEquals(1, rentPrice.getLeasePrices().size());
        assertEquals(MovieType.NEW_RELEASE, rentPrice.getLeasePrices().get(0).getMovieType());
        assertEquals("Some new movie", rentPrice.getLeasePrices().get(0).getMovieName());
        assertEquals(BigDecimal.valueOf(40), rentPrice.getLeasePrices().get(0).getPriceRent());
    }

    @Test
    @Transactional
    @Rollback
    @Ignore("Ignore it for now")
    public void newReleaseTreeDaysRentPriceTest() {
        BigDecimal rentPriceExpected = BigDecimal.valueOf(120);

        // given
        List<MovieRentDetailsModel> movieRentDetailsModelList = new ArrayList<>();
        MovieRentDetailsModel medm = new MovieRentDetailsModel();
        medm.setMovieId(1L);
        medm.setReturnDate(new DateTime().plusDays(3).toDate());
        movieRentDetailsModelList.add(medm);

        //test
        PriceCalculationModel rentPrice = leaseService.createLease(customerId, currentDate, movieRentDetailsModelList, employeeId);

        //validate
        Customer customer = customerService.getCustomer(customerId);
        assertEquals(Integer.valueOf(2), customer.getBonusPoints());

        List<CustomerBonusLog> bonusLog = customerBonusService.getBonusLogForCustomer(customerId);
        assertEquals(1, bonusLog.size());

        assertEquals(rentPriceExpected, rentPrice.getTotal());
        assertNotNull(rentPrice.getLeasePrices());
        assertEquals(1, rentPrice.getLeasePrices().size());
        assertEquals(MovieType.NEW_RELEASE, rentPrice.getLeasePrices().get(0).getMovieType());
        assertEquals("Some new movie", rentPrice.getLeasePrices().get(0).getMovieName());
        assertEquals(BigDecimal.valueOf(120), rentPrice.getLeasePrices().get(0).getPriceRent());
    }

    @Test
    @Transactional
    @Rollback
    @Ignore("Ignore it for now")
    public void regularTreeDaysRentPriceTest() {
        BigDecimal rentPriceExpected = BigDecimal.valueOf(30);

        //given
        List<MovieRentDetailsModel> movieRentDetailsModelList = new ArrayList<>();
        MovieRentDetailsModel medm = new MovieRentDetailsModel();
        medm.setMovieId(3L);
        medm.setReturnDate(new DateTime().plusDays(3).toDate());
        movieRentDetailsModelList.add(medm);

        //test
        PriceCalculationModel rentPrice = leaseService.createLease(customerId, currentDate, movieRentDetailsModelList, employeeId);

        Customer customer = customerService.getCustomer(customerId);
        assertEquals(Integer.valueOf(1), customer.getBonusPoints());

        List<CustomerBonusLog> bonusLog = customerBonusService.getBonusLogForCustomer(customerId);
        assertEquals(1, bonusLog.size());

        //validate
        assertEquals(rentPriceExpected, rentPrice.getTotal());
        assertNotNull(rentPrice.getLeasePrices());
        assertEquals(1, rentPrice.getLeasePrices().size());
        assertEquals(MovieType.REGULAR_FILM, rentPrice.getLeasePrices().get(0).getMovieType());
        assertEquals("Some regular movie", rentPrice.getLeasePrices().get(0).getMovieName());
        assertEquals(BigDecimal.valueOf(30), rentPrice.getLeasePrices().get(0).getPriceRent());
    }

    @Test
    @Transactional
    @Rollback
    @Ignore("Ignore it for now")
    public void oldMovieRentTest() {
        BigDecimal rentPriceExpected = BigDecimal.valueOf(60);

        //given
        List<MovieRentDetailsModel> movieRentDetailsModelList = new ArrayList<>();
        MovieRentDetailsModel medm = new MovieRentDetailsModel();
        medm.setMovieId(2L);
        medm.setReturnDate(new DateTime().plusDays(6).toDate());
        movieRentDetailsModelList.add(medm);

        //test
        PriceCalculationModel rentPrice = leaseService.createLease(customerId, currentDate, movieRentDetailsModelList, employeeId);

        //validate
        Customer customer = customerService.getCustomer(customerId);
        assertEquals(Integer.valueOf(1), customer.getBonusPoints());

        List<CustomerBonusLog> bonusLog = customerBonusService.getBonusLogForCustomer(customerId);
        assertEquals(1, bonusLog.size());

        assertEquals(rentPriceExpected, rentPrice.getTotal());
        assertNotNull(rentPrice.getLeasePrices());
        assertEquals(1, rentPrice.getLeasePrices().size());
        assertEquals(MovieType.OLD_FILM, rentPrice.getLeasePrices().get(0).getMovieType());
        assertEquals("Some old movie", rentPrice.getLeasePrices().get(0).getMovieName());
        assertEquals(rentPriceExpected, rentPrice.getLeasePrices().get(0).getPriceRent());
    }

    //
    @Test
    @Transactional
    @Rollback
    @Ignore("Ignore it for now")
    public void mixedMovieTypePriceRent() {
        // one movie new release one day
        BigDecimal rentPriceExpected = BigDecimal.valueOf(40 * 6 + 30 + 30);

        //given
        List<MovieRentDetailsModel> movieRentDetailsModelList = new ArrayList<>();
        MovieRentDetailsModel medm1 = new MovieRentDetailsModel();
        medm1.setMovieId(1L);
        medm1.setReturnDate(new DateTime().plusDays(6).toDate());
        movieRentDetailsModelList.add(medm1);
        MovieRentDetailsModel medm2 = new MovieRentDetailsModel();
        medm2.setMovieId(2L);
        medm2.setReturnDate(new DateTime().plusDays(6).toDate());
        movieRentDetailsModelList.add(medm2);

        //test
        PriceCalculationModel rentPrice = leaseService.createLease(customerId, currentDate, movieRentDetailsModelList, employeeId);

        //validate
        Customer customer = customerService.getCustomer(customerId);
        assertEquals(Integer.valueOf(2 + 1), customer.getBonusPoints());

        List<CustomerBonusLog> bonusLog = customerBonusService.getBonusLogForCustomer(customerId);
        assertEquals(2, bonusLog.size());

        assertEquals(rentPriceExpected, rentPrice.getTotal());

        assertEquals(2, rentPrice.getLeasePrices().size());
        assertEquals(MovieType.NEW_RELEASE, rentPrice.getLeasePrices().get(0).getMovieType());
        assertEquals("Some new movie", rentPrice.getLeasePrices().get(0).getMovieName());
        assertEquals(BigDecimal.valueOf(40 * 6), rentPrice.getLeasePrices().get(0).getPriceRent());

        assertEquals(MovieType.OLD_FILM, rentPrice.getLeasePrices().get(1).getMovieType());
        assertEquals("Some old movie", rentPrice.getLeasePrices().get(1).getMovieName());
        assertEquals(BigDecimal.valueOf(30 + 30), rentPrice.getLeasePrices().get(1).getPriceRent());
    }


    ////    overdue
    @Test
    @Transactional
    @Rollback
    @Ignore("Ignore it for now")
    public void calculatePriceOverDueRent() {

        BigDecimal extraPriceExpected = BigDecimal.valueOf(80);

        MovieRentDetailsModel movieRentDetailsModel = new MovieRentDetailsModel();
        movieRentDetailsModel.setMovieId(1L);
        movieRentDetailsModel.setReturnDate(new DateTime().minusDays(2).toDate());

        PriceCalculationModel createdLease = leaseService.createLease(customerId, new DateTime().minusDays(3), Collections.singletonList(movieRentDetailsModel), employeeId);


        PriceCalculationModel rentPrice = leaseService.calculateOverDuePrice(createdLease.getLeaseId());
        assertEquals(extraPriceExpected, rentPrice.getTotal());
        assertNotNull(rentPrice.getLeasePrices());
        assertEquals(1L, rentPrice.getLeasePrices().size());
        assertEquals("Some new movie", rentPrice.getLeasePrices().get(0).getMovieName());
        assertEquals(MovieType.NEW_RELEASE, rentPrice.getLeasePrices().get(0).getMovieType());
        assertEquals(extraPriceExpected, rentPrice.getLeasePrices().get(0).getPriceRent());
    }

    @Test
    @Transactional
    @Rollback
    @Ignore("Ignore it for now")
    public void calculatePriceOverDueRentWithTwoMovies() {

        MovieRentDetailsModel mr1 = new MovieRentDetailsModel();
        mr1.setMovieId(1L);
        mr1.setReturnDate(new DateTime().minusDays(2).toDate());

        MovieRentDetailsModel mr2 = new MovieRentDetailsModel();
        mr2.setMovieId(2L);
        mr2.setReturnDate(new DateTime().minusDays(1).toDate());

        BigDecimal expectedTotal = BigDecimal.valueOf(40 * 2 + 30);


        PriceCalculationModel createdLease = leaseService.createLease(customerId, new DateTime().minusDays(3), Arrays.asList(mr1, mr2), employeeId);


        PriceCalculationModel rentPrice = leaseService.calculateOverDuePrice(createdLease.getLeaseId());
        assertEquals(expectedTotal, rentPrice.getTotal());

        assertNotNull(rentPrice.getLeasePrices());
        assertEquals(2L, rentPrice.getLeasePrices().size());
        assertEquals("Some new movie", rentPrice.getLeasePrices().get(0).getMovieName());
        assertEquals(MovieType.NEW_RELEASE, rentPrice.getLeasePrices().get(0).getMovieType());
        assertEquals(BigDecimal.valueOf(80), rentPrice.getLeasePrices().get(0).getPriceRent());

        assertEquals("Some old movie", rentPrice.getLeasePrices().get(1).getMovieName());
        assertEquals(MovieType.OLD_FILM, rentPrice.getLeasePrices().get(1).getMovieType());
        assertEquals(BigDecimal.valueOf(30), rentPrice.getLeasePrices().get(1).getPriceRent());
    }

    @Test
    @Transactional
    @Rollback
    @Ignore("Ignore it for now")
    public void calculatePriceOverDueRentWithTreeMovies() {
        // given

        MovieRentDetailsModel mr1 = new MovieRentDetailsModel();
        mr1.setMovieId(3L);
        mr1.setReturnDate(new DateTime().minusDays(2).toDate());

        MovieRentDetailsModel mr2 = new MovieRentDetailsModel();
        mr2.setMovieId(2L);
        mr2.setReturnDate(new DateTime().minusDays(1).toDate());

        MovieRentDetailsModel mr3 = new MovieRentDetailsModel();
        mr3.setMovieId(1L);
        mr3.setReturnDate(new Date());

        BigDecimal expectedTotal = BigDecimal.valueOf(30 * 3);
        // insert lease
        PriceCalculationModel createdLease = leaseService.createLease(1L, new DateTime().minusDays(3), Arrays.asList(mr1, mr2, mr3), 1L);

        // test
        PriceCalculationModel rentPrice = leaseService.calculateOverDuePrice(createdLease.getLeaseId());

        //validate
        assertEquals(expectedTotal, rentPrice.getTotal());
        assertNotNull(rentPrice.getLeasePrices());
        assertEquals(2L, rentPrice.getLeasePrices().size());
        assertEquals("Some regular movie", rentPrice.getLeasePrices().get(0).getMovieName());
        assertEquals(MovieType.REGULAR_FILM, rentPrice.getLeasePrices().get(0).getMovieType());
        assertEquals(BigDecimal.valueOf(60), rentPrice.getLeasePrices().get(0).getPriceRent());

        assertEquals("Some old movie", rentPrice.getLeasePrices().get(1).getMovieName());
        assertEquals(MovieType.OLD_FILM, rentPrice.getLeasePrices().get(1).getMovieType());
        assertEquals(BigDecimal.valueOf(30), rentPrice.getLeasePrices().get(1).getPriceRent());
    }

}
