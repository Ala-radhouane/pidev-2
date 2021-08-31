package com.example.pi2.repository;

import com.example.pi2.model.Category;
import com.example.pi2.model.Product;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

	
	@Query("SELECT p FROM Product p WHERE p.name_prod= :name_prod")
	List<Product> SearchProductByName(@Param("name_prod") String name_prod);
	
	@Query("SELECT p FROM Product p WHERE p.category= :category")
	List<Product> SearchProductByCategory(@Param("category") Category category);
	
	
	@Query("from Product order by quantity asc")
	public List<Product> orderByAscendingQantity();
	
	@Query("from Product order by quantity desc")
	public List<Product> orderByDescendingQantity();
}
