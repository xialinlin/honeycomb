# honeycomb  
spring cloud client快速开发脚手架 

### 脚手架包含
* swagger
* spring boot2
* spring cloud 2.0.1
* mybatis , mybatis分页插件pagehelper
* druid
* crud快速开发模块-待续。。。。
* 分布式事物lcn,感谢lcn作者支持。  
[lcn官方网址: http://www.txlcn.org ](http://www.txlcn.org/)   

### 使用手册
下载源码，使用maven进入工程总目录执行以下命令

```
mvn clean install
```
pom中添加引入
```
       
		<dependency>
			<groupId>honeycomb</groupId>
			<artifactId>honeycomb-cloud-starter</artifactId>
			<version>0.0.1-SNAPSHOT</version>
		</dependency>
如果想在项目中使用分布式事务lcn。则直接使用下面引用
```
       
		<dependency>
			<groupId>honeycomb</groupId>
			<artifactId>honeycomb-cloud-starter</artifactId>
			<version>0.0.1-SNAPSHOT</version>
		</dependency>

配置文件列子application.yml


```
server:
  port: 8080
  
  
eureka:
  client:
    serviceUrl:
      defaultZone: http://dy.ayouke.com:8761/eureka/
     
      
spring:
  application:
    name: TEST1 
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource
    url: jdbc:mysql://120.92.76.218:3306/youke?serverTimezone=Asia/Shanghai&useSSL=false
    username: 
    password: 
    driverClassName: com.mysql.cj.jdbc.Driver
    initialSize: 5
    minIdle: 5
    maxActive: 20
    maxWait: 60000
    timeBetweenEvictionRunsMillis: 60000
    minEvictableIdleTimeMillis: 300000
    validationQuery: SELECT 1 FROM DUAL
    testWhileIdle: true
    testOnBorrow: false
    testOnReturn: false
    poolPreparedStatements: false
    maxPoolPreparedStatementPerConnectionSize: 20
    filters: stat,wall,log4j
    connectionProperties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000
  jackson:
    date-format: yyyy-MM-dd HH:mm:ss
    default-property-inclusion: non_empty
  
mybatis:
  mapper-locations: classpath*:com/hs/plat/*/po/xml/entity/*.xml,classpath*:com/hs/plat/*/po/xml/*.xml 
```