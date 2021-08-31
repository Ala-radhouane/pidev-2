package com.example.pi2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.core.io.Resource;

import com.example.pi2.model.Basket;
import com.example.pi2.model.Category;
import com.example.pi2.service.BaskerService;
import com.example.pi2.service.OrdersService;

@Controller
public class BasketController {

	@Autowired
	  OrdersService ordersService;
	@Autowired
	  BaskerService basketService;
	
	

	//http://localhost:8080/add-basket
	 @PostMapping("/add-basket") 
	  @ResponseBody
   public Basket addorders(@RequestBody Basket b) {
			

		  Basket order = basketService.addBasket(b);
	  return order;
	  }
	
	  // ValidateBasket  :
	  	// http://localhost:8080/ValidateBasket/{id_basket}/{id}/{type-tp}
	@PutMapping("/ValidateBasket/{id_basket}/{id}/{type-tp}")
	@ResponseBody
	public void ValidateOrder(@PathVariable("id_basket") int id_basket, @PathVariable("id") int id,@PathVariable("type-tp") String tp) {
		basketService.ValidateBasket(id_basket, id, tp);
	}
	  //Reduction  :
		// http://localhost:8080/Reduction/{id_basket}
		@PutMapping("/Reduction/{id_basket}")
		@ResponseBody
		public void Reduction(@PathVariable("id_basket") int id_basket) {
			basketService.Reduction(id_basket);
		}
		
		@GetMapping("/download")
		@ResponseBody
		public ResponseEntity<Resource> getFile() {
			String filename = "facture.xlsx";
			InputStreamResource file = new InputStreamResource(basketService.load());

			return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
					.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
		}
		
		// affecter product to basket
				// http://localhost:8080/add-client-a-basket/{id}/{id_basket}
				@PutMapping("/add-client-a-basket/{id}/{id_basket}")
				@ResponseBody
				public void affectClientBaske(@PathVariable("id") int id, @PathVariable("id_basket") int id_basket) {
					basketService.affectClientBasket(id, id_basket);
				}
				
				// http://localhost:8080/retrieve-all-Basket
				@GetMapping("/retrieve-all-Basket")
				@ResponseBody
				public List<Basket> getBasket() {
					List<Basket> list = basketService.retrieveAll();
					return list;
				}

				// http://localhost:8080/retrieve-Basket/{id_basket}
				@GetMapping("/retrieve-Basket/{id_basket}")
				@ResponseBody
				public Basket retrieveBasket(@PathVariable("id_basket") String id_basket) {
					return basketService.retrieveBasket(id_basket);
				}


				// http://localhost:8080/modify-Basket
				@PutMapping("/modify-Basket")
				@ResponseBody
				public Basket modifyBasket(@RequestBody Basket c) {
					return basketService.updateBasket(modifyBasket(null));
				}
	
}
