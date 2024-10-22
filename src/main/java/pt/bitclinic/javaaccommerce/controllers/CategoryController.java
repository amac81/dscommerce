	package pt.bitclinic.javaaccommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pt.bitclinic.javaaccommerce.dto.CategoryDTO;
import pt.bitclinic.javaaccommerce.services.CategoryService;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController{
	
	@Autowired
	private CategoryService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<CategoryDTO> getById(@PathVariable Long id) {
		CategoryDTO dto = service.findById(id); 
		
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping
	public ResponseEntity <List<CategoryDTO>> getAll() {
		List<CategoryDTO> list = service.findAll();
		
		return ResponseEntity.ok(list);
	}
	
	
}
