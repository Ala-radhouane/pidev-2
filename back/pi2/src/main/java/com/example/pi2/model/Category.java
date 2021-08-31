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
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_cat ;

    private String name_cat ;

    private String desc_cat ;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Product> product;

	public long getId_cat() {
		return id_cat;
	}

	public void setId_cat(long id_cat) {
		this.id_cat = id_cat;
	}

	public String getName_cat() {
		return name_cat;
	}

	public void setName_cat(String name_cat) {
		this.name_cat = name_cat;
	}

	public String getDesc_cat() {
		return desc_cat;
	}

	public void setDesc_cat(String desc_cat) {
		this.desc_cat = desc_cat;
	}

	public Set<Product> getProduct() {
		return product;
	}

	public void setProduct(Set<Product> product) {
		this.product = product;
	}

	public Category(long id_cat, String name_cat, String desc_cat, Set<Product> product) {
		super();
		this.id_cat = id_cat;
		this.name_cat = name_cat;
		this.desc_cat = desc_cat;
		this.product = product;
	}
    
	public Category (){}

	@Override
	public String toString() {
		return "Category [id_cat=" + id_cat + ", name_cat=" + name_cat + ", desc_cat=" + desc_cat + ", product="
				+ product + "]";
	}
	
	
}
