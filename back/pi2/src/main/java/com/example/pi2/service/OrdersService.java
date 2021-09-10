package com.example.pi2.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.example.pi2.model.Basket;
import com.example.pi2.model.Orders;
import com.example.pi2.model.Product;
import com.example.pi2.repository.BasketRepository;
import com.example.pi2.repository.ClientRepository;
import com.example.pi2.repository.OrdersRepository;
import com.example.pi2.repository.ProductRepository;
import com.fasterxml.jackson.core.JacksonException;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;



@Service
public class OrdersService {
	
	@Autowired
	OrdersRepository ordersRepository;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	BasketRepository basketrepository;
	
	@Autowired
	ClientRepository clientrepository;
	
	
	public List<Orders> retrieveAll() {
		return (List<Orders>)ordersRepository.findAll();
	}

	
	public Orders retrieveorders(String ordersId) {
		return ordersRepository.findById(Long.parseLong(ordersId)).orElse(null);	}

	
	public void removeorders(String ordersId) {
		ordersRepository.deleteById(Long.parseLong(ordersId));
	}

	
	public Orders updateorders(Orders id_order) {

		return ordersRepository.save(id_order);
	}

	
	public Orders addorders(Orders o) {
		//fees_order=quantity*price_product
		return ordersRepository.save(o);
		
	}

	
	public void affecterOrdertoBasket(int id_order, int id_basket) {
		Basket b = basketrepository.findById((long) id_basket).orElse(null);
		Orders o = ordersRepository.findById((long) id_order).orElse(null);
		b.getOrders().add(o);
		
		//grant total va etre incrementé dans le basket
		b.setTotal(b.getTotal()+o.getFees_order());
		basketrepository.save(b);
		o.setBasket(b);
		ordersRepository.save(o);
		
	}

	
	
	
	
	public List<Orders> GetOrderrsByProduct(Product product) {
		// TODO Auto-generated method stub
		return ordersRepository.GetOrderrsByProduct(product);
	}
	
	public String exportReport() throws FileNotFoundException, JRException {
		 String path = "C:\\Users\\alara\\JaspersoftWorkspace\\";
	        List<Orders> o = (List<Orders>) ordersRepository.findAll();
	        //load file and compile it
	        File file = ResourceUtils.getFile("classpath:OrderInvoice.jrxml");
	        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
	        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(o,false);
	        Map<String, Object> parameters = new HashMap<>();
	        parameters.put("createdBy", "Ala Radhouane");
	        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
	        JasperExportManager.exportReportToPdfFile(jasperPrint, path + "invoice.pdf");
	        

	        return "report generated in path : " + path;
	    }
	

	

}
