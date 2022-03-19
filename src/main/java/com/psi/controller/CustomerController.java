package com.psi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.psi.entity.Customer;
import com.psi.repository.CustomerRepository;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	// 客戶維護首頁
	@GetMapping("/")
	public String index(@ModelAttribute Customer customer,  Model model) {
		model.addAttribute("_method", "POST");
		model.addAttribute("customers", customerRepository.findAll());
		return "customer";
	}
	
	@GetMapping("/{id}")
	public String get(@PathVariable("id") Long id, Model model) {
		model.addAttribute("_method", "PUT");
		model.addAttribute("customer", customerRepository.getById(id));
		model.addAttribute("customers", customerRepository.findAll());
		return "customer";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		customerRepository.deleteById(id);
		return "redirect:../";
	}
	
	@PostMapping("/")
	public String add(@Valid @ModelAttribute Customer customer, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("_method", "POST");
			model.addAttribute("customers", customerRepository.findAll());
			return "customer";
		}
		customerRepository.save(customer);
		return "redirect:./";
	}
	
	@PutMapping("/")
	public String update(@Valid @ModelAttribute Customer customer, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("_method", "PUT");
			model.addAttribute("customers", customerRepository.findAll());
			return "customer";
		}
		customerRepository.save(customer);
		return "redirect:./";
	}
	
}
