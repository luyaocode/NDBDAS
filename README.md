<<<<<<< HEAD
# NDBDAS项目记录
## 一、版本情况
=======
#NDBDAS项目记录
##一、版本情况
>>>>>>> 058a504b4d2ff63c27516f5c0fe07b2352bc82f5
1. IDE：ideaIU-2019.3.1    
2. 项目管理工具：apache-maven-3.3.9  
3. jdk	1.8.0_333  
4. springboot  2.3.1.RELEASE  
5. 数据库：mysql8.0.29, Redis-x64-3.0.504  
6. vue 2.6
7. npm 8.11.0
8. Node.js 16.16.0  

前端：  
设备配置：el-icon-set-up /device/conf/{devId}  
设备电参量显示：el-icon-data-line device/show/{devId}  
设备管理："tree" /devmanage  

<<<<<<< HEAD
## 二、日志  
=======
##二、日志  
>>>>>>> 058a504b4d2ff63c27516f5c0fe07b2352bc82f5
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
<<<<<<< HEAD
### 2022.9.13
=======
###2022.9.13
>>>>>>> 058a504b4d2ff63c27516f5c0fe07b2352bc82f5
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

<<<<<<< HEAD
### 2022.9.14 
前后端，数据库串联   
### 2022.9.16
1. 新建网关数据库表，使项目启动时自动查网关表并建立socket连接  
2. 使用多线程接收多个网关的响应：使用全局静态数组存放每个Socket对象，使用@Async注解开启异步方法connect()和receive(),connect()用于与网关建立socket连接并返回socket对象，使用Future< Socket >接收异步方法的返回参数。在项目入口函数进行调用static send(Socket socket)用于向网关传送数据，该方法在controller层进行调用。receive(Socket socket)用于接收网关响应帧  
3. 前端show.vue加入一个控制按钮，编写前后端交互接口 dev.js，新增03读寄存器函数，使得网页点击“控制按钮”，前端携带命令帧字符串通过api传递给后端的controller层。后端读取全局静态数组socket，以其为参数调用静态方法send()向网关发送命令  
4. 接收到网关响应帧之后进行帧格式校验、解析（待完成），编写sql工具类将关键数据更新到对应的数据库表  
### 2022.9.19
1. 新增任务：网关管理页面（网关列表查询、新增、删除、修改，网关手动连接，单个连接，全部连接）  
### 2022.9.22
1. 电压电流实时监控、命令帧测试  
2. 网关配置：查询  
### 2022.9.27
1. 读电压测试帧：  
0x7534=>30004=>电压数据块，大小2*3字节  
发送：0808 0000 0006 01 03 7534 0001  
响应：0808 0000 0006 01 03 数据长度 199.99 209.99 219.99  
2. 读电流测试帧
0x7538=>30008=>电流数据块  
0808 0000 0006 01 03 7538 0001  
3. 读有功  
=======
###2022.9.14 
前后端，数据库串联   
###2022.9.16
1. 新建网关数据库表，使项目启动时自动查网关表并建立socket连接  
2. 使用多线程接收多个网关的响应：使用全局静态数组存放每个Socket对象，使用@Async注解开启异步方法connect()和receive()  
connect()用于与网关建立socket连接并返回socket对象，使用Future< Socket >接收异步方法的返回参数。在项目入口函数进行调用    
static send(Socket socket)用于向网关传送数据，该方法在controller层进行调用  
receive(Socket socket)用于接收网关响应帧  
3. 前端show.vue加入一个控制按钮，编写前后端交互接口 dev.js，新增03读寄存器函数，使得网页点击“控制按钮”，前端携带命令帧字符串通过api传递给后端的controller层。后端读取全局静态数组socket，以其为参数调用静态方法send()向网关发送命令  
4. 接收到网关响应帧之后进行帧格式校验、解析（待完成），编写sql工具类将关键数据更新到对应的数据库表  
###2022.9.19
1. 新增任务：网关管理页面（网关列表查询、新增、删除、修改，网关手动连接，单个连接，全部连接）  
###2022.9.22
1. 电压电流实时监控、命令帧测试
2. 网关配置：查询  
###2022.9.27
1. 读电压测试帧：
0x7534=>30004=>电压数据块，大小2*3字节
发送：0808 0000 0006 01 03 7534 0001  
响应：0808 0000 0006 01 03 数据长度 199.99 209.99 219.99
2. 读电流测试帧
0x7538=>30008=>电流数据块
0808 0000 0006 01 03 7538 0001  
3. 读有功
>>>>>>> 058a504b4d2ff63c27516f5c0fe07b2352bc82f5
0808 0000 0006 01 03 753d 0001 
4. 读无功
0808 0000 0006 01 03 7542 0001  
5. 读视在
0808 0000 0006 01 03 7547 0001  
6. 读功率因数  
<<<<<<< HEAD
### 2022.10.9
后期工作内容   
1. 与网关调通。具体命令帧分类（表cmd_dict）  
2. 新增子模块：系统配置/设备配置+网关配置。以系统配置/参数配置为模板    
3. 新增子模块：系统管理/设备管理。以树的形式展示所有设备。状态。左右两个模块   
4. 新增功能：响应帧解析（支持多协议）、校验、存入数据库  
5. 动态表名（x）    
6. 优化：先在前端显示响应的电参量，再存入数据库   
7. 如何使用redis缓存数据库减轻Mysql压力？  
8. 设备电参量表结构优化  
    实时表  
