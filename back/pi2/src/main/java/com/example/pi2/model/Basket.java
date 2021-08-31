package com.example.pi2.model;



import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
public class Basket implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_basket ;

    @Temporal(TemporalType.DATE)
    private Date date_basket ;

    private float total ;

    @Enumerated(EnumType.STRING)
    private Paiement type_paiement;

    public enum Paiement {
        ATTHEDELIVERY,BYCARD;
    }

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Orders> orders;
    
    @OneToOne
    private Client client;
    
    @OneToOne
    private Product product;
    

	public long getId_basket() {
		return id_basket;
	}

	public void setId_basket(long id_basket) {
		this.id_basket = id_basket;
	}

	public Date getDate_basket() {
		return date_basket;
	}

	public void setDate_basket(Date date_basket) {
		this.date_basket = date_basket;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public Paiement getType_paiement() {
		return type_paiement;
	}

	public void setType_paiement(Paiement type_paiement) {
		this.type_paiement = type_paiement;
	}

	public Set<Orders> getOrders() {
		return orders;
	}

	public void setOrders(Set<Orders> orders) {
		this.orders = orders;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Basket(long id_basket, Date date_basket, float total, Paiement type_paiement, Set<Orders> orders,
			Client client) {
		super();
		this.id_basket = id_basket;
		this.date_basket = date_basket;
		this.total = total;
		this.type_paiement = type_paiement;
		this.orders = orders;
		this.client = client;
	} 
	public Basket(){}

	@Override
	public String toString() {
		return "Basket [id_basket=" + id_basket + ", date_basket=" + date_basket + ", total=" + total
				+ ", type_paiement=" + type_paiement + ", orders=" + orders + ", client=" + client + "]";
	}
    
	
	
}
