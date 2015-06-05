package org.roommanager.testng;


import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import org.testng.annotations.Test;

public class CreateResourceTestng {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeTest
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://172.20.208.174:4042/admin";
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test (priority=1)
  public void testCreateResource() throws Exception {
    driver.get(baseUrl + "/#/login");
    driver.findElement(By.xpath("//button")).click();
    driver.findElement(By.linkText("Resources")).click();
    
    WebElement addButton = (new WebDriverWait(driver, 60))
	.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/div/button")));   
    addButton.click();


    driver.findElement(By.xpath("(//input[@type='text'])[3]")).clear();
    driver.findElement(By.xpath("(//input[@type='text'])[3]")).sendKeys("NuevoRecurso");

    driver.findElement(By.xpath("(//input[@type='text'])[4]")).clear();
    driver.findElement(By.xpath("(//input[@type='text'])[4]")).sendKeys("NuevoRecurso");

    driver.findElement(By.cssSelector("button.info")).click();

    driver.findElement(By.xpath("//input[@type='text']")).clear();
    driver.findElement(By.xpath("//input[@type='text']")).sendKeys("NuevoRecurso");
    
    
    driver.findElement(By.cssSelector("input.ngSelectionCheckbox")).click();
        
    WebElement assertionSearch = (new WebDriverWait(driver, 60))
	.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='resourcesGrid']/div[2]/div/div/div[2]/div[2]/div")));   
    assertionSearch.click();    
    
    
    assertEquals("", driver.findElement(By.xpath("//div[@id='resourcesGrid']/div[2]/div/div/div[2]/div[2]/div")).getText());
           
    	   
    driver.findElement(By.id("btnRemove")).click();
      
    WebElement deleteButton = (new WebDriverWait(driver, 60))
    		.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button.info")));   
    deleteButton.click();           	    
   
  }

  @AfterTest
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