9. 设备配置  
10. 告警信息-等级和时间排序、后续处理、阈值    
### 2022.10.20
1. 重构了实时电参量表(dev_epara_info_t1 ==>dev_epara_info_temp)，所有设备的实时电参量都放在这张表。新建设备信息表(dev_info)、设备楼层位置信息表(dev_loc_info)  
2. 更新：现在点击不同配电柜会显示正确的电参量信息  
3. 新增：设备管理模块，点击具体楼层房间可以查看该楼层的设备情况  
### 2022.10.27
1. mybatis-plus: (a or b) and c实现的方式：使用QueryWrapper的nested方法将所用or语句括起来  
2. el-table: 根据设备不同状态（0和1）显示不同内容（启用、禁用）  
=======
###2022.10.9
后期工作内容：  
1. 与网关调通。具体命令帧分类（表cmd_dict）  
2. 新增子模块：系统配置/设备配置+网关配置。以系统配置/参数配置为模板  
3. 新增子模块：系统管理/设备管理。以树的形式展示所有设备。状态。左右两个模块  
4. 新增功能：响应帧解析（支持多协议）、校验、存入数据库  
5. 动态表名（x）  
6. 优化：先在前端显示响应的电参量，再存入数据库  
7. 如何使用redis缓存数据库减轻Mysql压力？  
8. 设备电参量表结构优化
    实时表
