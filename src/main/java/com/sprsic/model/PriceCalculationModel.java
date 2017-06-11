package com.sprsic.model;

import java.math.BigDecimal;
import java.util.List;

/**
 * Written with love
 *
 * @author Sasa Prsic 11/06/2017
 */
public class PriceCalculationModel {
    private List<MoviePriceRentModel> leasePrices;
    private BigDecimal total;
    private Long leaseId;

    public PriceCalculationModel(List<MoviePriceRentModel> leasePrices, BigDecimal total, Long leaseId) {
        this.leasePrices = leasePrices;
        this.total = total;
        this.leaseId = leaseId;
    }

    public void setLeasePrices(List<MoviePriceRentModel> leasePrices) {
        this.leasePrices = leasePrices;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Long getLeaseId() {
        return leaseId;
    }

    public void setLeaseId(Long leaseId) {
        this.leaseId = leaseId;
    }

    public List<MoviePriceRentModel> getLeasePrices() {
        return leasePrices;
    }

    public BigDecimal getTotal() {
        return total;
    }
}
