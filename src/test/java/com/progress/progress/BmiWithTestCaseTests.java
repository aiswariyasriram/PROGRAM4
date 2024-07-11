package com.progress.progress;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

@SpringBootTest
class BmiWithTestCaseTests {


	WebDriver driver;

	@BeforeClass
	public void setUp() {
		// Set the path to the chromedriver executable
		System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

		// Initialize WebDriver
		driver = new ChromeDriver();

		// Open the BMI Calculator web page
		driver.get("URL_of_BMI_Calculator");
	}

	@Test
	public void verifyPageTitle() {
		String expectedTitle = "BMI Calculator";
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle, "Page title is not as expected.");
	}

	@Test
	public void verifyBMICalculation() {
		// Locate height, weight input fields and calculate button
		WebElement heightInput = driver.findElement(By.id("heightInputId"));
		WebElement weightInput = driver.findElement(By.id("weightInputId"));
		WebElement calculateButton = driver.findElement(By.id("calculateButtonId"));

		// Input test data
		heightInput.sendKeys("180");
		weightInput.sendKeys("75");

		// Click calculate button
		calculateButton.click();

		// Locate the result field and get the calculated BMI value
		WebElement resultField = driver.findElement(By.id("resultFieldId"));
		String actualResult = resultField.getText();

		// Verify the result
		String expectedResult = "23.1"; // Expected result should be pre-calculated based on inputs
		Assert.assertEquals(actualResult, expectedResult, "BMI calculation is incorrect.");
	}

	@Test
	public void verifyErrorMessageForInvalidInput() {
		// Locate height, weight input fields and calculate button
		WebElement heightInput = driver.findElement(By.id("heightInputId"));
		WebElement weightInput = driver.findElement(By.id("weightInputId"));
		WebElement calculateButton = driver.findElement(By.id("calculateButtonId"));

		// Input invalid data
		heightInput.sendKeys("abc");
		weightInput.sendKeys("xyz");

		// Click calculate button
		calculateButton.click();

		// Locate the error message field and get the error message
		WebElement errorMessageField = driver.findElement(By.id("errorMessageFieldId"));
		String actualErrorMessage = errorMessageField.getText();

		// Verify the error message
		String expectedErrorMessage = "Please enter valid numbers for height and weight.";
		Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message is not as expected.");
	}

	@Test
	public void verifyEmptyInputFields() {
		// Locate calculate button
		WebElement calculateButton = driver.findElement(By.id("calculateButtonId"));

		// Click calculate button without entering any data
		calculateButton.click();

		// Locate the error message field and get the error message
		WebElement errorMessageField = driver.findElement(By.id("errorMessageFieldId"));
		String actualErrorMessage = errorMessageField.getText();

		// Verify the error message
		String expectedErrorMessage = "Height and Weight fields cannot be empty.";
		Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message for empty input fields is not as expected.");
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
