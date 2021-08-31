package com.example.pi2.repository;

import com.example.pi2.model.Orders;
import com.example.pi2.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends CrudRepository<Orders, Long> {

	@Query("SELECT o FROM Orders o WHERE o.product= :product")
	List<Orders> GetOrderrsByProduct(@Param("product") Product product);

}
