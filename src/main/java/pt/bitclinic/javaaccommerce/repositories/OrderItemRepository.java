package pt.bitclinic.javaaccommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pt.bitclinic.javaaccommerce.entities.OrderItem;
import pt.bitclinic.javaaccommerce.entities.OrderItemPK;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK>
{
	 
	
}
