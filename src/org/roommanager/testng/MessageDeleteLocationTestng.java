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

public class MessageDeleteLocationTestng {
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

  @Test (priority=3)
  public void testMessageDeleteLocation() throws Exception {
    driver.get(baseUrl + "/#/login");
    driver.findElement(By.xpath("//button")).click();
    driver.findElement(By.linkText("Locations")).click();
 
    WebElement addButton = (new WebDriverWait(driver, 60))
	.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[4]/div/button")));   
    addButton.click();
    
  //wait for the form Add Location Info
    (new WebDriverWait(driver, 60))
    .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.modal-header.ng-scope")));

    String locationName = "New Location";
    driver.findElement(By.id("location-add-name")).clear();
    driver.findElement(By.id("location-add-name")).sendKeys(locationName);        
    driver.findElement(By.id("location-add-display-name")).clear();
    driver.findElement(By.id("location-add-display-name")).sendKeys(locationName);
        
    driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
    driver.navigate().refresh();        
       
    WebElement locationTab = (new WebDriverWait(driver, 60))
    .until(ExpectedConditions.presenceOfElementLocated(By.linkText("Locations")));
    locationTab.click();
    
    
    (new WebDriverWait(driver, 60))
    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[4]/div/button")));
    
    //search for the id created
    int lastPosition = (((driver.findElements(By.xpath("//div[@id='locationGrid']/div[2]/div/*")))).size());
    
    driver.findElement(By.xpath("//div[@id='locationGrid']/div[2]/div/div["+lastPosition+"]/div[2]/div[2]/div")).click();              
    
    (new WebDriverWait(driver, 20))
    .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[2]")));
    
    driver.findElement(By.xpath("//button[2]")).click();
    
    (new WebDriverWait(driver, 20))
    .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button.btn.btn-primary")));
    
    driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        
    assertEquals("Location " + locationName + " sucessfully removed", driver.findElement(By.cssSelector("div.ng-binding.ng-scope")).getText());
                 
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

