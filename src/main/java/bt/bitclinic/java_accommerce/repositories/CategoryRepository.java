package bt.bitclinic.java_accommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import bt.bitclinic.java_accommerce.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>
{

}
