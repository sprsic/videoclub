package com.sprsic.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

/**
 * Written with love
 *
 * @author Sasa Prsic 11/06/2017
 */
@Entity
@Table(name = "lease")
public class Lease {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "lease_id")
    private Long leaseId;
    @Temporal(TemporalType.DATE)
    @Column(name = "lease_date", nullable = false)
    private Date leaseDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn(name = "customer_id")
    private Customer customer;
    @PrimaryKeyJoinColumn(name = "movie_id")
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<LeaseMovie> leaseDetails;
    @PrimaryKeyJoinColumn(name = "employee_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Employee employee;

    public Long getLeaseId() {
        return leaseId;
    }

    public void setLeaseId(Long leaseId) {
        this.leaseId = leaseId;
    }

    public Date getLeaseDate() {
        return leaseDate;
    }

    public void setLeaseDate(Date leaseDate) {
        this.leaseDate = leaseDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<LeaseMovie> getLeaseDetails() {
        return leaseDetails;
    }

    public void setLeaseDetails(List<LeaseMovie> leaseDetails) {
        this.leaseDetails = leaseDetails;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
