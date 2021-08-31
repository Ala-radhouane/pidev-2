package com.example.pi2.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Client {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
	private String firstname ;
	private String lastname ;
	
	@OneToOne(fetch = FetchType.EAGER)
	private Basket basket;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Basket getBasket() {
		return basket;
	}

	public void setBasket(Basket basket) {
		this.basket = basket;
	}

	public Client(long id, String firstname, String lastname, Basket basket) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.basket = basket;
	} 
	
	public Client(){}

	@Override
	public String toString() {
		return "Client [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", basket=" + basket + "]";
	}
	
	
}
