## 《分布式数据服务Tx-Service》

## 一、简介

### 1. 概述
Tx-service 是一款腾讯课堂数据服务系统，现已开放源代码，开箱即用。

### 2.系统架构

#### 2.1 系统架构图
<p align="center">
  <img src="https://i.niupic.com/images/2020/05/10/7L2C.png" alt="系统架构图"  width="70%" />
</p>


#### 2.2 架构说明

* **数据调度层** 

    数据调度主要采用Linux服务器自带的crontab进行shell任务周期性调度,暂时未采用分布式调度调度框架(dolphinshceduler,airflow等),故
    对于任务容灾,任务重跑等暂不支持


* **数据采集层** 

    数据采集主要采用python scrapy爬虫框架进行工作,通过数据库DB保存好的爬虫种子ID读取,构建完整链接,然后丢给scrapy平台,自行调度
    
     ##### 该采集突破反扒包含：
     - **动态IP代理池**主要负责不断更改Scrapy Proxy HttpCrawMiddleWare中的IP地址，防止系统网站是reboot攻击,进行黑白名单ip屏蔽。

     - **动态随机Agent**主要用于Scrapy请求时,随机分配Request Agent，伪装成普通用户请求,避免系统网站认为是reboot请求,进行拦截屏蔽.

* **数据应用层** 

    Web应用层主要采用Springboot框架,利用其开箱即用特性来提供数据服务能力

* **UI** 

    系统的前端页面采用VUE,提供系统的可视化操作界面。

## 二、后端&前端开发文档    
## 环境要求

 * [Mysql](https://dev.mysql.com/downloads/mysql/) (5.7+) :  必装
 * [JDK](https://www.oracle.com/technetwork/java/javase/downloads/index.html) (1.8+) :  必装
 * [Maven](http://maven.apache.org/download.cgi) (3.3+) ：必装
 * [Anaconda](https://repo.anaconda.com/archive/Anaconda3-2020.02-Windows-x86_64.exe) (4.8.3+) ：必装
 * [Node.js](https://nodejs.org/en/) (12.16.3+) ：必装 
 
 ## Tx-service-web项目编译运行
 将Tx-service源码下载导入Idea开发工具后，首先转为Maven项目(右键点击后选择"Add Framework Support")
 
 * linux&windows平台编译命令：
 
 ```
  mvn -U clean package -pl tx-service-web/tx-service-server -am -Dmaven.test.skip=true
 ```
 * 本地运行：
  
   ![输入图片说明](https://i.niupic.com/images/2020/05/10/7L2L.png "在这里输入图片标题")

## Tx-service-python项目编译运行

- 安装scrapy依赖工具: 

   `直接安装命令: pip install -r requestments.txt`
   `或者利用conda进行安装依赖`
- 本地运行:
   ![输入图片说明](https://i.niupic.com/images/2020/05/10/7L3k.png "在这里输入图片标题")
- linux 脚本运行:

  `nohup run.sh classmate >/dev/null 2>&1 &`  
- 注意事项：

  `修改settings.py的动态代理池ip.txt路径,调成自己服务机器路径`

## Tx-service-front项目编译运行  

- 安装vue依赖工具: 

  - 淘宝镜像命令行输入: `npm install -g cnpm --registry=https://registry.npm.taobao.org`
  - 用命令行模式 `cd`  进入 `Tx-service-front`项目目录并执行 `npm install` 拉取项目依赖包

- 本地运行：
  `npm run serve`  
  
  ![输入图片说明](https://i.niupic.com/images/2020/05/10/7L6J.png "在这里输入图片标题")
  

### 总结
本文从调度出发，初步介绍了腾讯课堂数据采集服务系统--Tx-Service的架构与运行模式。未完待续



