package br.com.liraquality.core;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PaginaFactory {

	private final static Map<Long, CommonWebDriver> instanceMap = new HashMap<Long, CommonWebDriver>();

	public static CommonWebDriver instance() {
		CommonWebDriver cwd = instanceMap.get(Thread.currentThread().getId());
		if (cwd == null) {
//			WebDriver driver = new FirefoxDriver();
			WebDriver driver = new ChromeDriver();
			cwd = new CommonWebDriver(driver, new WebDriverWait(driver, 10));
			instanceMap.put(Thread.currentThread().getId(), cwd);
		}
		return cwd;
	}

}