9. 设备配置
10. 告警信息-等级和时间排序、后续处理、阈值  
###2022.10.20
1. 重构了实时电参量表(dev_epara_info_t1 ==>dev_epara_info_temp)，所有设备的实时电参量都放在这张表。新建设备信息表(dev_info)、设备楼层位置信息表(dev_loc_info)
2. 更新：现在点击不同配电柜会显示正确的电参量信息
3. 新增：设备管理模块，点击具体楼层房间可以查看该楼层的设备情况  
###2022.10.27
1. mybatis-plus: (a or b) and c实现的方式：使用QueryWrapper的nested方法将所用or语句括起来
2. el-table: 根据设备不同状态（0和1）显示不同内容（启用、禁用）
>>>>>>> 058a504b4d2ff63c27516f5c0fe07b2352bc82f5
3. 功能增加：按照设备创建时间搜索设备。细节描述：前端queryParams对象经过addDateRange的js函数后
扩充了一个params属性，该属性是一个包含beginTime和endTime两个属性的对象，不能被DevInfo对象接收（
因为DevInfo对象没有这个params属性），ruoyi框架使用PageUtils里面的ServletUtils来获取时间参数并
赋值给PageVO对象的时间属性，从而在impl类中可以使用QueryWrapper结合时间参数来拼接sql进行查询。
<<<<<<< HEAD
mysql对于datetime类型的字段都是直接将字符串转换成时间进行大小比较的，不需要格式转换。  
=======
mysql对于datetime类型的字段都是直接将字符串转换成时间进行大小比较的，不需要格式转换。
>>>>>>> 058a504b4d2ff63c27516f5c0fe07b2352bc82f5
4. 功能增加：新增设备。新增设备和修改设备共用一个对话窗<el-dialog>，根据form.id的不同决定显示的
是新增还是修改。设备编号devId为必选项，需要将其prop添加到rules进行限制。新增功能使用post方式与
后端交互,api接口将前端form对象使用data传参方式传给controller，后者使用@RequestBody接收data。
在impl类里面需要对设备编号是否为空进行二次校验，并对是否与已存在的设备重复进行校验。最后使用ruoyi
框架统一的接口将新增结果传回前端。前端在submitForm函数里对回传的数据的code进行判断以显示操作结果    
<<<<<<< HEAD
### 2022.10.28
=======
###2022.10.28
>>>>>>> 058a504b4d2ff63c27516f5c0fe07b2352bc82f5
1. 新增功能：修改设备。修改设备有两个入口（会议要求改为一个入口），框选和点选。框选根据ids数组拿到选中的id，点选根据button
拿到id。const id=row.id || this.ids 前面的表示点选，后面的是框选。在js里面，逻辑或（||）运算符
的运行规则是：前面的为true就返回前面的值，后面的就不再判断；否则判断后面的，若后面的为true就返回
后面的值。要求点选的优先级比框选的高，所以row.id应该放在||前面。删除设备：删除同样有两个入口，
<<<<<<< HEAD
框选和点选。与修改不同，删除支持框选删除。  
2. 新增功能：设备回收站。  
3. 后续方向：1、导入导出，2、楼层信息自动挂载，3、设备配置，4、帧解析   
### 2022.11.1
1. 功能增加：点击table每一栏跳转路由到显示设备实时电参量。具体实现：给el-table属性@row-click加一个点击函数，在函数里面用this.$router.push()函数。函数直接以row为形参的话，可以直接调用table每一行数据的属性，而不必在@row-click函数中写明形参。  
问题1： <el-table> 点击行时，会跳转到一个详细信息页面， 但是同时这一行也有编辑和删除按钮,在点击按钮时，@row-click事件也被触发了，而我并不想触发 row-click 事件。  
解决办法： 写按钮的 @click 事件时添加 .stop，表示禁止其他事件，只响应.stop后面跟着的时间。问题2：重置搜索框时，el-tree选中高亮没有取消，解决办法:
this.$refs.tree.setCurrentKey(null);获取当前ref名称为"tree"的结点设置选中的key为null  
2. 下载模板：模板不涉及数据库交互，由前端完成，放在前端静态资源文件夹里面，通过点击a标签超链接的形式下载下来  
### 2022-11-2
1. 设备导出：问题1：url拼接的是/VUE_APP_BASE_API/device/management/export?pageNum=1&pageSize=10，VUE_APP_BASE_API = '/dev-api'表示开发环境，定义于
.env.development文件；VUE_APP_BASE_API = '/prod-api'表示生产环境，定义于.env.production文件。在前端页面打印process.env.VUE_APP_BASE_API，发现
VUE_APP_BASE_API = '/dev-api'。前端端口是81，在vue.config.js文件中，对process.env.VUE_APP_BASE_API进行了拦截，定向到http://localhost:8080，也就是后端。
问题2：在不同的页面点击导出，会携带当前页面的参数pageNum到后端，sql语句LIMIT n,size表示从序号n开始往后查size条记录。已知pageNum=1,pageSize=10时，LIMIT 0,500表示查询序号从0开始的500条记录，第二页参数，pageNum=2,pageSize=10，对应LIMIT 500,500，表示查询序号从500开始的500条记录。要求不管在第几页点击
导出携带参数都是pageNum=1，pageSize=10,不变，所以前端传递查询参数queryParams时，需要浅拷贝对象并设置queryParams.pageNum=1，再将新对象传入导出excel的接口函数
exportExcel  
2. 楼层编号对照表导出：方法同设备导出。  
3. 设备导入：文件上传，前端使用el-upload发送文件，后端使用使用MultipartFile类接收文件，并将excel文件保存到本地。  
### 2022-11-3
1. 设备导入：如果原设备不存在，直接导入记录，如果原设备存在，且要求覆盖旧数据，就直接更新，如果原设备存在，不要求覆盖旧数据，不做任何处理。  

