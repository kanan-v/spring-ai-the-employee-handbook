package com.kannan.the_employee_handbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class TheEmployeeHandbookApplication {



	public static void main(String[] args) {

        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));

        System.out.println("JVM TZ = " + TimeZone.getDefault());

        SpringApplication.run(TheEmployeeHandbookApplication.class, args);
	}

}
