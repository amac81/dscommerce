package pt.bitclinic.javaaccommerce.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pt.bitclinic.javaaccommerce.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>
{
	/*@Query("SELECT new pt.bitclinic.javaaccommerce.ProductDTO() "
			+ "FROM Product obj ")*/
	Page<Product> searchByName	(String name, Pageable pageable);
}
