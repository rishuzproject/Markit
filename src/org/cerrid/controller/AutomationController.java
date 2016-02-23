package org.cerrid.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.cerrid.excelAutomation.CopyValues;
import org.cerrid.excelAutomation.SetCalculatedValues;
import org.cerrid.webAutomation.DataFields;
import org.cerrid.webAutomation.TestClass;

public class AutomationController {
	static Logger logger = Logger.getLogger(AutomationController.class);

	public Map<String, String> automate(String username, String password, String filePath, int entryCount) {
		logger.info(":: Entered in controller ");
		Map<String, String> responseMap = new HashMap<>();
		try {
			List<DataFields> dataList = new CopyValues().copyDataFromExcel(filePath, entryCount);
			TestClass testClass = new TestClass(username, password, dataList);
			testClass.login();
			testClass.changePage();
			testClass.calculateValues();
			testClass.logout();
			SetCalculatedValues setCalculatedValues = new SetCalculatedValues();
			setCalculatedValues.readDataFile(dataList, filePath);
			responseMap.put("status", "success");
			return responseMap;
		} catch (Exception e) {
			e.printStackTrace();
			responseMap.put("status", "fail");
			responseMap.put("reason", e.toString());
			return responseMap;
		}
	}

	public static void main(String[] args) {
		AutomationController automationController = new AutomationController();
		automationController.automate("bh-tamukherjee", "Tower377@$*",
				"D:\\Anshu\\Downloads\\ms\\CDS_RiskCalcs_UD.xlsm", 4);
	}
}
