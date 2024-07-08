package bt.bitclinic.java_accommerce.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = "/products")
public class ProductController{

	@GetMapping
	public String teste() {
		return "Hello World";
	}

}