package pt.bitclinic.javaaccommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pt.bitclinic.javaaccommerce.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>
{
}
