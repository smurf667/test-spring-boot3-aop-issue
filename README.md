Demonstration of Spring 6 AOP issue
===================================

This is a distilled example from a more complex real-world scenario.

The issue is that with Spring Boot 3 (Spring 6) a (non-fatal) `INFO` level stacktrace is output,
while with Spring Boot 2 (Spring 5) everything is fine. In both cases the application starts up
and produces the expected output:

```
Service output: Hello, world!
```

However, in Spring Boot 3 this additional stack trace with an error is shown, too:

```
2023-10-19T16:15:42.223+02:00  INFO 18756 --- [           main] o.s.b.f.s.DefaultListableBeanFactory     : FactoryBean threw exception from getObjectType, despite the contract saying that it should return null if the type of its object cannot be determined yet

org.springframework.aop.framework.AopConfigException: TargetSource cannot determine target class: Either an interface or a target is required for proxy creation.
        at org.springframework.aop.framework.DefaultAopProxyFactory.createAopProxy(DefaultAopProxyFactory.java:65) ~[spring-aop-6.0.12.jar:6.0.12]
...
```

The setup is a bit more complicated:

1. An `ApplicationContextInitializer` is configured in `application.properties` (`MyContextInitializer`)
2. `MyContextInitializer` adds a `BeanFactoryPostProcessor` (`MyBeanFactoryPostProcessor`)
3. `MyBeanFactoryPostProcessor` defines a proxied service (`MyServiceImpl`) which just says "Hello, world!" via several beans

When the application is started, the service output is printed to the console.

Changing to Spring Boot 2 (e.g. `<version>2.7.14</version>`) makes this work without a stacktrace in the output.

We've inspected all setters of `org.springframework.aop.framework.ProxyFactoryBean` but nothing we could set would change this behavior.

Are we doing something wrong, or is this an issue/regression in Spring 6? Both in Spring 5 and Spring 6 the application actually works as expected...
