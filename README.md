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

### 3. 各个服务配置情况汇总
+ Zookeeper 
    * 192.168.3.203:2181
+ Redis
    * 192.168.3.203:6379
+ 项目的Service提供者  
    * 127.0.0.1:20880
+ 项目的Web表现层
    * 127.0.0.1:8081
+ Dubbo监控中心
    * 192.168.3.203:8080
+ MySql数据库
    * 127.0.0.1:3306/money2
+ 消息队列
    * 
    
### 3. 需要解决的问题
* 所有的属性实体都还没有删除的方法
* 用户上传的头像更新后还没有删除旧的
* 创建用户的时候需要自动创建一个账本
* 需要创建默认的分类，所有的用户都可以使用这个分类
