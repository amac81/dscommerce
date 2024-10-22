package pt.bitclinic.DSCommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pt.bitclinic.DSCommerce.entities.OrderItem;
import pt.bitclinic.DSCommerce.entities.OrderItemPK;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK>
{
	 
	
}
