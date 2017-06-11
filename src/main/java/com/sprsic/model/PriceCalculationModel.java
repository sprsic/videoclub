package com.sprsic.model;

import java.math.BigDecimal;
import java.util.List;

/**
 * Written with love
 *
 * @author Sasa Prsic 11/06/2017
 */
public class PriceCalculationModel {
    List<MoviePriceRentModel> leasePrices;
    BigDecimal total;

    public PriceCalculationModel(List<MoviePriceRentModel> leasePrices, BigDecimal total) {
        this.leasePrices = leasePrices;
        this.total = total;
    }

    public List<MoviePriceRentModel> getLeasePrices() {
        return leasePrices;
    }

    public BigDecimal getTotal() {
        return total;
    }
}
