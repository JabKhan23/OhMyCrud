package com.qa.tasklist.selenium;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest
@ActiveProfiles("testing")
public class TestMain {

		private static WebDriver driver;

		
		@BeforeAll
		public static void setup() {
			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
			driver = new ChromeDriver();
		}

		@AfterAll
		public static void tearDown() {
			driver.quit();

			
		}
		




		  @Test
		  public void crud() {
			    driver.get("http://localhost:8080/index.html");
			    driver.manage().window().setSize(new Dimension(984, 564));
			    driver.findElement(By.linkText("Diary Manager")).click();

		  }
		  @Test
		  public void createTaskList() {
			    driver.get("http://localhost:8080/");
			    driver.manage().window().setSize(new Dimension(988, 568));
			    driver.findElement(By.linkText("Diary Manager")).click();
			    driver.findElement(By.id("listTitle")).click();
			    driver.findElement(By.id("listTitle")).sendKeys("Clean room");
			    driver.findElement(By.id("createButton")).click();
			    driver.findElement(By.id("taskTitle")).click();
			    driver.findElement(By.id("taskTitle")).sendKeys("Hoover Room");
			    driver.findElement(By.id("taskDueDate")).click();
			    driver.findElement(By.id("taskDueDate")).sendKeys("2022-08-12");
			    driver.findElement(By.id("IdOfList")).click();
			    driver.findElement(By.id("IdOfList")).sendKeys("1");
			    driver.findElement(By.id("addButton")).click();
			    {
			      WebElement element = driver.findElement(By.id("addButton"));
			      Actions builder = new Actions(driver);
			      builder.moveToElement(element).perform();
			    }
			    {
			      WebElement element = driver.findElement(By.tagName("body"));
			      Actions builder = new Actions(driver);
			      builder.moveToElement(element, 0, 0).perform();
			    }
			    driver.findElement(By.id("finishAddButton")).click();
	
			  }
		  @Test
		  public void viewList() {
		    driver.get("http://localhost:8080/");
		    driver.manage().window().setSize(new Dimension(988, 568));
		    driver.findElement(By.linkText("Diary Manager")).click();
		    driver.findElement(By.id("listId")).click();
		    driver.findElement(By.id("listId")).sendKeys("1");
		    driver.findElement(By.id("readListById")).click();

		  }
		  
		  @Test
		  public void updateTask() {
		    driver.get("http://localhost:8080/");
		    driver.manage().window().setSize(new Dimension(990, 570));
		    driver.findElement(By.linkText("Diary Manager")).click();
		    driver.findElement(By.id("taskIDUpdate")).click();
		    driver.findElement(By.id("taskIDUpdate")).sendKeys("1");
		    driver.findElement(By.id("taskTitleUpdate")).click();
		    driver.findElement(By.id("taskTitleUpdate")).sendKeys("Fix the lightbulb");
		    driver.findElement(By.id("taskDueDateUpdate")).click();
		    driver.findElement(By.id("taskDueDateUpdate")).sendKeys("2022-08-13");
		    driver.findElement(By.id("IdOfListUpdate")).click();
		    driver.findElement(By.id("IdOfListUpdate")).sendKeys("1");
		    driver.findElement(By.id("updateButton")).click();
		    driver.findElement(By.id("finishUpdateButton")).click();

		  }
		  
		  @Test
		  public void updateList() {
		    driver.get("http://localhost:8080/");
		    driver.manage().window().setSize(new Dimension(992, 572));
		    driver.findElement(By.linkText("Diary Manager")).click();
		    driver.findElement(By.id("updatelistId")).click();
		    driver.findElement(By.id("updatelistId")).sendKeys("1");
		    driver.findElement(By.id("updatelistTitle")).click();
		    driver.findElement(By.id("updatelistTitle")).sendKeys("New Lights");
		    driver.findElement(By.id("updateListButton")).click();

		  }
		  @Test
		  public void deleteTask() {
			    driver.get("http://localhost:8080/");
			    driver.manage().window().setSize(new Dimension(978, 558));
			    driver.findElement(By.linkText("Diary Manager")).click();
			    driver.findElement(By.id("deletetaskId")).click();
			    driver.findElement(By.id("deletetaskId")).sendKeys("1");
			    driver.findElement(By.id("deleteTaskButton")).click();

			  }
		  
