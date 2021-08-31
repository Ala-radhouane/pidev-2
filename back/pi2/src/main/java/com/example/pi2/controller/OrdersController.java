package com.example.pi2.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.pi2.model.Orders;
import com.example.pi2.model.Product;
import com.example.pi2.repository.OrdersRepository;
import com.example.pi2.service.BaskerService;
import com.example.pi2.service.OrdersService;

import net.sf.jasperreports.engine.JRException;

@RestController
public class OrdersController {

	@Autowired
	OrdersService ordersService;
	@Autowired
	 BaskerService basketService;

	@Autowired
	private OrdersRepository orderrepository;

		
	// afficher les orders with product
	// http://localhost:8080/ordersproduct/{id_prod}
	@GetMapping("/ordersproduct/{id_prod}")
	@ResponseBody
	public List<Orders> getordersbyproducts(@PathVariable("id_prod") Product product) {
		List<Orders> list = ordersService.GetOrderrsByProduct(product);
		return list;
	}
			
	
	 // ADD Order : 
	//http://localhost:8080/add-orders
 @PostMapping("/add-orders")
 @ResponseBody
 public Orders addorders(@RequestBody Orders o) {
	  Orders order = ordersService.addorders(o);
 return order;
 }

// http://localhost:8080/remove-orders/{orders-id}
  @DeleteMapping("/remove-orders/{orders-id}")
  @ResponseBody
  public void removeorders(@PathVariable("orders-id") String ordersId) {
	   ordersService.removeorders(ordersId);
  }
 
  // http://localhost:8080/modify-orders
  @PutMapping("/modify-orders")
  @ResponseBody
  public Orders modifyOrder(@RequestBody Orders ordersId) {
  return ordersService.updateorders(ordersId);
  }


// http://localhost:8080/retrieve-all-orders
@GetMapping("/retrieve-all-orders")
@ResponseBody
public List<Orders> getOrders() {
List<Orders> list = ordersService.retrieveAll();
return list;
}

//http://localhost:8080/report
@GetMapping("/report")
public String generateReport() throws FileNotFoundException, JRException {
    return ordersService.exportReport();
}

// http://localhost:8080/retrieve-orders/{orders-id}
 @GetMapping("/retrieve-orders/{orders-id}")
 @ResponseBody
 public Orders retrieveOrder(@PathVariable("orders-id") String ordersId) {
 return ordersService.retrieveorders(ordersId);
 }
 
 // Affecter order to basket  :
 	// http://localhost:8080/affectOrderToBasket/{id_basket}/{id_order}
@PutMapping("/affectOrderToBasket/{id_basket}/{id_order}")
@ResponseBody
public void affectOrderToBasket(@PathVariable("id_basket") int basketId, @PathVariable("id_order") int orderId) {
	ordersService.affecterOrdertoBasket(orderId, basketId);
}
	
}
