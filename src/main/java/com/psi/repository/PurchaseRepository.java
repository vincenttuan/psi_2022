package com.psi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.psi.entity.Purchase;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
	
}
