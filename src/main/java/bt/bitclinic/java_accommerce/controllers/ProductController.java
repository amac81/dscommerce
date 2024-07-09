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
		
		List <String> names = new ArrayList<>();
		
		names.add("a");
		names.add("b");
		names.add("c");
		
		
		return names;
	}

}
