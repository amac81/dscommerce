package pt.bitclinic.DSCommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pt.bitclinic.DSCommerce.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>
{
}
