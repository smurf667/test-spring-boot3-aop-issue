package com.example.demo;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.target.SingletonTargetSource;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) beanFactory;
		final String beanName = "myBeanName";
		final String targetSource = "myTargetSource";

		defaultListableBeanFactory.registerBeanDefinition(targetSource,
				BeanDefinitionBuilder.genericBeanDefinition(SingletonTargetSource.class)
						.addConstructorArgValue(new MyServiceImpl()).getBeanDefinition());
		defaultListableBeanFactory.registerBeanDefinition(beanName,
				BeanDefinitionBuilder.genericBeanDefinition(ProxyFactoryBean.class)
						.addPropertyReference("targetSource", targetSource).getBeanDefinition());
	}

}
