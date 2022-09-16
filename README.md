#NDBDAS项目记录
一. 版本情况
1. IDE：ideaIU-2019.3.1    
2. 项目管理工具：apache-maven-3.3.9  
3. jdk	1.8.0_333  
4. springboot  2.3.1.RELEASE  
5. 数据库：mysql8.0.29, Redis-x64-3.0.504  
6. vue 2.6
7. npm 8.11.0
8. Node.js 16.16.0  

二. 日志  
<el-card> 加页面模块  
asurplus-ui/src/layout/components/Sidebar/Logo.vue      侧边栏的系统名称  
侧边栏加模块，直接改数据库  
还要改路由   
views/config        系统配置  
views/monitor       系统监控  
views/system        系统管理  
views/tools         系统工具  
部署到linux服务器（×）  
前端：  
    ctrl+shift+r查找关键字localhost和127.0.0.1，替换为linux服务器ip地址192.168.253.130  
数据库：  
    windows  
后端：  
文件application-dev和application-prod.yml  
    server.port：后端运行的端口号，默认8080  
    spring.datasource.url：连接MYSQL数据库，url改成windows的ip地址  
    username：MYSQL用户名  
    password：MYSQL密码  
mysql  
	sys_permission_info		侧边栏主菜单  
首页      
设备管理(device)       
#####2022.9.13
数据库设计  
dev_epara_info  
电压voltage---volt_a/b/c  
电流current---curr_a/b/c  
总功率total_pow  
频率freq  
有功功率---acti_pow_a/b/c  
无功功率---watt_pow_a/b/c  
视在功率---appr_pow_a/b/c  
相角phase_angle  
电压相角---volt_pha_a/b/c  
电流相角---curr_pha_a/b/c  
  
#####2022.9.14 
前后端，数据库串联  
#####2022.9.16
1. 新建网关数据库表，使项目启动时自动查网关表并建立socket连接  
2. 使用多线程接收多个网关的响应：使用全局静态数组存放每个Socket对象，使用@Async注解开启异步方法connect()和receive()  
connect()用于与网关建立socket连接并返回socket对象，使用Future< Socket >接收异步方法的返回参数。在项目入口函数进行调用    
static send(Socket socket)用于向网关传送数据，该方法在controller层进行调用  
receive(Socket socket)用于接收网关响应帧  
3. 前端show.vue加入一个控制按钮，编写前后端交互接口 dev.js，新增03读寄存器函数，使得网页点击“控制按钮”，前端携带命令帧字符串通过api传递给后端的controller层。后端读取全局静态数组socket，以其为参数调用静态方法send()向网关发送命令  
4. 接收到网关响应帧之后进行帧格式校验、解析（待完成），编写sql工具类将关键数据更新到对应的数据库表  



#Asurplus-Vue

#### 介绍
本项目本着避免重复造轮子的原则，建立一套快速开发JavaWEB项目（asurplus-vue），能满足大部分后台管理系统基础开发功能，使得开发人员直接可从业务模块开始，减少大量的重复开发工作。前端框架使用 ruoyi-vue：https://gitee.com/y_project/RuoYi-Vue?_from=gitee_search

#### 演示环境
加入QQ群后查看群公告：916226778

#### 软件架构

后端：
1.  后端采用SpringBoot + MyBatisPlus为主的一个架构
2.  权限控制采用Sa-Token框架，这是一个新的框架，文档地址：https://sa-token.dev33.cn/doc/index.html#/，Sa-Token 是一个轻量级 Java 权限认证框架，主要解决：登录认证、权限认证、Session会话、单点登录、OAuth2.0、微服务网关鉴权 等一系列权限相关问题。
3.  数据库依然采用的是MySQL，然后连接池换成了Alibab的Druid，在数据监控模块也集成了它的监控页面。
4.  缓存框架采用的是Redis，使用Sa-Token与Redis相整合，还有验证码模块也会用到Redis。
5.  文件上传依然采用的是Minio，依然是那么的好用。

前端：
1.  采用的模板是Ruoyi-vue中的ruoyi-ui模板，进行了自己的改造，然后有了asurplus-ui。
2.  前端的文档可以参照ruoyi-ui的文档，http://doc.ruoyi.vip/ruoyi-vue/。

#### 安装教程

后端：
1.  下载项目源码后，将pom.xml文件右键，选择 Add maven project，添加成Maven项目，才会进行Maven依赖下载。
2.  本项目使用了lombok插件，需要先安装lombok插件。
3.  在db目录中找到SQL文件，在MySQL中新建数据库：asurplus-vue，导入数据库文件。
4.  修改配置文件中的MySQL连接信息为自己的，修改Redis连接信息为自己的，修改Minio连接信息为自己的。
5.  接下来就可以启动后端项目了。

前端：
1.  打开控制台Terminal，进入到asurplus-ui目录。
2.  同样，我们vue项目也需要下载对应的依赖，推荐使用命令：npm install --registry=https://registry.npm.taobao.org 命令安装依赖，不然会遇到各种各样的问题。
3.  启动后端项目，输入命令：npm run dev，等待启动完成，会自动打开浏览器访问，也可以自己输入访问地址：localhost:80，即可访问。
4.  超级管理员的账户密码为：admin，123456.

#### 使用说明

