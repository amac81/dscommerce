package bt.bitclinic.java_accommerce.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bt.bitclinic.java_accommerce.entities.Product;
import bt.bitclinic.java_accommerce.repositories.ProductRepository;

@RestController
@RequestMapping(value = "/products")
public class ProductController{
	
	@Autowired
	private ProductRepository repository;
	

	@GetMapping
	public List<String> teste() {
		
		List <Product> products = repository.findAll();
		List <String> names = new ArrayList<>();
		
		for(Product p : products) {
			names.add(p.getName());
		}
		
		return names;
	}

}
