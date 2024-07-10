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
		Product entity = result.get();
		ProductDTO dto = new ProductDTO(entity);
		
		return dto;
	}
	
	@Transactional(readOnly = true)
	public Page<ProductDTO> findAll(Pageable pageable) {
		
		Page<Product> result = repository.findAll(pageable);
		
		//with lambda expression
		return result.map(x -> new ProductDTO(x));
	}
	
	
	@Transactional(readOnly = false)
	public ProductDTO insert(ProductDTO dto) {
		
		Product entity = dtoToEntity(dto); 
		entity = repository.save(entity);
		
		return new ProductDTO(entity);
	}
	
	
	private Product dtoToEntity(ProductDTO dto) {
		Product entity = new Product();
		
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setImgUrl(dto.getImgUrl());
		entity.setPrice(dto.getPrice());
		
		return entity;
	}
	
}
