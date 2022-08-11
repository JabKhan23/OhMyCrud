package com.qa.tasklist;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OhMyCrudProjectApplication {

	public static void main(String[] args) {
		
		ApplicationContext beanBag = SpringApplication.run(OhMyCrudProjectApplication.class, args);
		
		//print out local time when system starts up
		System.out.println("Time ran:\t" + beanBag.getBean("getTime",String.class));
	}

}
