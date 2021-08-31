package com.example.pi2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_prod ;

    private String name_prod ;

    private String description_prod ;

    private int quantity ;

    private float price_prod ;

    private int barcode_prod ;

    private String weight_prod ;

    private int minvalue_stock ;

    @ManyToMany(mappedBy="product", cascade = CascadeType.ALL)
    private Set<Orders> orders;

    @ManyToOne
    Category category;
    
    @OneToOne(fetch = FetchType.EAGER)
	private Basket basket;
    
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public long getId_prod() {
		return id_prod;
	}

	public void setId_prod(long id_prod) {
		this.id_prod = id_prod;
	}

	public String getName_prod() {
		return name_prod;
	}

	public void setName_prod(String name_prod) {
		this.name_prod = name_prod;
	}

	public String getDescription_prod() {
		return description_prod;
	}

	public void setDescription_prod(String description_prod) {
		this.description_prod = description_prod;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getPrice_prod() {
		return price_prod;
	}

	public void setPrice_prod(float price_prod) {
		this.price_prod = price_prod;
	}

	public int getBarcode_prod() {
		return barcode_prod;
	}

	public void setBarcode_prod(int barcode_prod) {
		this.barcode_prod = barcode_prod;
	}

	public String getWeight_prod() {
		return weight_prod;
	}

	public void setWeight_prod(String weight_prod) {
		this.weight_prod = weight_prod;
	}

	public int getMinvalue_stock() {
		return minvalue_stock;
	}

	public void setMinvalue_stock(int minvalue_stock) {
		this.minvalue_stock = minvalue_stock;
	}

	public Set<Orders> getOrders() {
		return orders;
	}

	public void setOrders(Set<Orders> orders) {
		this.orders = orders;
	}

	public Product(long id_prod, String name_prod, String description_prod, int quantity, float price_prod,
			int barcode_prod, String weight_prod, int minvalue_stock, Set<Orders> orders) {
		super();
		this.id_prod = id_prod;
		this.name_prod = name_prod;
		this.description_prod = description_prod;
		this.quantity = quantity;
		this.price_prod = price_prod;
		this.barcode_prod = barcode_prod;
		this.weight_prod = weight_prod;
		this.minvalue_stock = minvalue_stock;
		this.orders = orders;
	}
    
    
	public Product(){}

	@Override
	public String toString() {
		return "Product [id_prod=" + id_prod + ", name_prod=" + name_prod + ", description_prod=" + description_prod
				+ ", quantity=" + quantity + ", price_prod=" + price_prod + ", barcode_prod=" + barcode_prod
				+ ", weight_prod=" + weight_prod + ", minvalue_stock=" + minvalue_stock + ", orders=" + orders + "]";
	}
	
	
}
