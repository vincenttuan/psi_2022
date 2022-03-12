package com.psi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.psi.entity.Department;
import com.psi.repository.DepartmentRepository;

@Controller
@RequestMapping("/department")
public class DepartmentController {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	// 部門維護首頁
	@GetMapping("/")
	public String index(@ModelAttribute Department department,  Model model) {
		model.addAttribute("_method", "POST");
		model.addAttribute("departments", departmentRepository.findAll());
		return "department";
	}
	
	@GetMapping("/{id}")
	public String get(@PathVariable("id") Long id, Model model) {
		model.addAttribute("_method", "PUT");
		model.addAttribute("department", departmentRepository.getById(id));
		model.addAttribute("departments", departmentRepository.findAll());
		return "department";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		departmentRepository.deleteById(id);
		return "redirect:../";
	}
	
	@PostMapping("/")
	public String add(@ModelAttribute Department department) {
		departmentRepository.save(department);
		return "redirect:./";
	}
	
	@PutMapping("/")
	public String update(@ModelAttribute Department department) {
		departmentRepository.save(department);
		return "redirect:./";
	}
	
}