		  @Test
		  public void deleteList() {
			    driver.get("http://localhost:8080/");
			    driver.manage().window().setSize(new Dimension(980, 560));
			    driver.findElement(By.linkText("Diary Manager")).click();
			    driver.findElement(By.id("deletelistId")).click();
			    driver.findElement(By.id("deletelistId")).sendKeys("1");
			    driver.findElement(By.id("deleteListButton")).click();
			    driver.findElement(By.id("listId")).click();
			    driver.findElement(By.id("listId")).sendKeys("1");
			    driver.findElement(By.id("readListById")).click();

			  }
//		  @Test
//		  public void testAll() {
//			    driver.get("http://localhost:8080/");
//			    driver.manage().window().setSize(new Dimension(987, 567));
//			    driver.findElement(By.linkText("Diary Manager")).click();
//			    driver.findElement(By.id("listTitle")).click();
//			    driver.findElement(By.id("listTitle")).sendKeys("Everything");
//			    driver.findElement(By.id("createButton")).click();
//			    driver.findElement(By.id("taskTitle")).click();
//			    driver.findElement(By.id("taskTitle")).sendKeys("Do it all");
//			    driver.findElement(By.id("taskDueDate")).click();
//			    driver.findElement(By.id("taskDueDate")).sendKeys("2022-08-13");
//			    driver.findElement(By.id("IdOfList")).click();
//			    driver.findElement(By.id("IdOfList")).sendKeys("1");
//			    driver.findElement(By.id("addButton")).click();
//			    {
//			      WebElement element = driver.findElement(By.id("addButton"));
//			      Actions builder = new Actions(driver);
//			      builder.moveToElement(element).perform();
//			    }
//			    {
//			      WebElement element = driver.findElement(By.tagName("body"));
//			      Actions builder = new Actions(driver);
//			      builder.moveToElement(element, 0, 0).perform();
//			    }
//			    driver.findElement(By.id("finishAddButton")).click();
//			    driver.findElement(By.id("listId")).click();
//			    driver.findElement(By.id("listId")).sendKeys("1");
//			    driver.findElement(By.id("readListById")).click();
//			    driver.findElement(By.id("readLists")).click();
//			    {
//			      WebElement element = driver.findElement(By.id("clearLists"));
//			      Actions builder = new Actions(driver);
//			      builder.moveToElement(element).perform();
//			    }
//			    {
//			      WebElement element = driver.findElement(By.tagName("body"));
//			      Actions builder = new Actions(driver);
//			      builder.moveToElement(element, 0, 0).perform();
//			    }
//			    driver.findElement(By.id("clearLists")).click();
//			    driver.findElement(By.id("taskIDUpdate")).click();
//			    driver.findElement(By.id("taskIDUpdate")).sendKeys("1");
//			    driver.findElement(By.id("taskTitleUpdate")).click();
//			    driver.findElement(By.id("taskTitleUpdate")).sendKeys("Did it do it?");
//			    driver.findElement(By.id("taskDueDateUpdate")).click();
//			    driver.findElement(By.id("taskDueDateUpdate")).click();
//			    driver.findElement(By.id("taskDueDateUpdate")).sendKeys("2022-08-14");
//			    driver.findElement(By.id("IdOfListUpdate")).click();
//			    driver.findElement(By.id("IdOfListUpdate")).sendKeys("1");
//			    driver.findElement(By.id("updateButton")).click();
//			    driver.findElement(By.id("finishUpdateButton")).click();
//			    driver.findElement(By.id("readListById")).click();
//			    {
//			      WebElement element = driver.findElement(By.id("readListById"));
//			      Actions builder = new Actions(driver);
//			      builder.moveToElement(element).perform();
//			    }
//			    {
//			      WebElement element = driver.findElement(By.tagName("body"));
//			      Actions builder = new Actions(driver);
//			      builder.moveToElement(element, 0, 0).perform();
//			    }
//			    driver.findElement(By.id("updatelistId")).click();
//			    driver.findElement(By.id("updatelistId")).sendKeys("1");
//			    driver.findElement(By.id("updatelistTitle")).click();
//			    driver.findElement(By.id("updatelistTitle")).sendKeys("I did it");
//			    driver.findElement(By.id("updateListButton")).click();
//			    {
//			      WebElement element = driver.findElement(By.id("updateListButton"));
//			      Actions builder = new Actions(driver);
//			      builder.moveToElement(element).perform();
//			    }
//			    {
//			      WebElement element = driver.findElement(By.tagName("body"));
//			      Actions builder = new Actions(driver);
//			      builder.moveToElement(element, 0, 0).perform();
//			    }
//			    driver.findElement(By.id("readLists")).click();
//			    driver.findElement(By.id("deletetaskId")).click();
//			    driver.findElement(By.id("deletetaskId")).sendKeys("1");
//			    driver.findElement(By.id("deleteTaskButton")).click();
//			    driver.findElement(By.id("deletelistId")).click();
//			    driver.findElement(By.id("deletelistId")).sendKeys("1");
//			    driver.findElement(By.id("deleteListButton")).click();
//			    driver.findElement(By.id("readLists")).click();
//			    driver.findElement(By.id("clearLists")).click();
//			    driver.findElement(By.id("readLists")).click();
//			  }
		
}
