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


public class CreateLocationTestng {
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

  @Test (priority=0)
  
  public void testCreateLocation2() throws Exception {
    driver.get(baseUrl + "/#/login");
    driver.findElement(By.xpath("//button")).click();
    driver.findElement(By.linkText("Locations")).click();
    //wait explicit   
    (new WebDriverWait(driver, 60))
      .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[4]/div/button")));
   
    driver.findElement(By.xpath("//div[4]/div/button")).click();
    
  //wait for the form Add Location Info
    (new WebDriverWait(driver, 60))
    .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.modal-header.ng-scope")));

    driver.findElement(By.id("location-add-name")).clear();
    driver.findElement(By.id("location-add-name")).sendKeys("NewLocation");        

    driver.findElement(By.id("location-add-display-name")).clear();
    driver.findElement(By.id("location-add-display-name")).sendKeys("NewLocation");
        
    driver.findElement(By.cssSelector("button.btn.btn-primary")).click();

    driver.navigate().refresh();        
        
    (new WebDriverWait(driver, 30))
    .until(ExpectedConditions.presenceOfElementLocated(By.linkText("Locations")));
    
    driver.findElement(By.linkText("Locations")).click();
       
    (new WebDriverWait(driver, 60))
    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[4]/div/button")));
    

    int lastPosition = (((driver.findElements(By.xpath("//div[@id='locationGrid']/div[2]/div/*")))).size());
    driver.findElement(By.xpath("//div[@id='locationGrid']/div[2]/div/div["+lastPosition+"]/div[2]/div[2]/div")).click();
           
    assertEquals("NewLocation", driver.findElement(By.xpath("//div[@id='locationGrid']/div[2]/div/div["+lastPosition+"]/div[2]/div[2]/div")).getText());
    
    driver.findElement(By.xpath("//button[2]")).click();

    driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
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
