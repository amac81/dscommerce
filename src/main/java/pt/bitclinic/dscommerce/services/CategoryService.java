package pt.bitclinic.dscommerce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import pt.bitclinic.dscommerce.dto.CategoryDTO;
import pt.bitclinic.dscommerce.entities.Category;
import pt.bitclinic.dscommerce.exceptions.DatabaseException;
import pt.bitclinic.dscommerce.exceptions.ResourceNotFoundException;
import pt.bitclinic.dscommerce.repositories.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repository;
	
	@Transactional(readOnly = true)
	public CategoryDTO findById(Long id) {
		
		Optional<Category> result = repository.findById(id);
		Category entity = result.orElseThrow(
				()-> new ResourceNotFoundException("Recurso não encontrado"));
		CategoryDTO dto = new CategoryDTO(entity);
		
		return dto;
	}
			
	@Transactional(readOnly = true)
	public List<CategoryDTO> findAll() {
		List<Category> result = repository.findAll();
		
		//with lambda expression
		return result.stream().map(x -> new CategoryDTO(x)).toList();
	}
	
	
	@Transactional
	public CategoryDTO insert(CategoryDTO dto) {
		
		Category entity = new Category(); 
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		
		entity = repository.save(entity);
		
		return new CategoryDTO(entity);
	}
	
	@Transactional
	public CategoryDTO update(Long id, CategoryDTO dto) {
		try {
		//does not go to the database; object monitored by JPA
		Category entity = repository.getReferenceById(id); 
		
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		
		entity = repository.save(entity);
		
		return new CategoryDTO(entity);
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
	

}
