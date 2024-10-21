package pt.bitclinic.javaaccommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pt.bitclinic.javaaccommerce.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long>
{
	 
	
}
