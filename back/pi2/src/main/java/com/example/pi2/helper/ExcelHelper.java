package com.example.pi2.helper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.pi2.model.Basket;
import com.example.pi2.model.Product;



public class ExcelHelper {

	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String[] HEADERs = { "date_basket", "total", "type_paiement","Client name","Product name","Product description","product price" };
	static String SHEET = "facture";

	public static ByteArrayInputStream factureToExcel(List<Basket> facture) {

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			Sheet sheet = workbook.createSheet(SHEET);

			// Header
			Row headerRow = sheet.createRow(0);

			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(HEADERs[col]);
			}

			int rowIdx = 1;
			for (Basket b : facture) {
				Row row = sheet.createRow(rowIdx++);
		
				row.createCell(0).setCellValue(b.getDate_basket());
				row.createCell(1).setCellValue(b.getTotal());
				row.createCell(2).setCellValue(b.getType_paiement().toString());
				row.createCell(3).setCellValue(b.getClient().getFirstname());
				row.createCell(4).setCellValue(b.getProduct().getName_prod());
				row.createCell(5).setCellValue(b.getProduct().getDescription_prod());
				row.createCell(6).setCellValue(b.getProduct().getPrice_prod());


							}
			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
		}
	}
}
