package com.psi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "products")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	@Size(min = 2, max = 50, message = "{product.name.size}")
	private String name;
	
	@Column
	@NotNull(message = "{product.cost.notnull}")
	@Range(min = 10, max = 1000, message = "{product.cost.range}")
	private Integer cost;
	
	@Column
	@NotNull(message = "{product.price.notnull}")
	@Range(min = 10, max = 1000, message = "{product.price.range}")
	private Integer price;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}
	
	
	
}
