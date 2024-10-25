package pt.bitclinic.dscommerce.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import pt.bitclinic.dscommerce.dto.CategoryDTO;
import pt.bitclinic.dscommerce.dto.ProductDTO;
import pt.bitclinic.dscommerce.dto.ProductMinDTO;
import pt.bitclinic.dscommerce.entities.Category;
import pt.bitclinic.dscommerce.entities.Product;
import pt.bitclinic.dscommerce.exceptions.DatabaseException;
import pt.bitclinic.dscommerce.exceptions.ResourceNotFoundException;
import pt.bitclinic.dscommerce.repositories.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
		
		Optional<Product> result = repository.findById(id);
		Product entity = result.orElseThrow(
				()-> new ResourceNotFoundException("Recurso não encontrado"));
		ProductDTO dto = new ProductDTO(entity);
		
		return dto;
	}
	
	@Transactional(readOnly = true)
	public Page<ProductMinDTO> findAllMin(String name, Pageable pageable) {
		
		Page<Product> result = repository.searchByName(name, pageable);
		
		//with lambda expression
		return result.map(x -> new ProductMinDTO(x));
	}
	
	@Transactional(readOnly = true)
	public Page<ProductDTO> findAllComplete(String name, Pageable pageable) {
		
		Page<Product> result = repository.findProductsCategories(name, pageable);
		
		//with lambda expression
		return result.map(x -> new ProductDTO(x));
	}
	
	
	@Transactional
	public ProductDTO insert(ProductDTO dto) {
		
		Product entity = new Product(); 
		
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		
		return new ProductDTO(entity);
	}
	
	@Transactional
	public ProductDTO update(Long id, ProductDTO dto) {
		try {
		//does not go to the database; object monitored by JPA
		Product entity = repository.getReferenceById(id); 
		
		copyDtoToEntity(dto, entity);		
		entity = repository.save(entity);
		
		return new ProductDTO(entity);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Recurso não encontrado");
		}
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public void deleteById(Long id) {
		if(!repository.existsById(id)) 
		{
			throw new ResourceNotFoundException("Recurso não encontrado");
		}
		try {
			repository.deleteById(id);
		}catch (DataIntegrityViolationException e) 
		{
        	throw new DatabaseException("Falha de integridade referencial");
		}	
	}
	
	private void copyDtoToEntity(ProductDTO dto, Product entity) {
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setImgUrl(dto.getImgUrl());
		entity.setPrice(dto.getPrice());
		
		//clear categories List
		entity.getCategories().clear();
		
		for(CategoryDTO catDto : dto.getCategories() ) {
			
			Category cat = new Category();
			cat.setId(catDto.getId());
			cat.setName(catDto.getName());
			
			entity.getCategories().add(cat);
		}
	}

	
	

}
