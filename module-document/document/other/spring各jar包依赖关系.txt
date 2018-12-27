### spring core 
core部分包含4个模块
spring-core：依赖注入IoC与DI的最基本实现
spring-beans：Bean工厂与bean的装配
spring-context：spring的context上下文即IoC容器
spring-expression：spring表达式语言

#### 依赖关系
spring-core
	 -->
	 commons-log (可用指定的log替代)
spring-beans
	 -->
	 spring-core
spring-expression
	 -->
	 spring-core
spring-context
	 -->
	 spring-core
	 spring-beans
	 spring-context
	 spring-aop
	 
### spring aop 
aop部分包含4个模块

spring-aop：面向切面编程
spring-aspects：集成AspectJ
spring-instrument：提供一些类级的工具支持和ClassLoader级的实现，用于服务器
spring-instrument-tomcat：针对tomcat的instrument实现

#### 依赖关系
spring-aop
	 -->
	 spring-core
	 spring-beans
	 aopalliance
spring-aspects
	 -->
	 aspectjrt  事务包
	 aspectjweaver  代理包
		 
### spring data access 
data access部分包含5个模块

spring-jdbc：jdbc的支持
spring-tx：事务控制
spring-orm：对象关系映射，集成orm框架
spring-oxm：对象xml映射
spring-jms：java消息服务

#### 依赖关系
spring-jdbc
	 -->
	 spring-core
	 spring-beans
	 spring-tx
spring-tx
	 -->
	 spring-core
	 spring-beans
spring-orm
	 -->
	 spring-core
	 spring-beans
	 spring-tx
	 spring-jdbc
spring-oxm
	 -->
	 spring-core
	 spring-beans
spring-jms
	 -->
	 spring-core
	 spring-beans
	 spring-tx
	 spring-aop
	 spring-context
### spring web 
web部分包含4个模块

spring-web：基础web功能，如文件上传
spring-webmvc：mvc实现
spring-webmvc-portlet：基于portlet的mvc实现

#### 依赖关系
spring-web
	 -->
	 spring-core
	 spring-beans
	 spring-aop
	 spring-context
spring-webmvc
	 -->
	 spring-core
	 spring-beans
	 spring-aop
	 spring-context
	 spring-expression
spring-webmvc-portlet
	 -->
	 spring-core
	 spring-beans
	 spring-web
	 spring-webmvc
	 spring-context