1.  系统模板：没什么好说的， 就是用户-角色-菜单权限。
2.  数据监控：记录了登录日志，操作日志（SysLog注解实现的），数据监控（Druid），定时任务（Quartz），服务监控（Server），缓存监控（Redis）。
3.  系统配置：参数配置（系统中使用的一些可变参数的配置，通过参数名拿到参数值），APP版本（不用管，我自己写项目的时候使用的）。
4.  系统工具：表单构建（使用拖拉拽的方式生成vue表单），代码生成（通过在MySQL建立表后，导入到代码生成中，修改对应的配置信息，可自动生成对应的后端CRUD代码和前端VUE代码），接口文档（Knife4j）。

## 后端

### 添加为Maven项目

找到 pom.xml 文件，右键 "Add As Maven Project"，开始下载 Maven 依赖

### 修改项目配置

找到 application-xxx.yml 文件

   1. 修改 MySQL 配置信息，连接地址，数据库名称，端口，用户名，用户密码
   2. 修改 Redis 配置信息，连接地址，数据库索引，密码

## 前端

### 下载依赖

`npm install --registry=https://registry.npm.taobao.org`

### 启动前端

`npm run dev`

### 打包上线

`npm run build:prod`

### 部署 Nginx

```
# 后台管理端入口
location / {
     root   /home/ruoyi/projects/ruoyi-ui;
     try_files $uri $uri/ /index.html;
     index  index.html index.htm;
}
```

```
# 后台服务端入口
location /prod-api/ {
     proxy_set_header Host $http_host;
     proxy_set_header X-Real-IP $remote_addr;
     proxy_set_header REMOTE-HOST $remote_addr;
     proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
     proxy_pass http://localhost:8080/;
}
```

### 增加前端访问路径

   1. 修改 vue.config.js 文件中的 publicPath 属性

`publicPath: process.env.NODE_ENV === "production" ? "/admin/" : "/admin/",`

   2. 修改 router/index.js 文件中添加一行 base 属性

```
export default new Router({
    base: "/admin",
    mode: 'history', // 去掉url中的#
    scrollBehavior: () => ({ y: 0 }),
    routes: constantRoutes
})
```

   3. /index 路由添加获取子路径 /admin，修改 layout/components/Navbar.vue 文件中的 location.href

`location.href = '/admin/index';`

   4. 修改 utils/request.js 文件中的 location.href

`location.href = '/admin/index';`

   5. 修改 Nginx 配置

```
# 后台管理端入口
location /admin {
    alias   /home/ruoyi/projects/ruoyi-ui;
    try_files $uri $uri/ /admin/index.html;
    index  index.html index.htm;
}

# druid监控入口
location /druid/ {
    proxy_pass http://localhost:8080/druid/;
}
```

### 增加后端访问路径

   1. 修改 application-xxx.yml 文件中 server.servlet.context-path 的值
```
# 系统配置
server:
  servlet:
    # 项目路径
    context-path: /admin
```

   2. 修改 Nginx 配置
   
```
# 后台服务端入口
location /prod-api/ {
     proxy_set_header Host $http_host;
     proxy_set_header X-Real-IP $remote_addr;
     proxy_set_header REMOTE-HOST $remote_addr;
     proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
     proxy_pass http://localhost:8080/admin/;
}
```

#### 页面展示

<table>
    <tr>
        <td><img src="https://img-blog.csdnimg.cn/544b9d95e54b43e2bb04f124c817b9f9.png"/></td>
        <td><img src="https://img-blog.csdnimg.cn/0d51b692e92948f3b743c7c1f945dab4.png"/></td>
    </tr>
    <tr>
        <td><img src="https://img-blog.csdnimg.cn/13336fcf2aea41de855023304f3e5f17.png"/></td>
        <td><img src="https://img-blog.csdnimg.cn/642eb137282f4e3f858ce8cd660b87b3.png"/></td>
    </tr>
    <tr>
        <td><img src="https://img-blog.csdnimg.cn/f8e01dd9a9d745c4942998740c6b6cac.png"/></td>
        <td><img src="https://img-blog.csdnimg.cn/e89b816177474aa6b004da30ae055f37.png"/></td>
    </tr>
	<tr>
        <td><img src="https://img-blog.csdnimg.cn/a393646f954f44979a409d19c1c1411e.png"/></td>
        <td><img src="https://img-blog.csdnimg.cn/928a3a684914449aabc80e044d014179.png"/></td>
    </tr>
    <tr>
        <td><img src="https://img-blog.csdnimg.cn/0440b58e8064454187e963e6d56899cd.png"/></td>
        <td><img src="https://img-blog.csdnimg.cn/efe9dad92969485db0af3ad7554bd3ff.png"/></td>
    </tr>
</table>

#### Asurplus

[![李洲/asurplus-vue](https://gitee.com/asurplus/asurplus-vue/widgets/widget_card.svg?colors=eae9d7,2e2f29,272822,484a45,eae9d7,747571)](https://gitee.com/asurplus/asurplus-vue)

#### 捐赠

如果觉得还不错，请作者喝杯咖啡吧 ☺

<table>
    <tr>
        <td style="text-align: center">微信扫一扫</td>
        <td style="text-align: center">支付宝扫一扫</td>
    </tr>
    <tr>
        <td><img src="https://img-blog.csdnimg.cn/20210221195740916.png"/></td>
        <td><img src="https://img-blog.csdnimg.cn/20210221195829103.jpg"/></td>
    </tr>
</table>

#### 交流QQ群
群号：<b>916226778</b>

<img src="https://img-blog.csdnimg.cn/20210427105454901.jpg" alt="微信公众号" />

#### 关注

欢迎关注我的微信公众号

<img src="https://img-blog.csdnimg.cn/20210221200018475.jpg" alt="微信公众号" />
