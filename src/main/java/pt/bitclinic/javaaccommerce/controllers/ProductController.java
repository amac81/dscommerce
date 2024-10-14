package pt.bitclinic.javaaccommerce.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;
import pt.bitclinic.javaaccommerce.dto.ProductDTO;
import pt.bitclinic.javaaccommerce.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductController{
	
	@Autowired
	private ProductService service;
	

	@GetMapping(value = "/{id}")
	public ResponseEntity<ProductDTO> getById(@PathVariable Long id) {
		ProductDTO dto = service.findById(id); 
		
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping
	public ResponseEntity<Page<ProductDTO>> getAll(@RequestParam(name="name", defaultValue="") String name, Pageable pageable) {
		Page<ProductDTO> page = service.findAll(name, pageable); 
		
		return ResponseEntity.ok(page);
	}
	
	@PostMapping
	public ResponseEntity<ProductDTO> insert(@Valid @RequestBody ProductDTO dto) {
		dto = service.insert(dto); 
		
		//to generate the correct HTTP response code 201 - Created
		//good programming practicePost 
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}").buildAndExpand(dto.getId())
				.toUri();
		
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ProductDTO> updateById(@PathVariable Long id, @Valid @RequestBody ProductDTO dto) {
		dto = service.update(id, dto); 
		
		return ResponseEntity.ok(dto);
	}
	
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	
}
