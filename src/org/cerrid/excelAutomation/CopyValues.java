package org.cerrid.excelAutomation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.cerrid.webAutomation.DataFields;
import org.cerrid.webAutomation.WebSiteConstants;

public class CopyValues {

	private int rowNum = -1;
	private int columnNum = -1;
	Logger logger = Logger.getLogger(CopyValues.class);

	public List<DataFields> readDataFile(String riskFilePath, int entryCount) {
		List<DataFields> dataFieldList = new ArrayList<>();
		try {
			FileInputStream file = new FileInputStream(new File(riskFilePath));

			XSSFWorkbook workbook = new XSSFWorkbook(file);

			XSSFSheet sheet = workbook.getSheet(WebSiteConstants.SHEET_1);

			getPasteNums(sheet);

			DataFields dataFields = null;

			for (int rowNumber = rowNum + 1, count = 0; count < entryCount; rowNumber++, count++) {
				Row r = sheet.getRow(rowNumber);
				if (r == null) {
					continue;
				}
				Cell indexCell = r.getCell(columnNum + 0, Row.RETURN_BLANK_AS_NULL);
				Cell inputPriceCell = r.getCell(columnNum + 1, Row.RETURN_BLANK_AS_NULL);
				Cell inputSpreadCell = r.getCell(columnNum + 2, Row.RETURN_BLANK_AS_NULL);
				if (inputPriceCell == null) {
					dataFields = new DataFields(WebSiteConstants.INDICES_TYPE_CMBX, indexCell.getStringCellValue(),
							inputSpreadCell.getNumericCellValue(), WebSiteConstants.INPUT_TYPE_SPREAD, r.getRowNum(),
							columnNum + 3);
				} else {
					dataFields = new DataFields(WebSiteConstants.INDICES_TYPE_CMBX, indexCell.getStringCellValue(),
							inputPriceCell.getNumericCellValue(), WebSiteConstants.INPUT_TYPE_PRICE, r.getRowNum(),
							columnNum + 4);
				}
				dataFieldList.add(dataFields);
			}

			file.close();
			FileOutputStream out = new FileOutputStream(new File(riskFilePath));
			workbook.write(out);
			out.close();
			workbook.close();
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		return dataFieldList;
	}

	private void getPasteNums(XSSFSheet sheet) {
		for (int rowNumber = sheet.getFirstRowNum(); rowNumber <= sheet.getLastRowNum(); rowNumber++) {
			Row r = sheet.getRow(rowNumber);
			if (r == null) {
				continue;
			}
			for (int cn = 0; cn < r.getLastCellNum(); cn++) {
				Cell c = r.getCell(cn, Row.RETURN_BLANK_AS_NULL);
				if (c == null) {
					// The spreadsheet is empty in this cell
				} else {
					try {
						if (c.getStringCellValue().equalsIgnoreCase(WebSiteConstants.INDEX_COLUMN_NAME)) {
							columnNum = cn;
							rowNum = rowNumber;
							return;
						}
					} catch (Exception e) {
						logger.error(e);
						e.printStackTrace();
						continue;
					}
				}
			}
		}
	}

	public List<DataFields> copyDataFromExcel(String filePath, int entryCount) {
		logger.info("## copyDataFromExcel method");
		CopyValues copyValues = new CopyValues();
		List<DataFields> dataFieldList = copyValues.readDataFile(filePath, entryCount);
		return dataFieldList;
	}

	public static void main(String[] args) {
		CopyValues copyValues = new CopyValues();
		copyValues.copyDataFromExcel("D:\\Anshu\\Downloads\\ms\\CDS_RiskCalcs_UD.xlsm", 4);
		System.out.println("HALO");
	}
}
