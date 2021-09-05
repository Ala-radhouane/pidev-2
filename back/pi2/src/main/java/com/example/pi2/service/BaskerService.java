package com.example.pi2.service;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pi2.helper.ExcelHelper;
import com.example.pi2.model.Basket;
import com.example.pi2.model.Category;
import com.example.pi2.model.Basket.Paiement;
import com.example.pi2.model.Client;
import com.example.pi2.model.Orders;
import com.example.pi2.model.Product;
import com.example.pi2.repository.BasketRepository;
import com.example.pi2.repository.ClientRepository;
import com.example.pi2.repository.OrdersRepository;

@Service
public class BaskerService {

	@Autowired
	BasketRepository basketRepository;
	@Autowired
	OrdersRepository orderRepository;
	@Autowired
	ClientRepository clientRepository;
	
	public List<Basket> retrieveAll() {
		// TODO Auto-generated method stub
		return (List<Basket>)basketRepository.findAll();
	}

	
	public Basket retrieveBasket(String id_basket) {
		// TODO Auto-generated method stub
		return basketRepository.findById(Long.parseLong(id_basket)).orElse(null);	}

	
	public void removeBasket(String id_basket) {
		// TODO Auto-generated method stub
		basketRepository.deleteById(Long.parseLong(id_basket));
	}

	
	public Basket updateBasket(Basket Basket) {
		// TODO Auto-generated method stub
		return basketRepository.save(Basket);
	}

	
	public Basket addBasket(Basket b) {
		
		return basketRepository.save(b);
	}

	
	public void ValidateBasket(int basketId, int clientId,String tp ) {
		Basket b = basketRepository.findById((long) basketId).orElse(null);
		Client c = clientRepository.findById((long) clientId).orElse(null);
		b.setClient(c);
		List  ods=new ArrayList<>(b.getOrders());
		for(int i=0;i<ods.size();i++){
			Orders o=(Orders) ods.get(i);
			o.setStatus_order("in Delivering");
			
		}
		 if(tp.toUpperCase().equals(Paiement.ATDELIVERY) ){
			  b.setType_paiement(Paiement.ATDELIVERY);
		   }
		 else {
			  b.setType_paiement(Paiement.BYCARD);

		 }
		
		basketRepository.save(b);
		
	}

	
	public void Reduction(int basketId) {
		
		Basket b = basketRepository.findById((long) basketId).orElse(null);
		if(b.getTotal()>99){
			b.setTotal(b.getTotal()-(b.getTotal()*0.05f));

		}
		else if(b.getTotal()>130){
			b.setTotal(b.getTotal()-(b.getTotal()*0.1f));

		}
		else if(b.getTotal()>200){
			b.setTotal(b.getTotal()-(b.getTotal()*0.2f));
			  

		}
		else if(b.getTotal()>299){
			b.setTotal(b.getTotal()-(b.getTotal()*0.4f));

		}
		else {
			b.setTotal(b.getTotal()-(b.getTotal()*0.5f));

		}
		  basketRepository.save(b);
	}
	
	public ByteArrayInputStream load() {
		List<Basket> facture = (List<Basket>) basketRepository.findAll();

		ByteArrayInputStream in = ExcelHelper.factureToExcel(facture);
		return in;
	}
	
	public void affectClientBasket(int id, int id_basket) {
		// TODO Auto-generated method stub
		Client client = clientRepository.findById((long) id).orElse(null);
		Basket basket = basketRepository.findById((long) id_basket).orElse(null);
		basket.setClient(client);
		basketRepository.save(basket);
	}
}
