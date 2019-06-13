package br.com.liraquality.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonWebDriver {
	private WebDriver driver;
	private WebDriverWait waitDriver;

	public CommonWebDriver(WebDriver driver, WebDriverWait waitDriver) {
		this.driver = driver;
		this.waitDriver = waitDriver;

	}

	public WebDriver getDriver() {
		return driver;
	}

	public WebDriverWait getWaitDriver() {
		return waitDriver;
	}

}
