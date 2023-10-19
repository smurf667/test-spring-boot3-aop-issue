package com.example.demo;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class MyContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

	@Override
	public void initialize(final ConfigurableApplicationContext applicationContext) {
		applicationContext.addBeanFactoryPostProcessor(
				new MyBeanFactoryPostProcessor());
	}
}
