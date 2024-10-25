package pt.bitclinic.dscommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pt.bitclinic.dscommerce.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long>
{
	 
	
}
