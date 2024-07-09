package bt.bitclinic.java_accommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import bt.bitclinic.java_accommerce.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>
{

}
