package org.cerrid.main;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.cerrid.controller.AutomationController;

public class MarkitMain {
	static Logger logger = Logger.getLogger(MarkitMain.class);

	public static void main(String[] args) {
		logger.info(" :: Starting Jar main ");
		int length = args.length;
		int filePathPieces = length - 3;
		int entryCount = 0;
		if (length > 0) {
			StringBuilder filePath = new StringBuilder();
			String password = args[length - 1];
			String userName = args[length - 2];
			String count = args[length - 3];
			try {
				entryCount = Integer.parseInt(count);
			} catch (Exception e) {
				logger.error(e);
			}
			// If file Path has spaces
			if (filePathPieces > 0) {
				for (int i = 0; i < filePathPieces; i++) {
					filePath.append(args[i]).append(" ");
				}
			} else {
				filePath.append(args[0]);
			}

			AutomationController controller = new AutomationController();
			logger.info(" :: Calling Controller with arguments ");
			try {
				controller.automate(userName, password, filePath.toString(), entryCount);
				Desktop dt = Desktop.getDesktop();
				dt.open(new File(filePath.toString()));
			} catch (Exception e) {
				logger.error(e);
			}
			logger.info(" :: File Path ->" + filePath.toString());
		} else {
			logger.info(" :: Error "
					+ "No Argument found in argument List, Please provide required arguments space separated while running jar");
		}
	}
}
