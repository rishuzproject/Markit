package org.cerrid.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Level;
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
		TestClass testClass = null;
		try {
			//AutomationController.extractDrivers(filePath);
			List<DataFields> dataList = new CopyValues().copyDataFromExcel(filePath, entryCount);
			testClass = new TestClass(username, password, dataList);
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
            logger.info(" --------------------------EXCEPTION - START----------------------- ");
            logger.log(Level.FATAL, e.getMessage(), e);
            logger.info(" --------------------------EXCEPTION - END----------------------- ");
            responseMap.put("status", "fail");
            responseMap.put("reason", e.toString());
            return responseMap;
		} finally {
            if (null != testClass)
            	logger.info("Log out ..");
             	testClass.logout();
		}
	}

	public static void main(String[] args) {
		AutomationController automationController = new AutomationController();
		automationController.automate("bh-tamukherjee", "Tower377@$*",
				"D:\\MarkIt(Op)Test\\CDS_RiskCalcs_UD_ORG(1).xlsm", 4);
	}

	public static void extractDrivers(String filePath) {
		File file = new File(filePath);
		String directoryPath = file.getParent();
		String part1 = "pushd " + directoryPath;
		String part2 = "jar xf MarkIt-Hub.jar chromedriver.exe";
		String part3 = "jar xf MarkIt-Hub.jar IEDriverServer.exe";

		try {
			Process p = Runtime.getRuntime()
					.exec("cmd /c start cmd.exe /c \"" + part1 + " && " + part2 + "&&" + part3 + " && echo end\"");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
