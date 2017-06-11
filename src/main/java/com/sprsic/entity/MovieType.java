package com.sprsic.entity;

import java.math.BigDecimal;

/**
 * Written with love
 *
 * @author Sasa Prsic 11/06/2017
 */
public enum MovieType {
    NEW_RELEASE(BigDecimal.valueOf(40), 2),
    REGULAR_FILM(BigDecimal.valueOf(30), 1),
    OLD_FILM(BigDecimal.valueOf(30), 1);

    MovieType(BigDecimal price, int bonusPoints) {
        this.rentPrice = price;
        this.bonusPoints = bonusPoints;
    }

    private final BigDecimal rentPrice;
    private final int bonusPoints;

    public BigDecimal getRentPrice() {
        return this.rentPrice;
    }

    public int getBonusPoints() {
        return this.bonusPoints;
    }
}
