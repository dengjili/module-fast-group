## module-fast-group
采用maven分模块搭建


## 工程关系
![工程关系](https://raw.githubusercontent.com/dengjili/module-web/master/project-relation.jpg)

## 模块结构
```
module-fast-group 顶层项目
----module-business	业务中心	
--------module-business-mybatis-generator	mybatis代码生成工具(单独成一个子项目)
--------module-business-person	个人中心
--------module-business-person-dao	个人中心dao
--------module-business-person-service	个人中心service
----module-common-util	工具类(单独成一个子项目)
----module-config	基础配置
----module-lib	公共包
----module-document	文档管理
----module-web	前台页面
--------module-business-person	个人前台页面
```

## 模块介绍

| 模块       | 说明          |   进度 |
| ------------- |:-------------:| ----|
|[module-document](module-document)|文档管理| 100%|
|[module-lib](module-lib)|公共包管理| 100%|
|[module-config](module-config) |公共配置| 10%|
|[module-common-util](module-common-util) |公共类| 10%|
|[module-business-mybatis-generator](module-business-mybatis-generator)|代码生成| 0%|
|[module-service](module-service)|业务中心| 0%|
|[module-business-person](module-business-person)|业务中心-个人中心模块| 10%|
|[module-business-person-dao](module-business-person-dao)|个人中心dao模块| 10%|
|[module-business-person-service](module-business-person-service)|个人中心service模块| 10%|
|[module-service-auth](module-service-auth)|业务中心-权限模块| 0%|
|[module-web](module-web)|页面管理| 0%|
|[module-business-person](module-business-person)| 个人前台页面|  0%|
|[module-fast-group](module-fast-group)|顶层pom管理| 0%|