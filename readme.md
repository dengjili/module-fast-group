### 这是一个记录开发技术的项目，设计以下过程

**当前系统阶段为 2**

 1. 后台springmvc，spring，mybatis，页面jstl+jsp（ 前后端绑定），服务器tomcat，版本采用git管理，托管于github
 2. url支持restfull风格，建表模型化，引入前台框架layui/bootstrap框架，禁止引入前端框架的数据引擎，防止后续改造困难
 3. 前后端办分离（组件化，模块化）
 4. 完善ssm框架，提供分页，异常等框架的支持,提供NoSQL支持，选择redis，完成一个基础blog的页面
 5. 页面引入中间件，页面搜索。
 51. 引入Shiro框架，开发一套权限系统
 52. 持续集成之代码质量管理-Sonar，改变不良编写代码习惯
 6. 数据库读写分离，分表分库，mycat中间件
 61. 完善后台进程辅助功能,形成补偿系统，中间件quartz，搭建周边系统
 7. 支持RPC，引入dubbo远程调用 , 大量日志中间件kafaka 
 71. 系统若为分布式，应支持单点登录
 72. 引入分布式消息中间件，主流mq
 72. spring boot Spring Cloud，soa,微服务,服务治理？
 72. 高系统性能，负载均衡，nginx中间件，其他LVS等
 8. 自定义框架，如使用自定义springmvc，spring，mybatis，dubbo等，属于源码阅读，后续使用自定义的框架替代
 9. 关于部署，jenkins自动化部署
 10. 工作流引入，业务流程化
 