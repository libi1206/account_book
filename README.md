# 整个项目的架构情况一览

### 1. 子工程结构
+ account-book：父工程，打包方式pom，管理jar包的版本号。项目中所有工程都应该继承父工程。
	+ |--account-book-backend：整个项目的后端部分。聚合工程。Pom工程
	    + |--account-book-interface：保存service接口
	    + |--account-book-pojo：保存所有的实体类和dto类
	    + |--account-book-service：业务的真正实现，一个dubbo服务提供者
	+ |--account-book-web：表现层工程，web工程

### 2. 预计使用技术
+ 基础Bean管理：SpringBoot
+ 安全性和权限控制：SpringSecurity 
+ 反向代理：Nginx 
+ RPC框架：Dubbo
+ 注册中心：Zookeeper
+ 消息队列：Kafka
+ 数据库：MySql

### 3. 如何通过源码安装这个应用
* 这是一个SpringBoot应用，因此在安装和打包的时候必须要安装Java环境和Maven
* 这个应用关闭了静态文件映射，使用Nginx做动静分离，因此这个应用需要使用安装Nginx，正确配置并且修改account-book/accout-book-web/src/main/resources/app-param.properties下的头像存储路径。注意，为了和url对应，最后一个文件夹的名字必须要叫/head
* 修改account-book/account-book-backend/account-book-service/src/main/resource/application.yml下的各种环境，包括Zookeeper、Redis所在IP和端口
* 使用Maven在项目根路径下运行如下命令
```
mvn install
mvn clean package
```
* 然后就可以在account-book/account-book-backend/account-book-service/target和account-book/accout-book-web/target里看见这两个jar包，使用下面的命令就可以正确运行整个应用
```
java -jar xxx.jar
```

### 3. 各个服务默认配置情况汇总
+ Zookeeper 
    * 192.168.3.203:2181
+ Redis
    * 192.168.3.203:6379
+ 项目的Service提供者  
    * 127.0.0.1:20881
+ 项目的Web表现层
    * 127.0.0.1:8081
+ Dubbo监控中心
    * 192.168.3.203:8080
+ MySQL数据库
    * 127.0.0.1:3306/money2
+ 消息队列
    * 
    
### 4. 需要解决的问题（已知的BUG）

* ~~所有的属性实体都还没有删除的方法~~
    * 在数据库的连接参数上使用`allowMultiQueries=true`来一次性执行多条语句
    * 在删除时加入 `SET foreign_key_checks = 0;` 来忽视外键约束
* 用户上传的头像更新后还没有删除旧的
* 用户退出家庭后需要做检测，如果这个家庭没有任何用户了，则删除这个家庭
* 创建用户的时候需要自动创建一个账本
* 需要创建默认的分类，所有的用户都可以使用这个分类
* ~~需要过滤不规范的操作，抛出对应的异常码~~
    * **解决方案** ：因为SpringBoot在使用自己的异常处理器`@ExceptionHandler`时必须要去掉静态资源的映射才能正确的抛出404的异常`NoHandlerFoundException`，所以我使用Nginx动静分离把文件存在了nginx中。面对不同的生产环境只需要改变`resource/file-path.properties`下的路径即可，注意，最后一个路径必须要以`/hean`结尾。nginx的配置如下
    
    ```
        #这个标签表示创建一个服务器
        server {
        	#监听端口号
            listen       80;
        	#服务名，配置域名
            server_name  localhost;
            charset utf-8;
        	#配置匹配拦截的URL
            location / {
                proxy_pass   http://127.0.0.1:8081;
            }
        	location /head/ {
        		#拦截后跳转的根路径（文件夹）
        		root		money;
        	}
        }
    ```
* ~~更新时用ID查不到的属性时也会成功，但是不会对数据造成影响~~ 
    * 现在会抛出异常来驳回请求，而且会检查是否是当前登陆账号的属性
* ~~资产和小金库可以被减为负数~~
    * 通过抛出异常的方式解决
* ~~还没有做按时间查询~~ 
    * 已解决
* ~~还没有做（测试）自己的资产转账给自己的资产的操作~~
    * 已解决
* 登陆时用户名没有区分大小写
* ~~查询交易记录按照创建时间顺序查找~~
    * 在SELECT语句上添加ORDER BY语句
* ~~还没有配置共享Session~~
    * 配置如下,只需要在pom文件里添加如下的依赖就好了（同时也需要redis的starter依赖和jedis依赖）
    
    ```
    <dependency>
        <groupId>org.springframework.session</groupId>
        <artifactId>spring-session-data-redis</artifactId>
    </dependency>
    ```
    
 ### 5. 架构上需要改进的问题
正常的编码是7成时间用在架构和分析需求上，3成的时间用在编码上。由于我经验不足，经过草草沟通和分析就开始编码，这导致了我在代码的一致性和整洁上做的不够.具体的问题体现在下面的方面
* 前期由于缺乏规划，DTO类和Entity类的乱用导致代码很乱
* 没有意识到很多Service类都有怎删改查的的方法，没有把公共的方法提出成模板，导致后面重构了很多遍
* 由于前期规划不足没有意识到AOP的注解代理导致很多方法的固定前置方法没有被提出来，导致重复代码很多
 