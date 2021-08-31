package com.example.pi2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity

@AllArgsConstructor
@NoArgsConstructor
public class Orders implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_order;
    private String  Status_order;
    private float fees_order;

    @Temporal(TemporalType.DATE)
    private Date date_order;

    private int quantity ;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Product> product;

    @ManyToOne
    Basket basket;
    
    @ManyToOne
	private Client client;

	public long getId_order() {
		return id_order;
	}

	public void setId_order(long id_order) {
		this.id_order = id_order;
	}

	public String getStatus_order() {
		return Status_order;
	}

	public void setStatus_order(String status_order) {
		Status_order = status_order;
	}

	public float getFees_order() {
		return fees_order;
	}

	public void setFees_order(float fees_order) {
		this.fees_order = fees_order;
	}

	public Date getDate_order() {
		return date_order;
	}

	public void setDate_order(Date date_order) {
		this.date_order = date_order;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Set<Product> getProduct() {
		return product;
	}

	public void setProduct(Set<Product> product) {
		this.product = product;
	}

	public Basket getBasket() {
		return basket;
	}

	public void setBasket(Basket basket) {
		this.basket = basket;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Orders(long id_order, String status_order, float fees_order, Date date_order, int quantity,
			Set<Product> product, Basket basket, Client client) {
		super();
		this.id_order = id_order;
		Status_order = status_order;
		this.fees_order = fees_order;
		this.date_order = date_order;
		this.quantity = quantity;
		this.product = product;
		this.basket = basket;
		this.client = client;
	}

	public Orders(){}

	@Override
	public String toString() {
		return "Orders [id_order=" + id_order + ", Status_order=" + Status_order + ", fees_order=" + fees_order
				+ ", date_order=" + date_order + ", quantity=" + quantity + ", product=" + product + ", basket="
				+ basket + ", client=" + client + "]";
	}
	
	
	
}
