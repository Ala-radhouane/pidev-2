package com.example.pi2.controller;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.pi2.model.Invoice;
import com.example.pi2.service.InvoiceService;

import net.sf.jasperreports.engine.JRException;

@RestController
public class InvoiceController {
	
	@Autowired
	InvoiceService inService;
	
	        // http://localhost:8080/retrieve-all-invoices
			@GetMapping("/retrieve-all-invoices")
			@ResponseBody
			public List<Invoice> getinvoicess() {
				List<Invoice> list = inService.retrieveAllinvoices();
				return list;
			}
			
			//  http://localhost:8080/add-invoice
			@PostMapping("/add-invoice")
			@ResponseBody
			public Invoice addinvoice(@RequestBody Invoice p) {
				Invoice prod = inService.addinvoice(p);
				return prod;
			}
			
			// http://localhost:8080/remove-invoice/{prod-id}
			@DeleteMapping("/remove-invoice/{invoice-id}")
			@ResponseBody
			public void removeinvoice(@PathVariable("invoice-id") Long id_invoice) {
				inService.deleteInvoice(id_invoice);
				
			}
			
			//http://localhost:8080/reportinvoice
			@GetMapping("/reportinvoice")
			public String generateReport() throws FileNotFoundException, JRException {
			    return inService.exportReport1();
			}

}
