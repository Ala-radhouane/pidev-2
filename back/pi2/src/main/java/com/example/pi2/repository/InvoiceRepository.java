package com.example.pi2.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.pi2.model.Invoice;

@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, Long> {

}
