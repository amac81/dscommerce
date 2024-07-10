package bt.bitclinic.java_accommerce.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	public Page<ProductDTO> findAll(Pageable pageable) {
		
		Page<Product> result = repository.findAll(pageable);
		
		//with lambda expression
		return result.map(x -> new ProductDTO(x));
	}
	
	
	@Transactional(readOnly = false)
	public ProductDTO insert(ProductDTO productDto) {
		
		Product entity = dtoToEntity(productDto); 
		entity = repository.save(entity);
		
		return new ProductDTO(entity);
	}
	
	
	private Product dtoToEntity(ProductDTO productDto) {
		Product entity = new Product();
		
		entity.setName(productDto.getName());
		entity.setDescription(productDto.getDescription());
		entity.setImgUrl(productDto.getImgUrl());
		entity.setPrice(productDto.getPrice());
		
		return entity;
	}
	
}
