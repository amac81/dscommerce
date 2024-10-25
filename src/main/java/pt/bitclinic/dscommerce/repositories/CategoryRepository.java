package pt.bitclinic.dscommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pt.bitclinic.dscommerce.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>
{
}
