package com.automation.common;

import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.io.filefilter.FileFileFilter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.automation.api.PageFactory;
import com.automation.page.mobile.MobilePageFactory;
import com.automation.page.web.WebPageFactory;

public class WebDriverTest {

	public static Properties props;
	public static WebDriver driver = null;

	public static List<String> resourcesFiles = new ArrayList<String>();
	public static List<String> resourcesDirectories = new ArrayList<String>();

	public static FileReader fileReader = null;
	public static FileWriter fileWriter = null;

	public static WebDriverWait driverWait;

	public static String locator;

	private PageFactory pageFactory;

	public WebDriverTest() {
		props = readResources();
		if (driver == null) {
			if (props.getProperty("platform.name").contains("web")) {
				if (props.getProperty("browser").contains("firefoxDriver")) {
					driver = new FirefoxDriver();
				} else if (props.getProperty("browser").contains("chromeDriver")) {
					System.setProperty("webdriver.chrome.driver",
							System.getProperty("user.dir") + "/server/chromedriver.exe");
					DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
					driver = new ChromeDriver(desiredCapabilities);
				}
				driver.manage().window().maximize();
			} else {

			}
			driverWait = new WebDriverWait(driver, 50);
		}
	}

	protected PageFactory getPageFactory() {
		if (null == pageFactory) {
			pageFactory = "web".equalsIgnoreCase(props.getProperty("platform.name")) ? new WebPageFactory()
					: new MobilePageFactory();
		}
		return pageFactory;
	}

	public static void waitForPresent(WebElement element) {
		driverWait.until(ExpectedConditions.presenceOfElementLocated(findLocator(element)));
	}

	public static void waitForVisible(WebElement element) {
		driverWait.until(ExpectedConditions.visibilityOfElementLocated(findLocator(element)));
	}

	public static Properties readResources() {
		try {
			resourcesFiles.clear();
			getResources("resources");
			fileWriter = new FileWriter("result.properties");
			for (String file : resourcesFiles) {
				fileReader = new FileReader(file);
				int charecter;
				while ((charecter = fileReader.read()) != -1) {
					fileWriter.write(charecter);
				}
				fileWriter.append("\n");
			}

			resourcesFiles.clear();
			getResources("config");
			for (String file : resourcesFiles) {
				File xmlFile = new File(file);
				DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
				Document document = documentBuilder.parse(xmlFile);
				document.getDocumentElement().normalize();
				NodeList nodeList = document.getElementsByTagName("parameter");

				for (int temp = 0; temp < nodeList.getLength(); temp++) {
					Node node = nodeList.item(temp);
					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) node;
						fileWriter.write(eElement.getAttribute("name") + "=" + eElement.getAttribute("value"));
						fileWriter.append("\n");
					}
				}
			}
			props = new Properties();
			fileWriter.flush();

			File file = new File("result.properties");
			fileReader = new FileReader(file);
			props.load(fileReader);
			for (String s : props.stringPropertyNames()) {
				System.setProperty(s, props.getProperty(s));
			}
		} catch (Exception e) {
		} finally {
			try {
				if (fileReader != null) {
					fileReader.close();
				}
				if (fileWriter != null) {
					fileWriter.close();
				}
			} catch (IOException e) {
			}
		}
		Path path = Paths.get("result.properties");
		try {
			Files.deleteIfExists(path);
		} catch (IOException e) {
		}
		return props;
	}

	public static void getResources(String fileOrDirectoryName) {

		File fileOrDirectory = new File(fileOrDirectoryName);
		File[] fileList = fileOrDirectory.listFiles();

		for (File file : fileList) {
			if (file.isDirectory()) {
				resourcesDirectories.add(file.getAbsolutePath());
			}
		}
		fileList = fileOrDirectory.listFiles((FileFilter) FileFileFilter.FILE);
		for (File file : fileList) {
			if (file.isFile()) {
				resourcesFiles.add(file.getAbsolutePath());
			}
		}
		for (int i = 0; i < resourcesDirectories.size(); i++) {
			String path = resourcesDirectories.get(i);
			resourcesDirectories.remove(i);
			getResources(path);
		}
	}

	public static By findLocator(WebElement element) {

		if (element.toString().contains("class name:")) {
			getSelector(element, "class name:");
			return By.className(locator);
		} else if (element.toString().contains("id:")) {
			getSelector(element, "id:");
			return By.id(locator);
		} else if (element.toString().contains("link text:")) {
			getSelector(element, "link text:");
			return By.linkText(locator);
		} else if (element.toString().contains("partial link text:")) {
			getSelector(element, "partial link text:");
			return By.partialLinkText(locator);
		} else if (element.toString().contains("tag name:")) {
			getSelector(element, "tag name:");
			return By.tagName(locator);
		} else if (element.toString().contains("name:")) {
			getSelector(element, "name:");
			return By.name(locator);
		} else if (element.toString().contains("xpath:")) {
			getSelector(element, "xpath:");
			return By.xpath(locator);
		} else if (element.toString().contains("css selector:")) {
			getSelector(element, "css selector:");
			return By.cssSelector(locator);
		}
		return null;
	}

	public static void getSelector(WebElement element, String selector) {
		locator = element.toString().split(selector)[1].trim();
		int length = locator.length();
		locator = locator.substring(0, length - 1);
	}

	@AfterSuite(alwaysRun = true)
	public void shutDown() {
		driver.close();
		driver.quit();
	}
}
