package com.progress.progress;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class BmiWithTestCase implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BmiWithTestCase.class, args);
	}

	@Override
	public void run(String... args) {
		try {

			Scanner scanner = new Scanner(System.in);

			System.out.print("Enter weight in kilograms: ");
			double weight = scanner.nextDouble();

			System.out.print("Enter height in meters: ");
			double height = scanner.nextDouble();

			scanner.close();
			BMICalculator bmiCalculator = new BMICalculator(height, weight); // height in meters and weight in kilograms
			double bmi = bmiCalculator.calculateBMI();
			String category = bmiCalculator.getBMICategory();
			System.out.println("BMI: " + bmi);
			System.out.println("Category: " + category);
		} catch (IllegalArgumentException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}
