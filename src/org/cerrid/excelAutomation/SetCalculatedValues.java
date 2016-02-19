package org.cerrid.excelAutomation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.cerrid.webAutomation.DataFields;
import org.cerrid.webAutomation.WebSiteConstants;

public class SetCalculatedValues {

	static Logger logger = Logger.getLogger(SetCalculatedValues.class);
	
	public void readDataFile(List<DataFields> dataFieldsList, String riskPath) {
		try {
			FileInputStream file = new FileInputStream(new File(riskPath));

			XSSFWorkbook workbook = new XSSFWorkbook(file);

			XSSFSheet sheet = workbook.getSheet(WebSiteConstants.SHEET_1);

			setNewValues(sheet, dataFieldsList);
			file.close();
			FileOutputStream out = new FileOutputStream(new File(riskPath));
			workbook.write(out);
			out.close();
			workbook.close();
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
	}

	private void setNewValues(XSSFSheet sheet, List<DataFields> dataFieldsList) {
		int indexColumn;
		for(DataFields dataFields : dataFieldsList) {
			Row r = sheet.getRow(dataFields.getRowNumber());
			if(dataFields.getInputType().equalsIgnoreCase(WebSiteConstants.INPUT_TYPE_PRICE)) {
				indexColumn = dataFields.getColumnNumber() - 4;
			}
			else {
				indexColumn = dataFields.getColumnNumber() - 3;
			}
			Cell indexCell = r.getCell(indexColumn);
			if(indexCell.getStringCellValue().trim().equalsIgnoreCase(dataFields.getIndices())) {
				Cell c = r.getCell(dataFields.getColumnNumber());
				c.setCellValue(dataFields.getCalculatedValue());
			}
		}
	}

}
