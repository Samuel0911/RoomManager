package org.roommanager.testng;


import java.util.regex.Pattern;
import org.openqa.selenium.interactions.Actions;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import org.testng.annotations.Test;

public class UpdateRoomTestng {
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

  @Test (priority=2)
  public void testUpdateRooms() throws Exception {
    driver.get(baseUrl + "/#/login");
    driver.findElement(By.xpath("//button")).click();
    driver.findElement(By.linkText("Conference Rooms")).click();

    Actions action = new Actions(driver);
    action.doubleClick(driver.findElement(By.xpath("//div[@id='roomsGrid']/div[2]/div/div[3]/div[3]/div[2]/div/span[2]")));
    action.perform();
   
    driver.findElement(By.xpath("//div[@id='roomsGrid']/div[2]/div/div[3]/div[3]/div[2]/div/span[2]")).click();
    
    driver.findElement(By.xpath("(//input[@type='text'])[5]")).clear();
    driver.findElement(By.xpath("(//input[@type='text'])[5]")).sendKeys("30");

    driver.findElement(By.xpath("(//input[@type='text'])[6]")).clear();
    driver.findElement(By.xpath("(//input[@type='text'])[6]")).sendKeys("50");    
    
    driver.findElement(By.cssSelector("button.info")).click();
    
    driver.findElement(By.xpath("//input[@type='text']")).clear();
    driver.findElement(By.xpath("//input[@type='text']")).sendKeys("SM-Room11");
    
    assertEquals("SM-Room11", driver.findElement(By.xpath("//div[@id='roomsGrid']/div[2]/div/div/div[3]/div[2]/div/span[2]")).getText());
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
