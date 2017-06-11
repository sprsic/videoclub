package com.sprsic.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.List;

/**
 * Written with love
 *
 * @author Sasa Prsic 11/06/2017
 */
@Entity
@Table(name = "movie", uniqueConstraints = @UniqueConstraint(name = "MC_01", columnNames = {"name"}))
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "movie_id")
    private Long movieId;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "year", nullable = false)
    private Integer year;
    @ManyToMany
    @JoinTable(
            name = "movie_genre",
            joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_name", referencedColumnName = "genre_name"))
    private List<Genre> genres;

    @Column(name = "movie_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private MovieType movieType;

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public MovieType getMovieType() {
        return movieType;
    }

    public void setMovieType(MovieType movieType) {
        this.movieType = movieType;
    }
}
