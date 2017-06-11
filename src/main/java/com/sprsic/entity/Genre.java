package com.sprsic.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Written with love
 *
 * @author Sasa Prsic 11/06/2017
 */
@Entity
@Table(name = "genre")
public class Genre {

    @Id
    @Column(name = "genre_name")
    private String genreName;

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }
}
