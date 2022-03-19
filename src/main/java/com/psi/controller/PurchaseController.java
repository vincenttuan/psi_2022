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
import org.springframework.web.bind.annotation.ResponseBody;

import com.psi.entity.Purchase;
import com.psi.entity.PurchaseItem;
import com.psi.repository.EmployeeRepository;
import com.psi.repository.ProductRepository;
import com.psi.repository.PurchaseRepository;
import com.psi.repository.SupplierRepository;

@Controller
@RequestMapping("/purchase")
public class PurchaseController {
	
	@Autowired
	private PurchaseRepository purchaseRepository;
	
	@Autowired
	private SupplierRepository supplierRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	// 採購主檔
	//--------------------------------------------------------------------
	@GetMapping("/")
	public String index(@ModelAttribute Purchase purchase,  Model model) {
		model.addAttribute("_method", "POST");
		model.addAttribute("purchases", purchaseRepository.findAll());
		model.addAttribute("suppliers", supplierRepository.findAll());
		model.addAttribute("employees", employeeRepository.findAll());
		return "purchase";
	}
	
	@GetMapping("/{id}")
	public String get(@PathVariable("id") Long id, Model model) {
		model.addAttribute("_method", "PUT");
		model.addAttribute("purchase", purchaseRepository.getById(id));
		model.addAttribute("purchases", purchaseRepository.findAll());
		model.addAttribute("suppliers", supplierRepository.findAll());
		model.addAttribute("employees", employeeRepository.findAll());
		return "purchase";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		purchaseRepository.deleteById(id);
		return "redirect:../";
	}
	
	@PostMapping("/")
	public String add(@Valid @ModelAttribute Purchase purchase, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("_method", "POST");
			model.addAttribute("purchases", purchaseRepository.findAll());
			model.addAttribute("suppliers", supplierRepository.findAll());
			model.addAttribute("employees", employeeRepository.findAll());
			return "purchase";
		}
		purchaseRepository.save(purchase);
		return "redirect:./";
	}
	
	@PutMapping("/")
	public String update(@Valid @ModelAttribute Purchase purchase, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("_method", "PUT");
			model.addAttribute("purchases", purchaseRepository.findAll());
			model.addAttribute("suppliers", supplierRepository.findAll());
			model.addAttribute("employees", employeeRepository.findAll());
			return "purchase";
		}
		purchaseRepository.save(purchase);
		return "redirect:./";
	}
	// 採購細目
	//--------------------------------------------------------------------
	@RequestMapping("/{pid}/view/item")
	public String viewItem(Model model, @PathVariable("pid") Long pid, @ModelAttribute PurchaseItem purchaseItem) {
		// 同時呈現採購單主檔與採購單細目
		// 採購單主檔(會含採購單細目)
		// 會進行採購單細目的CRUD
		Purchase purchase = purchaseRepository.findById(pid).get();
		model.addAttribute("purchase", purchase);
		model.addAttribute("products", productRepository.findAll());
		model.addAttribute("_method", "POST");
		return "purchaseitem";
	}
}




