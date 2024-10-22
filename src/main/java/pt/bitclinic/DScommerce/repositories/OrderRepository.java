package pt.bitclinic.DSCommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pt.bitclinic.DSCommerce.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long>
{
	 
	
}
