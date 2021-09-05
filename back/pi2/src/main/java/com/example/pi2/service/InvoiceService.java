package com.example.pi2.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.example.pi2.model.Invoice;
import com.example.pi2.model.Orders;
import com.example.pi2.repository.InvoiceRepository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class InvoiceService {

	@Autowired
	InvoiceRepository invoiceRepository;

	
	public List<Invoice> retrieveAllinvoices() {
		// TODO Auto-generated method stub
		return (List<Invoice>) invoiceRepository.findAll();
	}
	
	public Invoice addinvoice(Invoice p) {
		// TODO Auto-generated method stub
		return invoiceRepository.save(p);
	}

	
	public void deleteInvoice(Long id_invoice) {
		// TODO Auto-generated method stub
		invoiceRepository.deleteById((long) id_invoice);
	}
	
	public String exportReport1() throws FileNotFoundException, JRException {
		 String path = "C:\\Users\\alara\\JaspersoftWorkspace\\";
	        List<Invoice> o = (List<Invoice>) invoiceRepository.findAll();
	        //load file and compile it
	        File file = ResourceUtils.getFile("classpath:Invoice.jrxml");
	        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
	        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(o,false);
	        Map<String, Object> parameters = new HashMap<>();
	        parameters.put("createdBy", "Ala Radhouane");
	        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
	        JasperExportManager.exportReportToPdfFile(jasperPrint, path + "invoice.pdf");
	        

	        return "report generated in path : " + path;
	    }

}
