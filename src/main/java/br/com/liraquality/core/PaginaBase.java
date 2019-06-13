package br.com.liraquality.core;

import java.util.AbstractMap.SimpleEntry;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class PaginaBase {

	static {
		if (System.getProperty("webdriver.gecko.driver") == null) {
			if (StringUtils.isNoneEmpty(System.getenv("GECKO_HOME")))
				System.setProperty("webdriver.gecko.driver", System.getenv("GECKO_HOME") + SystemUtils.FILE_SEPARATOR
						+ "geckodriver" + (SystemUtils.IS_OS_WINDOWS ? ".exe" : ""));
			else
				System.setProperty("webdriver.gecko.driver", PaginaBase.class.getClassLoader().getResource("").getPath()
						+ "driver/geckodriver" + (SystemUtils.IS_OS_WINDOWS ? ".exe" : ""));
		}
		
		if (System.getProperty("webdriver.chrome.driver") == null) {
				System.setProperty("webdriver.chrome.driver", PaginaBase.class.getClassLoader().getResource("").getPath()
						+ "driver/chromedriver" + (SystemUtils.IS_OS_WINDOWS ? ".exe" : ""));
		}
		
	}
  
	private WebDriver driver;
	private WebDriverWait waitDriver;
	public PaginaBase(PaginaBase baseCaller) {
		this.driver = baseCaller.getDriver();
		this.waitDriver = baseCaller.getWaitDriver();
	}

	public PaginaBase(WebDriver driver) {
		init(driver);
	}

	public PaginaBase() {
		init(null);
	}

	private void init(WebDriver driver) {
		if (driver == null) {
			CommonWebDriver cwd = PaginaFactory.instance();
			this.driver = cwd.getDriver();
			this.waitDriver = cwd.getWaitDriver();
		} else {
			this.driver = driver;
			this.waitDriver = new WebDriverWait(this.driver, 10);
		}
	}

	 

	 

	public void navegateTo(String url) {
		driver.navigate().to(url);
	}

	public WebDriver getDriver() {
		
		return driver;
	}

	public WebDriverWait getWaitDriver() {
		return waitDriver;
	}

	public void closeNavigator() {
		getDriver().quit();
	}

	public WebElement findElement(By by) {
		return getDriver().findElement(by);
	}

	public List<WebElement> findListElement(By by) {
		return getDriver().findElements(by);
	}

	public void waitPageLoad1() {
		waitPageLoad(10);
	}

	public void waitPageLoad(ExpectedCondition<?> condition) {
		waitPageLoad1();
		getWaitDriver().until(condition);
	}

	public void waitPageLoad(long seconds) {
		getDriver().manage().timeouts().pageLoadTimeout(seconds, TimeUnit.SECONDS);
	}

	public void waitPageLoad(PaginaBase pagina) {
		if (pagina.getExpectedCondition() != null)
			waitPageLoad(pagina.getExpectedCondition());
		else
			waitPageLoad1();
	}

	 
 
	 

	private String generateXPATH(WebElement childElement, String current) {
		String childTag = childElement.getTagName();
		if (childTag.equals("html")) {
			return "/html[1]" + current;
		}
		WebElement parentElement = childElement.findElement(By.xpath(".."));
		List<WebElement> childrenElements = parentElement.findElements(By.xpath("*"));
		int count = 0;
		for (int i = 0; i < childrenElements.size(); i++) {
			WebElement childrenElement = childrenElements.get(i);
			String childrenElementTag = childrenElement.getTagName();
			if (childTag.equals(childrenElementTag)) {
				count++;
			}
			if (childElement.equals(childrenElement)) {
				return generateXPATH(parentElement, "/" + childTag + "[" + count + "]" + current);
			}
		}
		return null;
	}

	public WebElement findElementByPosition(final WebElement elementBase, final int parent, final int position) {
		Integer index = 0, parentCount = parent;
		String xPath = generateXPATH(elementBase, "");
		String parentXPath = xPath;
		while (parentCount > 0) {
			parentXPath = parentXPath.substring(0, xPath.lastIndexOf("/"));
			parentCount--;
		}

		if (parentXPath.lastIndexOf("]") == parentXPath.length() - 1)
			index = Integer.valueOf(parentXPath.substring(parentXPath.lastIndexOf("[") + 1, parentXPath.length() - 1));
		index += position;
		if (index < 0)
			throw new RuntimeException("Element require not found");

		parentXPath = parentXPath.substring(0, parentXPath.lastIndexOf("[") + 1) + index + "]";

		return findElement(By.xpath(parentXPath));
	}

	public boolean isChecked() {
		return getExpectedCondition().apply(getDriver());
	}

	public abstract ExpectedCondition<Boolean> getExpectedCondition();

	public String getResourceLocale() {
		return "NA";
	}

 
	public void killDriver(){
		if(driver != null) {
			driver.quit();
			driver = null;
		}
	}
	
	public void goToFrameContent(){
		getDriver().switchTo().frame("frameContent");
	}
	
	public void goToDefaultFrame(){
		getDriver().switchTo().defaultContent();
	}
	public void maximize(){
		getDriver().manage().window().maximize();
	}
	public void popUp(){
		 
		getDriver().switchTo().activeElement() ;
		
	}
	public  void selecOption(By elemento , String option){
		 
		WebElement element = getDriver().findElement(elemento);
		Select combo = new Select(element);
		combo.selectByVisibleText(option);
		
	}
 
	 
	 
}
