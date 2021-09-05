package com.example.pi2.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private long id_invoice ;

	    private int Reference ;

	    private String Date ;

	    private String due_date ;

	    @Enumerated(EnumType.STRING)
	    private currency money;

	    public enum currency {
	        EURO,Dinar;
	    }
	    
	    @OneToOne
	    private Client client;

		

		public long getId_invoice() {
			return id_invoice;
		}

		public void setId_invoice(long id_invoice) {
			this.id_invoice = id_invoice;
		}

		public Client getClient() {
			return client;
		}

		public void setClient(Client client) {
			this.client = client;
		}

		public int getReference() {
			return Reference;
		}

		public void setReference(int reference) {
			Reference = reference;
		}

		public String getDate() {
			return Date;
		}

		public void setDate(String date) {
			Date = date;
		}

		public String getDue_date() {
			return due_date;
		}

		public void setDue_date(String due_date) {
			this.due_date = due_date;
		}

		public currency getMoney() {
			return money;
		}

		public void setMoney(currency money) {
			this.money = money;
		}
	    
	    


}
