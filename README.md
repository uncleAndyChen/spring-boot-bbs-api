# 项目地址
* 托管在 github 上的项目链接：https://github.com/uncleAndyChen/spring-boot-bbs-api
* 托管在 gitee 上的项目链接 ：https://gitee.com/uncleAndyChen/spring-boot-bbs-api

# 说明
该项目是以下前端项目的后端 api，前端项目是拿《React进阶之路》第九章示例 [bbs-redux-reselect](https://github.com/xuchaobei/react-book/tree/master/chapter-09/bbs-redux-reselect) 来改的。
仅改了调用api部分和添加了一些备注，之前的api用的是[apicloud](https://d.apicloud.com/mcm/api)。

- 前端项目地址
    - 托管在 github 上的项目链接：https://github.com/uncleAndyChen/react-full-stack-learning
    - 托管在 gitee 上的项目链接 ：https://gitee.com/uncleAndyChen/react-full-stack-learning

# 使用技术
- [Spring Boot v2.1.1](https://github.com/spring-projects/spring-boot)
- [MyBatis v3.4.6](https://github.com/mybatis/mybatis-3)
- [MyBatis Generator (MBG) v1.3.7](https://github.com/mybatis/generator)，关于如何使用，请参考我的另外一个项目：[mybatis-generator](https://github.com/uncleAndyChen/mybatis-generator)

# 运行步骤
## 运行后端
1. 创建数据库：导入项目根目录下的文件 createTable.sql。仅在 MySQL v5.7.x 和 v8.0.11 上测试过，未在别的版本和别的数据库测试。
1. 用 IDEA 或者 Eclipse 导入项目，修改数据库连接参数。需要修改的文件：bbs-api-spring-boot/resources/application.yml，修改好之后，运行起来。
1. 访问 [http://localhost:8080](http://localhost:8080)，可看到 api 测试页面，点击【获取帖子列表】、【获取帖子详情】、【获取评论列表】试试，正常情况下，可以读到数据。

## 运行前端
在 cmd 窗口下，进入前端项目根目录，执行 `npm start`。

## 用 WebStorm 调试前端 React 
请参考：[像用 IDEA 调试 Java 代码一样，用 WebStorm 调试 react 代码](https://www.lovesofttech.com/react/JetBrainsIDESupport)

## 登录
登录之后，才可以发帖和评论。
- 该bbs内置三个用户
    - tom
    - jack
    - steve
- 密码都是：123456

# 计划
由于只是利用业余时间完成该项目，进度可能会比较慢甚至短期中断。
1. [x] 基础框架搭建
1. [x] 查询帖子列表
1. [x] 查询评价列表
1. [x] 查询单个帖子
1. [x] 与前端项目对接
1. [ ] 使用druid
1. [ ] 项目文档
1. [ ] 将前端项目用 [ant-design](https://github.com/ant-design/ant-design) 改造，使用**分页插件和添加分页功能**。

# 修改记录
- 2018-12-19：项目基础架构搭建完成，可以正常查询数据，目前仅完成帖子列表的查询，暂未与前端项目对接。
- 2018-12-20：
    - 与前端项目对接，完成首页列表加载、帖子详情页面加载。
    - 存在的问题：
        - 帖子详情页面加载之后创建评论后页面报错。
        - 帖子详情页面，第一次加载正常，刷新后报错。
- 2018-12-21：完成与前端项目对接剩下的工作。
    - 解决帖子详情页面报错的问题，刷新和创建评论均正常。
    - 完成：创建帖子功能、修改帖子功能。

# 填坑记
数据库设计，主建一律采用自增主键（int 类型），而前端项目，会拿各表的 int 类型的主键当 key 来保存数据之间的关联关系，这样导致数据在 state 与各页面间传输的过程中大乱。

## 表现
- 有的地方，用 state.get(key) 获取数据时，由于 key 是 int 类型，而 state 的类型为 Map，通过修改 js 代码，用 String(key) 的方式将 key 转化为字符类型，但是即使这样转了，有的变量的（Map类型）的 key 还是为 int 类型，应该是自动转了。这样，导致本来应该有数据的地方可能得不到。最后的现象就是：帖子详情页面首次加载正常，但是刷新的时候，又取不到数据了。
- 修改 spring-boot-bbs-api 的 API，把返回的数据，将所有自增 id 改为 String 类型，问题依旧。
- 获取数据时，以字符作为 key 和以 int 作为 key，取到的数据不一样，分别为 Map 和 Object。
![](/bbs-api-spring-boot/resources/static/img/reactGet.png)

## 解决
将 React 的 state 中用作 key 的数据，用字符替换数字返回，但并不是把数字转字符返回，而是在主键的基础上添加前缀的方式。
- 保留数据库设计，在返回数据之前，将主键 id 添加前缀，前缀保存在静态的全局变量中。详情请见代码。
- 帖子详情页面，需要用帖子“主键”（返回时添加过前缀）查询时，将前缀过滤掉。
- 创建评论，入库前，将传过来的“主键”信息的前缀过滤掉，同时转化为 int。
