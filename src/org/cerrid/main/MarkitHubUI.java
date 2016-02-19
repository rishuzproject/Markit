/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cerrid.main;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

import org.apache.log4j.Logger;
import org.cerrid.Intialize.EncrypterDecrypter;
import org.cerrid.Intialize.PropertiesUtility;
import org.cerrid.constants.Constants;
import org.cerrid.controller.AutomationController;

/**
 *
 * @author Rishabh Prashar
 */
public class MarkitHubUI extends javax.swing.JFrame {

	private static final long serialVersionUID = 1288504235258677899L;
	static Logger logger = Logger.getLogger(MarkitHubUI.class);
	public static final int MODE_OPEN = 1;
	public static final int MODE_SAVE = 2;
	EncrypterDecrypter ecrypt = new EncrypterDecrypter();
	private static StringBuilder filePath;
	private static int rowCount;

	/**
	 * Creates new form MarkitHubUI
	 */
	public MarkitHubUI() {
		initComponents();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Markit");
		initApp();
	}

	private void initApp() {
		logger.info("Initializing App");
		Map<String, String> fieldsMap;
		try {
			fieldsMap = new PropertiesUtility().readProperties();
			if (null != fieldsMap && !fieldsMap.isEmpty()) {
				jTextField_user_name.setText(fieldsMap.get(Constants.USER_NAME).toString());
				try {
					jPasswordField.setText(ecrypt.decryptData(fieldsMap.get(Constants.PASSWORD).toString()));
				} catch (Exception ex) {
					logger.debug(ex.getMessage());
				}
			}

			jLabel_error.setVisible(false);
		} catch (IOException ex) {
			logger.debug(ex.getMessage());
		}
		if (null == filePath) {
			jLabel_error.setVisible(true);
			ok_button.setEnabled(false);
			logger.error(" :: ERROR - Could not find File Path required to run program , "
					+ "check whether file path argument is present when running jar,If running jar from other program please make your you have added file path argument  ");
		}
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		jLabel_Title = new javax.swing.JLabel();
		jLabel_user_name = new javax.swing.JLabel();
		jLabel_password = new javax.swing.JLabel();
		jTextField_user_name = new javax.swing.JTextField(20);
		jCheckBox_rememberPswd = new javax.swing.JCheckBox();
		jComboBox1 = new javax.swing.JComboBox();
		ok_button = new javax.swing.JButton();
		jPasswordField = new javax.swing.JPasswordField(20);
		jLabel_error = new javax.swing.JLabel();
		jLabel_user_star = new javax.swing.JLabel();
		jLabel_password_star = new javax.swing.JLabel();
		jSeparator2 = new javax.swing.JSeparator();
		jLabel5 = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jLabel_Title.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
		jLabel_Title.setForeground(new java.awt.Color(0, 153, 204));
		jLabel_Title.setText("Markit");

		jLabel_user_name.setText("User Name");

		jLabel_password.setText("Password");

		jTextField_user_name.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTextField_user_nameActionPerformed(evt);
			}
		});

		jCheckBox_rememberPswd.setText("Remember");
		jCheckBox_rememberPswd.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jCheckBox_rememberPswdActionPerformed(evt);
			}
		});

		jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "CMBX" }));

		ok_button.setText("Submit");
		ok_button.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				ok_buttonActionPerformed(evt);
			}
		});

		jLabel_error.setForeground(new java.awt.Color(255, 0, 0));
		jLabel_error.setText("File Path not found, Please see logs for details.");
		jLabel_error.setMaximumSize(new java.awt.Dimension(20, 14));
		jLabel_error.setMinimumSize(new java.awt.Dimension(20, 14));

		jLabel_user_star.setForeground(new java.awt.Color(255, 0, 0));
		jLabel_user_star.setText("*");

		jLabel_password_star.setForeground(new java.awt.Color(255, 0, 51));
		jLabel_password_star.setText("*");

		jLabel5.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
		jLabel5.setText("Message : ");
		jLabel5.setFocusable(false);

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup()
						.addGroup(jPanel1Layout.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(jSeparator2))
						.addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(jLabel5)
										.addGap(19, 19, 19)
										.addComponent(jLabel_error, javax.swing.GroupLayout.PREFERRED_SIZE, 283,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(0, 64, Short.MAX_VALUE))
								.addGroup(jPanel1Layout.createSequentialGroup().addGap(36, 36, 36)
										.addGroup(jPanel1Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(jPanel1Layout.createSequentialGroup()
														.addComponent(jLabel_user_name)
														.addPreferredGap(
																javax.swing.LayoutStyle.ComponentPlacement.RELATED)
														.addComponent(jLabel_user_star))
												.addGroup(jPanel1Layout.createSequentialGroup()
														.addComponent(jLabel_password)
														.addPreferredGap(
																javax.swing.LayoutStyle.ComponentPlacement.RELATED)
														.addComponent(jLabel_password_star)))
										.addGap(113, 113, 113)
										.addGroup(jPanel1Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(jPanel1Layout.createSequentialGroup()
														.addGap(0, 0, Short.MAX_VALUE)
														.addComponent(jCheckBox_rememberPswd))
												.addComponent(jTextField_user_name).addComponent(jPasswordField)
												.addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(ok_button, javax.swing.GroupLayout.PREFERRED_SIZE,
																72, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jComboBox1,
																javax.swing.GroupLayout.PREFERRED_SIZE, 132,
																javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGap(0, 0, Short.MAX_VALUE)))))
								.addGap(10, 10, 10)))
						.addContainerGap())
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						jPanel1Layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE)
								.addComponent(jLabel_Title, javax.swing.GroupLayout.PREFERRED_SIZE, 84,
										javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(jLabel_Title)
						.addGap(27, 27, 27)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel_user_name)
								.addComponent(jTextField_user_name, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel_user_star))
						.addGap(18, 18, 18)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel_password)
								.addComponent(jPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel_password_star))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jCheckBox_rememberPswd).addGap(3, 3, 3)
						.addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18).addComponent(ok_button)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
						.addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
								.addComponent(jLabel5).addComponent(jLabel_error,
										javax.swing.GroupLayout.PREFERRED_SIZE, 14,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(9, 9, 9)));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jPanel1,
						javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jPanel1,
						javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addContainerGap()));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void jTextField_user_nameActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField_user_nameActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jTextField_user_nameActionPerformed

	private void jCheckBox_rememberPswdActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jCheckBox_rememberPswdActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jCheckBox_rememberPswdActionPerformed

	private boolean validateFields() {
		return !jTextField_user_name.getText().trim().isEmpty() && jPasswordField.getPassword().length >= 1;
	}

	private void ok_buttonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_ok_buttonActionPerformed
		ok_button.setEnabled(false);
		if (validateFields()) {
			jLabel_error.setVisible(false);
			jTextField_user_name.setEditable(false);
			jPasswordField.setEditable(false);

			String userName = jTextField_user_name.getText().trim();
			String password = new String(jPasswordField.getPassword());
			// String indicexType =
			// String.valueOf(jComboBox1.getSelectedItem());

			if (jCheckBox_rememberPswd.isSelected()) {
				Map<String, String> fieldsMap = new HashMap<>();
				fieldsMap.put(Constants.USER_NAME, jTextField_user_name.getText().trim());
				try {
					fieldsMap.put(Constants.PASSWORD, ecrypt.encryptData(password));
				} catch (Exception ex) {
					logger.debug(ex.getMessage());
				}
				new PropertiesUtility().saveProperties(fieldsMap);
			}
			// Call Automation method
			AutomationController automate = new AutomationController();
			Map<String, String> responseMap = automate.controller(userName, password, filePath.toString(), rowCount);
			if (responseMap.get("status").equalsIgnoreCase("success")) {
				jLabel_error.setVisible(true);
				jLabel_error.setText("Success");
				CloseAppFrame newFrame = new CloseAppFrame(filePath.toString());
				newFrame.setVisible(true);
				newFrame.openDialog();
				this.dispose();
			} else {
				jLabel_error.setVisible(true);
				jLabel_error.setText("Error:" + responseMap.get("reason").trim());
			}
			jTextField_user_name.setEditable(true);
			jPasswordField.setEditable(true);
		} else {
			jLabel_error.setVisible(true);
			jLabel_error.setText(" Please enter all fields marked with *");
		}
	}// GEN-LAST:event_ok_buttonActionPerformed

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed" desc=" Look and feel setting
		// code (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the
		 * default look and feel. For details see
		 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.
		 * html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(MarkitHubUI.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		}
		// </editor-fold>

		if (args.length > 0) {
			filePath = new StringBuilder();
			if (args.length > 2) {
				for (int i = 0; i < args.length - 1; i++) {
					filePath.append(args[i]);
					filePath.append(" ");
				}
				filePath.deleteCharAt(filePath.length() - 1);
			} else {
				filePath.append(args[0]);
			}
			String strVal = args[args.length - 1];
			String no = strVal.substring(strVal.lastIndexOf("t")+1, strVal.length());
			rowCount = Integer.parseInt(no);
			logger.info(" :: ARGS -- > " + args.length);
			logger.info("FILE PATH : '" + filePath + "'" +" ROW COUNT-->"+rowCount);
		}

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MarkitHubUI().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JCheckBox jCheckBox_rememberPswd;
	private javax.swing.JComboBox jComboBox1;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel_Title;
	private javax.swing.JLabel jLabel_error;
	private javax.swing.JLabel jLabel_password;
	private javax.swing.JLabel jLabel_password_star;
	private javax.swing.JLabel jLabel_user_name;
	private javax.swing.JLabel jLabel_user_star;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPasswordField jPasswordField;
	private javax.swing.JSeparator jSeparator2;
	private javax.swing.JTextField jTextField_user_name;
	private javax.swing.JButton ok_button;
	// End of variables declaration//GEN-END:variables
}
