package bt.bitclinic.java_accommerce.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/products")
public class ProductController{

	@GetMapping
	public List<String> teste() {
		
		List<String> nomes = new ArrayList<>();
		
		nomes.add("Maria");
		nomes.add("Ana");
		nomes.add("Jorge");
		
		
		
		return nomes;
	}

}
