package bt.bitclinic.java_accommerce.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bt.bitclinic.java_accommerce.dto.ProductDTO;
import bt.bitclinic.java_accommerce.entities.Product;
import bt.bitclinic.java_accommerce.repositories.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
		
		Optional<Product> result = repository.findById(id);
		Product product = result.get();
		ProductDTO productDTO = new ProductDTO(product);
		
		return productDTO;
	}
	
	@Transactional(readOnly = true)
	public List<ProductDTO> findAll() {
		
		List<Product> result = repository.findAll();

		List <ProductDTO> productsDTO = new ArrayList<>(); 
		
		for(Product p: result) {
			productsDTO.add(new ProductDTO(p));
		}
		
		return productsDTO;
	}
	
}