=======
框选和点选。与修改不同，删除支持框选删除。
2. 新增功能：设备回收站。
3. 后续方向：1、导入导出，2、楼层信息自动挂载，3、设备配置，4、帧解析  
###2022.11.1
1. 功能增加：点击table每一栏跳转路由到显示设备实时电参量。具体实现：给el-table属性@row-click加一个点击函数，在函数里面用
this.$router.push()函数。函数直接以row为形参的话，可以直接调用table每一行数据的属性，而不必在@row-click函数中写明形参。
问题1： <el-table> 点击行时，会跳转到一个详细信息页面， 但是同时这一行也有编辑和删除按钮,在点击按钮时，@row-click事件也被触发了，而我并不想触发 row-click 事件。
解决办法： 写按钮的 @click 事件时添加 .stop，表示禁止其他事件，只响应.stop后面跟着的时间。问题2：重置搜索框时，el-tree选中高亮没有取消，解决办法:
this.$refs.tree.setCurrentKey(null);获取当前ref名称为"tree"的结点设置选中的key为null  
2. 下载模板：模板不涉及数据库交互，由前端完成，放在前端静态资源文件夹里面，通过点击a标签超链接的形式下载下来    
###2022-11-2
1. 设备导出：问题1：url拼接的是/VUE_APP_BASE_API/device/management/export?pageNum=1&pageSize=10，VUE_APP_BASE_API = '/dev-api'表示开发环境，定义于
.env.development文件；VUE_APP_BASE_API = '/prod-api'表示生产环境，定义于.env.production文件。在前端页面打印process.env.VUE_APP_BASE_API，发现
VUE_APP_BASE_API = '/dev-api'。前端端口是81，在vue.config.js文件中，对process.env.VUE_APP_BASE_API进行了拦截，定向到http://localhost:8080，也就是后端。
问题2：在不同的页面点击导出，会携带当前页面的参数pageNum到后端，sql语句LIMIT n,size表示从序号n开始往后查size条记录。已知pageNum=1,pageSize=10时，LIMIT 0,500
表示查询序号从0开始的500条记录，第二页参数，pageNum=2,pageSize=10，对应LIMIT 500,500，表示查询序号从500开始的500条记录。要求不管在第几页点击
导出携带参数都是pageNum=1，pageSize=10,不变，所以前端传递查询参数queryParams时，需要浅拷贝对象并设置queryParams.pageNum=1，再将新对象传入导出excel的接口函数
exportExcel  
2. 楼层编号对照表导出：方法同设备导出。 
3. 设备导入：文件上传，前端使用el-upload发送文件，后端使用使用MultipartFile类接收文件，并将excel文件保存到本地。  
###2022-11-3
1. 设备导入：如果原设备不存在，直接导入记录，如果原设备存在，且要求覆盖旧数据，就直接更新，如果原设备存在，不要求覆盖旧数据，不做任何处理。
>>>>>>> 058a504b4d2ff63c27516f5c0fe07b2352bc82f5






 


#Asurplus-Vue

#### 介绍
本项目本着避免重复造轮子的原则，建立一套快速开发JavaWEB项目（asurplus-vue），能满足大部分后台管理系统基础开发功能，使得开发人员直接可从业务模块开始，减少大量的重复开发工作。前端框架使用 ruoyi-vue：https://gitee.com/y_project/RuoYi-Vue?_from=gitee_search

<<<<<<< HEAD
=======
#### 演示环境
加入QQ群后查看群公告：916226778

>>>>>>> 058a504b4d2ff63c27516f5c0fe07b2352bc82f5
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

<<<<<<< HEAD
=======
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
>>>>>>> 058a504b4d2ff63c27516f5c0fe07b2352bc82f5
