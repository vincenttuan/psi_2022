package com.psi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.psi.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
