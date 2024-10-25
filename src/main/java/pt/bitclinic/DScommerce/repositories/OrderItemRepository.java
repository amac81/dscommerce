package pt.bitclinic.dscommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pt.bitclinic.dscommerce.entities.OrderItem;
import pt.bitclinic.dscommerce.entities.OrderItemPK;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK>
{
	 
	
}
