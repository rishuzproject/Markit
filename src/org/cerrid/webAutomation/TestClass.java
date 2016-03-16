package org.cerrid.webAutomation;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestClass {

	static Logger logger = Logger.getLogger(TestClass.class);
	private HtmlUnitDriver driver;
	private String username;
	private String password;
	private List<DataFields> dataFieldsList;

	public TestClass(String username, String password, List<DataFields> dataFieldsList) {
		driver = new HtmlUnitDriver(true);
		this.dataFieldsList = dataFieldsList;
		this.username = username;
		this.password = password;
	}

	public List<DataFields> getDataFieldsList() {
		return dataFieldsList;
	}

	public void setDataFieldsList(List<DataFields> dataFieldsList) {
		this.dataFieldsList = dataFieldsList;
	}

	public HtmlUnitDriver getDriver() {
		return driver;
	}

	public void setDriver(HtmlUnitDriver driver) {
		this.driver = driver;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void login() {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().setPosition(new Point(-2000, 0));
		WebElement userNameElement = null;
		int trying = 0;
		while (userNameElement == null && trying < 10) {
			try {
				driver.get(WebSiteConstants.url);
				userNameElement = driver.findElement(By.id(WebSiteConstants.USER_NAME_ELEMENT_ID));
				userNameElement.sendKeys(username);
				WebElement passwordElement = driver.findElement(By.id(WebSiteConstants.PASSWORD_ELEMENT_ID));
				passwordElement.sendKeys(password);
				WebElement submitElement = driver.findElement(By.id(WebSiteConstants.LOGIN_BUTTON));
				// passwordElement.submit();
				submitElement.click();
			} catch (Exception e) {
				logger.log(Level.INFO, "Could not Login Trying Again " + trying);
				trying++;
			}
		}
	}

	public void changePage() {
		 WebDriverWait wait = new WebDriverWait(driver, 50);
         driver.get(WebSiteConstants.REDIRECT_URL);
         wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(WebSiteConstants.FIRST_IFRAME_ID)));
         wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(WebSiteConstants.SECOND_IFRAME_ID)));
         wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.className(WebSiteConstants.SECOND_IFRAME_ID)));
         wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(WebSiteConstants.WAIT_IMAGE_XPATH)));
         driver.switchTo().defaultContent();
         wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(WebSiteConstants.FIRST_IFRAME_ID)));
         List<WebElement> navItems = null;
         while (navItems == null || navItems.size() < 2) {
                 wait.until(ExpectedConditions.elementToBeClickable(By.className(WebSiteConstants.TAB_CLASS_NAME)));
                 navItems = driver.findElements(By.className(WebSiteConstants.TAB_CLASS_NAME));
         }
         wait.until(ExpectedConditions.elementToBeClickable(navItems.get(2)));
         navItems.get(2).click();

         wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.className(WebSiteConstants.SECOND_IFRAME_ID)));
	}

	public void calculateValues() {
		WebDriverWait wait = new WebDriverWait(driver, 50);
		String inputElementId, clearElementId;
		for (DataFields dataField : dataFieldsList) {
			if (dataField.getIndicesType().equalsIgnoreCase(WebSiteConstants.INDICES_TYPE_CMBX)) {
				if(dataField.getInputType().equalsIgnoreCase(WebSiteConstants.INPUT_TYPE_PRICE)) {
					inputElementId = WebSiteConstants.PRICE_ID;
					clearElementId = WebSiteConstants.SPREAD_ID;
				}
				else {
					inputElementId = WebSiteConstants.SPREAD_ID;
					clearElementId = WebSiteConstants.PRICE_ID;
				}
				WebElement cmbxTab = wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath(WebSiteConstants.INDICES_TYPE_CMBX_TAB_XPATH)));
				cmbxTab.click();
				Select dropdown = new Select(driver.findElement(By.xpath(WebSiteConstants.INDICES_TYPE_CMBX_XPATH)));
				dropdown.selectByValue(dataField.getIndices());
				wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.className(WebSiteConstants.PORTFOLIO_HEADER_CLASS_NAME)));
				
				WebElement clearElement = driver.findElement(By.id(clearElementId));
				clearElement.clear();
				WebElement inputElement = driver.findElement(By.id(inputElementId));
				inputElement.clear();
				inputElement.sendKeys(Double.toString(dataField.getInputValue()));
				
				WebElement calculateBtn = driver.findElement(By.id(WebSiteConstants.CALCULATE_BUTTON_ID));
				calculateBtn.click();
				while(!calculateBtn.isEnabled()){
					
				}
				WebElement spreadValueElement = driver.findElement(By.xpath(WebSiteConstants.SPREAD_VALUE_XPATH));
				WebElement walValueElement = driver.findElement(By.xpath(WebSiteConstants.WAL_VALUE_XPATH));
				WebElement pv01ValueElement = driver.findElement(By.xpath(WebSiteConstants.PV01_VALUE_XPATH));
				while (spreadValueElement.getText().trim().equalsIgnoreCase("")
						|| walValueElement.getText().trim().equalsIgnoreCase("")
						|| pv01ValueElement.getText().trim().equalsIgnoreCase("")) {
					
				}
				try {
					dataField.setCalculatedValue(Double.parseDouble(clearElement.getAttribute("value")));
				} catch (Exception e) {
					logger.info(" :: Interrupted -" + e);
					e.printStackTrace();
				}
			}
		}
	}

	public void logout() {
		WebDriverWait wait = new WebDriverWait(driver, 50);
        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(WebSiteConstants.FIRST_IFRAME_ID)));
        WebElement markItElement = wait
                        .until(ExpectedConditions.elementToBeClickable(By.className(WebSiteConstants.LOGOUT_DROPDOWN)));
        markItElement.click();
        WebElement logoutElement = wait
                        .until(ExpectedConditions.elementToBeClickable(By.className(WebSiteConstants.LOGOUT_BUTTON)));
        logoutElement.click();
        driver.quit();
	}

}
