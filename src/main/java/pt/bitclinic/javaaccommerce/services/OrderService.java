package pt.bitclinic.javaaccommerce.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import pt.bitclinic.javaaccommerce.dto.OrderDTO;
import pt.bitclinic.javaaccommerce.entities.Order;
import pt.bitclinic.javaaccommerce.exceptions.DatabaseException;
import pt.bitclinic.javaaccommerce.exceptions.ResourceNotFoundException;
import pt.bitclinic.javaaccommerce.repositories.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repository;
	
	@Transactional(readOnly = true)
	public OrderDTO findById(Long id) {
		
		Optional<Order> result = repository.findById(id);
		Order entity = result.orElseThrow(
				()-> new ResourceNotFoundException("Recurso não encontrado"));
		OrderDTO dto = new OrderDTO(entity);
		
		return dto;
	}
	
		
	@Transactional
	public OrderDTO insert(OrderDTO dto) {
		
		Order entity = new Order(); 
		copyDtoToEntity(dto, entity);
		
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		
		return new OrderDTO(entity);
	}
	
	@Transactional
	public OrderDTO update(Long id, OrderDTO dto) {
		try {
		//does not go to the database; object monitored by JPA
			Order entity = repository.getReferenceById(id); 
		
			copyDtoToEntity(dto, entity);		
			entity = repository.save(entity);
		
			return new OrderDTO(entity);
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
	
	private void copyDtoToEntity(OrderDTO dto, Order entity) {
		entity.setClient(entity.getClient());
		entity.setId(dto.getId());
		entity.setMoment(dto.getMoment());
		entity.getPayment().setId(dto.getPayment().getId());
		entity.getPayment().setMoment(dto.getPayment().getMoment());
		
		
		entity.setStatus(dto.getStatus());
		
		
	}

	
	

}
