# NDBDAS项目记录
## 一、版本情况
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

## 二、日志  
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
### 2022.9.13
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
0808 0000 0006 01 03 753d 0001 
4. 读无功
0808 0000 0006 01 03 7542 0001  
5. 读视在
0808 0000 0006 01 03 7547 0001  
6. 读功率因数  
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
3. 功能增加：按照设备创建时间搜索设备。细节描述：前端queryParams对象经过addDateRange的js函数后
扩充了一个params属性，该属性是一个包含beginTime和endTime两个属性的对象，不能被DevInfo对象接收（
因为DevInfo对象没有这个params属性），ruoyi框架使用PageUtils里面的ServletUtils来获取时间参数并
赋值给PageVO对象的时间属性，从而在impl类中可以使用QueryWrapper结合时间参数来拼接sql进行查询。
mysql对于datetime类型的字段都是直接将字符串转换成时间进行大小比较的，不需要格式转换。  
4. 功能增加：新增设备。新增设备和修改设备共用一个对话窗<el-dialog>，根据form.id的不同决定显示的
是新增还是修改。设备编号devId为必选项，需要将其prop添加到rules进行限制。新增功能使用post方式与
后端交互,api接口将前端form对象使用data传参方式传给controller，后者使用@RequestBody接收data。
在impl类里面需要对设备编号是否为空进行二次校验，并对是否与已存在的设备重复进行校验。最后使用ruoyi
框架统一的接口将新增结果传回前端。前端在submitForm函数里对回传的数据的code进行判断以显示操作结果    
### 2022.10.28
1. 新增功能：修改设备。修改设备有两个入口（会议要求改为一个入口），框选和点选。框选根据ids数组拿到选中的id，点选根据button
拿到id。const id=row.id || this.ids 前面的表示点选，后面的是框选。在js里面，逻辑或（||）运算符
的运行规则是：前面的为true就返回前面的值，后面的就不再判断；否则判断后面的，若后面的为true就返回
后面的值。要求点选的优先级比框选的高，所以row.id应该放在||前面。删除设备：删除同样有两个入口，
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
### 2022-11-10  
1. 尝试部署项目到云服务器，前端可以；后端打jar包运行不出来，找不到类
### 2022-11-11  
1. 自动连接网关 =》手动连接。需要了解多线程、socket收发，注意：尽量service中写方法，便于后端调用，controller中写业务。  
### 2022-11-14  
1. 打印日志不要使用sout,因为I/O操作消耗性能，改用log.info
2. @Async方式开启receive多线程，receive方法有死循环，主线程会阻塞，导致无法开启下一个receive线程。解决办法：使用继承线程重写run方法的方式构建receive线程.  
3. 根据status的数字值,在字典中查询中文含义,并显示不同的样式.细节:使用插槽slot,用v-for遍历所有gatewayDictInfo所有对象,判断gatewayDictInfo对象的code属性
何时与status相等;使用三目运算符给class绑定自定义的不同的样式    
4. 同步更新网关的状态(0-已连接,1-未连接,2-未知状态).细节:connect是异步的,如何保证connect完成之后再updateStatus?(使用.then()函数:then()方法是异步执行。
意思是：就是当.then()前的方法执行完后再执行then()内部的程序，这样就避免了，数据没获取到等的问题。)  
5. 问题:使用axios接受请求,浏览器可以看到response是有内容的,但是打印却是undefined.
6. 问题：修改了管理员的sign发现进入系统后浏览器只剩下“首页”。  
### 2022-11-15  
1. 问题5：若依框架对axios进行了二次封装，响应拦截器对返回code为500的响应进行了拦截，加入一个提示弹窗，不必改动.前端根据返回的response是否为undefined判断
是否需要进行立刻修改网关状态。  
2. 问题6：有关权限管理  
3. 使用心跳检测来管理网关。  
4. 需求：连接按钮点击后变成断开按钮
5. 需求：一键连接、一键断开  
### 2022-11-18  
会议记录：  应是网关主动连接服务器，心跳检测保留，连接功能删除  
### 2022-11-23  
1. 修改网关为被动连接。问题：任何依赖socket的流的关闭都将导致socket的关闭，并导致发送命令时，客户端(python)循环打印空字节串。解决办法：发送指令后不关闭流，在心跳检测里关闭断开连接的socket  
2. 异常：NotLoginException: Token已被顶下线。解决办法：修改前端项目端口号  
3. 修改数据库字典，发现前端未更新。原因：springboot使用了@Cacheable对字典进行了缓存。解决办法：使用@CacheEvict注解方法清除缓存  
4. 检测到网关连接-》向网关请求设备编号-》网关发送设备编号-》存入数据库-》再向网关请求电参量。 
5. 要学一下权限管理，使支持多账号登录，单账号多端登录。  
###2022-12-5  
下午会议： 
第一，写一个总需求文档（季）
树莓派<->网关：645协议主要为了兼容其他协议，写645协议文档（胡），用组帧例子展示，再写成C函数（洪）
网关协议：modbus tcp ，645等  
挑战杯、互联网+、毕业论文：产品介绍、商业计划书、产品技术文档
PMU功能、多采集+时钟。准确授时+电参量波形  
将配置参数写到配置文件  
###2022-12-5 
增加：数据库表更新、网关字典更新  
###2022-12-6  
修改首页，园区图降一级。关键信息：园区总功率（各楼栋功率、各楼层功率、各房间功率）、设备数量（在线设备数量、离线设备数量）
为每个svg图层g标签添加高亮...
注册设备：将服务端的设备信息告知网关，网关据此请求电参量
重要：删除dev_gateway_info ，合并到dev_info表（设备编号、设备所在楼层、设备所属网关（添加字段,gateway_id）、设备状态(status)、设备序号（sort））
保留gateway_info，增加设备时，查询网关表，若有，则可选，如没有，也可手动添加
注册设备时，主动告知网关所辖设备。网关主动连接时，服务端查询dev_info，告知网关所辖设备。
优化: 基于modbus-tcp协议优化收发策略，服务端为每台设备制定上报发送电参量的任务 
考虑实时电参量表的扩容  
### 2022-12-13  
1. el-table根据属性显示不同的内容，例如根据0/1显示正确/错误。方法：el-table-column内嵌template，scope包含当前行的所有数据，scope.row  
2. el-table根据属性值查字典，显示字典内容。同上，使用js函数对scope的属性先进行一个转换再渲染  
3. el-form绑定的数据是id，显示的是字典里的数据。
el-form-item>el-select v-model="form.id">el-option>:key="item.id" :label="item.ip+':'+item.port" :value="item.id"
解读：  
label	这是给用户看的，当点击下拉菜单时，会出来选项，用户看到的选项就是这个  
value	这是你点击某个label(option)之后，将对应的值给v-model绑定的值model  
key	    这个呢相当于身份令牌，唯一的，防止出错，虽然没有也行，但是官网推荐还是加上为好。  
一般情况下，key和label都不是必须的，label不写，默认是value，也就是用户看到的是value  
### 2022-12-14 
1. 优化：点击取消时，修改页面未完全消失，reset表单已经执行，使用setTimeout进行延时  
2. 引入echarts 5.3.3 绘制树状图。问题：解决vue报错-Syntax Error: TypeError: this.getOptions is not a function  
解决办法：降低less-loader的版本，且加--force强制
3. this指向组件的实例。$el指向当前组件的DOM元素，在Vue中，$el指向当前组件template模板中的根标签。this.$el只在mounted中才有效  
4. 踩坑：TreeChart组件模板根元素的样式设置为height:100%，实际上高度为0px，从而无法显示树状图。已确认父组件传值给子组件的方法没错  
5. 复习：jq中的$.get(url,function(data))表示向url发起一个请求，返回数据为data，传入function  
  




 










 


#Asurplus-Vue

#### 介绍
本项目本着避免重复造轮子的原则，建立一套快速开发JavaWEB项目（asurplus-vue），能满足大部分后台管理系统基础开发功能，使得开发人员直接可从业务模块开始，减少大量的重复开发工作。前端框架使用 ruoyi-vue：https://gitee.com/y_project/RuoYi-Vue?_from=gitee_search

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

