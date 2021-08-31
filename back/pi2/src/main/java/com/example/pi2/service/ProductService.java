package com.example.pi2.service;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.pi2.model.Category;
import com.example.pi2.model.Product;
import com.example.pi2.repository.CategoryRepository;
import com.example.pi2.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository productRepository;

	@Autowired
	CategoryRepository catRepository;

	@Autowired
	OrdersService ordersService;

	
	public List<Product> retrieveAllProducts() {
		// TODO Auto-generated method stub
		return (List<Product>) productRepository.findAll();
	}

	
	public Product addProduct(Product p) {
		// TODO Auto-generated method stub
		return productRepository.save(p);
	}

	
	public void deleteProduct(Long id_prod) {
		// TODO Auto-generated method stub
		productRepository.deleteById((long) id_prod);
	}

	
	public Product updateProduct(Product p) {
		// TODO Auto-generated method stub
		return productRepository.save(p);
	}

	
	public Product retrieveProduct(String id_prod) {
		// TODO Auto-generated method stub
		return productRepository.findById(Long.parseLong(id_prod)).orElse(null);
	}


	public void affectCategoryProduct(int id_cat, int id_prod) {
		// TODO Auto-generated method stub
		Category category = catRepository.findById((long) id_cat).orElse(null);
		Product product = productRepository.findById((long) id_prod).orElse(null);
		product.setCategory(category);
		productRepository.save(product);
	}

	
	public Map<Product, Integer> getProductsSells() {
		// TODO Auto-generated method stub
		List<Product> products = (List<Product>) productRepository.findAll();
		Map<Product, Integer> productsSells = new HashMap<>();
		products.forEach(p -> {
			int sells = ordersService.GetOrderrsByProduct(p).size();
			productsSells.put(p, sells);

		});
		return productsSells;
	}

	
	public Map<Product, Integer> getTopNProducts(int n) {
		Map<Product, Integer> productsSells = getProductsSells();
		return productsSells.entrySet().stream().sorted(Entry.<Product, Integer>comparingByValue().reversed()).limit(n)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue,
						LinkedHashMap::new));

		// productsSells;

	}
	
	
	public List<Product> SearchProductByName(String name_prod) {
		// TODO Auto-generated method stub
		return productRepository.SearchProductByName(name_prod);
	}

	
	public List<Product> retrieveProductByCategory(Category category) {
		// TODO Auto-generated method stub
		return (List<Product>) productRepository.SearchProductByCategory(category);
	}


	
	public List<Product> orderByAscendingQantity() {
		// TODO Auto-generated method stub
		return productRepository.orderByAscendingQantity();
	}

	
	public List<Product> orderByDescendingQantity() {
		// TODO Auto-generated method stub
		return productRepository.orderByDescendingQantity();
	}

}
