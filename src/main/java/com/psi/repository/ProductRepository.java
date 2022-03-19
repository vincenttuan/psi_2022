package com.psi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.psi.entity.Product;
import com.psi.entity.view.Inventory;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	@Query(nativeQuery = true,
		   value = "SELECT p.id, p.name, p.cost, p.price, \n" + 
		   		"(SELECT sum(amount) FROM purchase_items WHERE product_id = p.id limit 1) as amount1, \n" + 
		   		"(SELECT sum(amount) FROM order_items WHERE product_id = p.id limit 1) as amount2 \n" + 
		   		"FROM products p")
	List<Inventory> queryInventories();
	
	@Query(nativeQuery = true,
			   value = "SELECT p.id, p.name, p.cost, p.price, \n" + 
			   		"(SELECT sum(amount) FROM purchase_items WHERE product_id = p.id limit 1) as amount1, \n" + 
			   		"(SELECT sum(amount) FROM order_items WHERE product_id = p.id limit 1) as amount2 \n" + 
			   		"FROM products p WHERE p.id=:id")
	Inventory findInventoryById(Long id);
	
}
