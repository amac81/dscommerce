package pt.bitclinic.javaaccommerce.services;

import java.time.Instant;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pt.bitclinic.javaaccommerce.dto.OrderDTO;
import pt.bitclinic.javaaccommerce.dto.OrderItemDTO;
import pt.bitclinic.javaaccommerce.entities.Order;
import pt.bitclinic.javaaccommerce.entities.OrderItem;
import pt.bitclinic.javaaccommerce.entities.OrderStatus;
import pt.bitclinic.javaaccommerce.entities.Product;
import pt.bitclinic.javaaccommerce.exceptions.DatabaseException;
import pt.bitclinic.javaaccommerce.exceptions.ResourceNotFoundException;
import pt.bitclinic.javaaccommerce.repositories.OrderItemRepository;
import pt.bitclinic.javaaccommerce.repositories.OrderRepository;
import pt.bitclinic.javaaccommerce.repositories.ProductRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
		
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthService authService;	
	
	@Transactional(readOnly = true)
	public OrderDTO findById(Long id) {
		
		Optional<Order> result = repository.findById(id);
		Order entity = result.orElseThrow(
				()-> new ResourceNotFoundException("Recurso não encontrado"));
		
		authService.validadeSelfOrAdmin(entity.getClient().getId());
		
		OrderDTO dto = new OrderDTO(entity);
		
		return dto;
	}
	
		
	@Transactional
	public OrderDTO insert(OrderDTO dto) {
		
		Order entity = new Order(); 
		
		entity.setMoment(Instant.now());
		entity.setStatus(OrderStatus.WAITING_PAYMENT);
		entity.setClient(userService.authenticated());
		
		
		for(OrderItemDTO itemDto : dto.getItems()) {
			Product product = productRepository.getReferenceById(itemDto.getProductId());
			OrderItem orderItem = new OrderItem(entity, product, itemDto.getQuantity(), product.getPrice());
			entity.getItems().add(orderItem);
		}
		
		entity = repository.save(entity);
		orderItemRepository.saveAll(entity.getItems());
		
		return new OrderDTO(entity);
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
