package com.psi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.psi.entity.Customer;
import com.psi.entity.Order;
import com.psi.repository.CustomerRepository;
import com.psi.repository.EmployeeRepository;
import com.psi.repository.OrderItemRepository;
import com.psi.repository.OrderRepository;
import com.psi.repository.ProductRepository;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	/* 訂單主檔
	 * --------------------------------------------------------------------
	 * GET  -> /            -> index
	 * GET  -> /{id}        -> get
	 * GET  -> /delete/{id} -> delete
	 * POST -> /            -> add
	 * PUT  -> /            -> update
	 ----------------------------------------------------------------------
	 * 訂單細目
	 * --------------------------------------------------------------------
	 * GET  -> /{pid}/item              -> viewItem
	 * GET  -> /{pid}/item/{iid}        -> getItem
	 * GET  -> /{pid}/item/delete/{iid} -> deleteItem
	 * POST -> /{pid}/item              -> addItem
	 * PUT  -> /{pid}/item              -> updateItem
	 ---------------------------------------------------------------------*/
	@GetMapping("/")
	public String index(@ModelAttribute Order order,  Model model) {
		model.addAttribute("_method", "POST");
		model.addAttribute("orders", orderRepository.findAll());
		model.addAttribute("customers", customerRepository.findAll());
		model.addAttribute("employees", employeeRepository.findAll());
		return "order";
	}
}
















